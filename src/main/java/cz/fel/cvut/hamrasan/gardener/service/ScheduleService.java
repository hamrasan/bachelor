package cz.fel.cvut.hamrasan.gardener.service;

import cz.fel.cvut.hamrasan.gardener.dao.NotificationDao;
import cz.fel.cvut.hamrasan.gardener.dao.UserDao;
import cz.fel.cvut.hamrasan.gardener.dao.ValveDao;
import cz.fel.cvut.hamrasan.gardener.dao.ValveScheduleDao;
import cz.fel.cvut.hamrasan.gardener.dto.ValveScheduleDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.model.*;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.*;

@Service
public class ScheduleService {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    private ValveDao valveDao;
    private ValveService valveService;
    private ValveScheduleDao valveScheduleDao;
    private UserDao userDao;
    private TranslateService translateService;
    private NotificationDao notificationDao;
    private final static Logger LOGGER = Logger.getLogger(ScheduleService.class.getName());


    @Autowired
    public ScheduleService(ValveDao valveDao, ValveService valveService, ValveScheduleDao valveScheduleDao, UserDao userDao,
                           TranslateService translateService, NotificationDao notificationDao) {

        this.valveDao = valveDao;
        this.valveService = valveService;
        this.valveScheduleDao = valveScheduleDao;
        this.userDao = userDao;
        this.translateService = translateService;
        this.notificationDao = notificationDao;
    }


    /**
     * Method sets all schedule of valves every day at 00:00
     */
    @Scheduled(cron = "0 0 0 * * *", zone = "CET")
    public void scheduler() {

        for (Valve valve : valveDao.findAll()) {
            for (ValveSchedule valveSchedule: valveScheduleDao.findByValve(valve.getId())) {
                final Runnable valving = new Runnable() {
                    public void run() {
                        if(valveScheduleDao.find(valveSchedule.getId()) != null) {
                            try {
                                valveService.moveValve(valve.getName(), "true");
                                valveService.setStopValving(valve.getName(), valveSchedule.getLength());
                                Notification notification = new Notification(LocalDate.now(), "Polievam polievačom " + valve.getName(), NotificationType.VALVING, valve.getUser());
                                notificationDao.persist(notification);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
//                final ScheduledFuture<?> valveHandle = scheduler.schedule(valving, (valveSchedule.getHour()- LocalDateTime.now().getHour())+ (valveSchedule.getMinutes() - LocalDateTime.now().getMinute()), MINUTES);
                Date date = new Date(System.currentTimeMillis());
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                    //DAY must be +1 bcs Sunday is 1 in Calendar
                if(valveSchedule.getDays().contains(c.get(Calendar.DAY_OF_WEEK)-1)){
                    final ScheduledFuture<?> valveHandle = scheduler.schedule(valving, (valveSchedule.getHour()*60) + valveSchedule.getMinutes(), MINUTES);
                    LOGGER.info("Valving schedule at " + valveSchedule.getHour() + ":" + valveSchedule.getMinutes() +" for valve " + valve.getName() +" is set");
                }
            }
        }
    }


    /**
     * Method saves schedule of valve to database and setup schedule if schedule is today
     * @param nameValve
     * @param days
     * @param time
     * @param valvingLength
     * @throws NotAllowedException
     */
    @Transactional
    public void setSchedule(String nameValve, List<Integer> days, String time, Integer valvingLength) throws NotAllowedException {
        Valve valve = valveDao.findByName(nameValve);
        User user = userDao.find(SecurityUtils.getCurrentUser().getId());
        String[] arrOfStr = time.split(":");

        if(!user.getValves().contains(valve)) throw new NotAllowedException("Not allowed operation");
        ValveSchedule valveSchedule = new ValveSchedule(Integer.parseInt(arrOfStr[0]), Integer.parseInt(arrOfStr[1]), valvingLength, valve, days);

        final Runnable valving = new Runnable() {
            public void run() {
                    try {
                        valveService.moveValve(valve.getName(), "true");
                        valveService.setStopValving(valve.getName(), valveSchedule.getLength());
                        Notification notification = new Notification(LocalDate.now(), "Polievam polievačom " + valve.getName(), NotificationType.VALVING, valve.getUser());
                        notificationDao.persist(notification);
                        LOGGER.info("Valving for user with id: " + user.getId() + " is running");
                    } catch (Exception e) {
                        LOGGER.warning("The problem with valving of valve name "+ valve.getName());
                        e.printStackTrace();
                    }
            }
        };

        Date date = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //DAY must be +1 bcs Sunday is 1 in Calendar
        if(valveSchedule.getDays().contains(c.get(Calendar.DAY_OF_WEEK)-1)){
            if(valveSchedule.getHour() > LocalDateTime.now().getHour()){
                final ScheduledFuture<?> valveHandle = scheduler.schedule(valving, (valveSchedule.getHour()- LocalDateTime.now().getHour())*60 + (valveSchedule.getMinutes() - LocalDateTime.now().getMinute()), MINUTES);
            }
            else if((valveSchedule.getHour() == LocalDateTime.now().getHour()) && (valveSchedule.getMinutes() > LocalDateTime.now().getMinute())){
                final ScheduledFuture<?> valveHandle = scheduler.schedule(valving,  valveSchedule.getMinutes() - LocalDateTime.now().getMinute(), MINUTES);
            }
        }

        valveScheduleDao.persist(valveSchedule);
        LOGGER.info("Valving schedule for user with id: " + user.getId() + " and valve name " + valve.getName() +" is set");
    }


    /**
     * Method gets all schedules of user
     * @param valveName
     * @return
     * @throws NotAllowedException
     */
    @Transactional
    public List<ValveScheduleDto> getUserSchedules(String valveName) throws NotAllowedException {
        User user = userDao.find(SecurityUtils.getCurrentUser().getId());
        Valve valve = valveDao.findByName(valveName);
        List<ValveScheduleDto> valveScheduleDtos = new ArrayList<>();
        if(!user.getValves().contains(valve)) throw new NotAllowedException("Not yours valve");

        for (ValveSchedule valveSchedule : valveScheduleDao.findByValve(valve.getId())) {
            valveScheduleDtos.add(translateService.translateValveSchedule(valveSchedule));
        }
        return valveScheduleDtos;
    }

    @Transactional
    public void deleteSchedule(Long id){
        ValveSchedule valveSchedule = valveScheduleDao.find(id);
        valveScheduleDao.remove(valveSchedule);
    }

}
