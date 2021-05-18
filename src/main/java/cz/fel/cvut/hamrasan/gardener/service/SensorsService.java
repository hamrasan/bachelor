package cz.fel.cvut.hamrasan.gardener.service;

import cz.fel.cvut.hamrasan.gardener.amqp.rpc.Tut6Client;
import cz.fel.cvut.hamrasan.gardener.dao.*;
import cz.fel.cvut.hamrasan.gardener.dto.*;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.model.*;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SensorsService {

    private PressureDao pressureDao;
    private GardenDao gardenDao;
    private TemperatureDao temperatureDao;
    private HumidityDao humidityDao;
    private TranslateService translateService;
    private Tut6Client client;
    private RainDao rainDao;
    private NotificationService notificationService;
    private SoilDao soilDao;
    private UserDao userDao;

    @Autowired
    public SensorsService(PressureDao pressureDao, GardenDao gardenDao, TemperatureDao temperatureDao, HumidityDao humidityDao,
                      TranslateService translateService, Tut6Client client, RainDao rainDao, NotificationService notificationService, SoilDao soilDao,
                      UserDao userDao) {

        this.pressureDao = pressureDao;
        this.gardenDao = gardenDao;
        this.temperatureDao = temperatureDao;
        this.humidityDao = humidityDao;
        this.translateService = translateService;
        this.client = client;
        this.rainDao = rainDao;
        this.notificationService = notificationService;
        this.soilDao = soilDao;
        this.userDao = userDao;
    }

    @Transactional
    public void savePress(String data, String key){
        pressureDao.persist(new Pressure(LocalDateTime.now(), Float.parseFloat(data), gardenDao.find(Long.parseLong(key.substring(8))) ));
    }


    /**
     * Method saves temperature data to database and sends notifications about change  and saves that to database
     * @param data - temperature data
     * @param key - garden id
     * @throws NotFoundException
     */
    @Transactional
    public void saveTemperatue(String data, String key) throws NotFoundException {
        Garden garden =  gardenDao.find(Long.parseLong(key.substring(4)));
        User user = userDao.find(garden.getUser().getId());

        if(temperatureDao.findLatest(garden) != null){
            if((Float.parseFloat(data) > user.getHighTemperature()) && (temperatureDao.findLatest(garden).getValue() <= user.getHighTemperature())){
                String message = "Teplota stúpla nad požadovanú teplotu " + user.getHighTemperature() + " °C.";

                notificationService.addNotification(LocalDate.now(), message, NotificationType.HIGHTEMPERATURE, user);
            }

            if((Float.parseFloat(data) < user.getLowTemperature()) && (temperatureDao.findLatest(garden).getValue() >= user.getLowTemperature())){
                String message = "Teplota klesla pod požadovanú teplotu " + user.getLowTemperature() + " °C.";

                notificationService.addNotification(LocalDate.now(), message, NotificationType.LOWTEMPERATURE, user);
            }

            if(temperatureDao.findLatest(garden).getValue() > (Float.parseFloat(data)) ){
                for (UserPlant userPlant: garden.getPlants()) {
                    if(userPlant.getMinTemperature() < temperatureDao.findLatest(garden).getValue() && userPlant.getMinTemperature() > (Float.parseFloat(data))){
                        String message = "Teplota klesla na " + Float.parseFloat(data) + " °C, " + "pod najnižšiu možnú teplotu na rastline " + userPlant.getPlant().getName();
                        notificationService.addNotification(LocalDate.now(), message, NotificationType.LOWTEMPERATURE, user);
                    }
                }
            }

            if(temperatureDao.findLatest(garden).getValue() < (Float.parseFloat(data)) ){
                for (UserPlant userPlant: garden.getPlants()) {
                    if(userPlant.getMaxTemperature() > temperatureDao.findLatest(garden).getValue() && userPlant.getMaxTemperature() < (Float.parseFloat(data)) ){
                        String message = "Teplota stúpla na " + Float.parseFloat(data) + " °C, " + "nad najvyššiu možnú teplotu na rastline " + userPlant.getPlant().getName();
                        notificationService.addNotification(LocalDate.now(), message, NotificationType.HIGHTEMPERATURE, user);
                    }
                }
            }
        }
        else {
            if(Float.parseFloat(data) > user.getHighTemperature() ){
                String message = "Teplota stúpla na "+ Float.parseFloat(data) + " °C, " +" nad požadovanú teplotu " + user.getHighTemperature() + " °C.";

                notificationService.addNotification(LocalDate.now(), message, NotificationType.HIGHTEMPERATURE, user);
            }

            if(Float.parseFloat(data) < user.getLowTemperature() ){
                String message = "Teplota klesla na " + Float.parseFloat(data) + " °C, " + " pod požadovanú teplotu " + user.getLowTemperature() + " °C.";

                notificationService.addNotification(LocalDate.now(), message, NotificationType.LOWTEMPERATURE, user);
            }

            for (UserPlant userPlant: garden.getPlants()) {
                if(Float.parseFloat(data) > userPlant.getMaxTemperature()){
                    String message = "Teplota stúpla na " + Float.parseFloat(data) + " °C, " + "nad najvyššiu možnú teplotu na rastline " + userPlant.getPlant().getName();
                    notificationService.addNotification(LocalDate.now(), message, NotificationType.HIGHTEMPERATURE, user);
                }
                else if(Float.parseFloat(data) < userPlant.getMinTemperature()){
                    String message = "Teplota klesla na " + Float.parseFloat(data) + " °C, " + "pod najnižšiu možnú teplotu na rastline " + userPlant.getPlant().getName();
                    notificationService.addNotification(LocalDate.now(), message, NotificationType.LOWTEMPERATURE, user);
                }
            }
        }

        temperatureDao.persist(new Temperature(LocalDateTime.now(), Float.parseFloat(data), garden));
    }

    @Transactional
    public void saveHumidity(String data, String key){
        humidityDao.persist(new Humidity(LocalDateTime.now(), Float.parseFloat(data), gardenDao.find(Long.parseLong(key.substring(8))) ));
    }

    @Transactional
    public void saveRain(String data, String key){
        boolean raining = false;
        if (data.equals("1")) raining = true;
        rainDao.persist(new Rain(LocalDateTime.now(), raining, gardenDao.find(Long.parseLong(key.substring(4))) ));
    }

    @Transactional
    public TemperatureDto getLatestTemperature(Long gardenId) throws NotAllowedException {
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId())  throw new NotAllowedException("Not your garden");

        return translateService.translateTemp(temperatureDao.findLatest(garden ));
    }

    @Transactional
    public HumidityDto getLatestHumidity(Long gardenId) throws NotAllowedException {
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId())  throw new NotAllowedException("Not your garden");

        return translateService.translateHumidity(humidityDao.findLatest(garden));
    }

    @Transactional
    public PressureDto getLatestPressure(Long gardenId) throws NotAllowedException {
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId())  throw new NotAllowedException("Not your garden");

        return translateService.translatePressure(pressureDao.findLatest(garden));
    }

    @Transactional
    public RainDto getLatestRain(Long gardenId) throws NotAllowedException {
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId())  throw new NotAllowedException("Not your garden");

        return translateService.translateRain(rainDao.findLatest(garden));
    }


    /**
     * Method sets time of measure to ESP
     * @param minutes
     */
    @Transactional
    public void setMeassureMinutes(String minutes){
        client.setMeassureMinutes(minutes);
    }

    @Transactional
    public void saveSoil(String data, String key) throws NotFoundException {
        soilDao.persist(new Soil(LocalDateTime.now(), Float.parseFloat(data), gardenDao.find(Long.parseLong(key.substring(4))) ));
    }

    @Transactional
    public SoilDto getLatestSoil(Long gardenId) throws NotAllowedException {
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        return translateService.translateSoil(soilDao.findLatest(garden));
    }

    /**
     * Method gets 5 soil values of garden history
     * @param gardenId - garden id
     * @return List<SoilDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<SoilDto> getShortHistorySoil(Long gardenId) throws NotAllowedException {
        List<SoilDto> soilDtos = new ArrayList<>();
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Soil soil: soilDao.findHistoryOfGarden(garden) ) {
            soilDtos.add(translateService.translateSoil(soil));
        }

        if(soilDtos.size()>1) return soilDtos.subList(1,soilDtos.size());
        else return new ArrayList<>();
    }

    /**
     * Method gets 5 rain values of garden history
     * @param gardenId - garden id
     * @return List<RainDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<RainDto> getShortHistoryRain(Long gardenId) throws NotAllowedException {
        List<RainDto> rainDtos = new ArrayList<>();
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Rain rain: rainDao.findHistoryOfGarden(garden) ) {
           rainDtos.add(translateService.translateRain(rain));
        }

        if(rainDtos.size()>1) return rainDtos.subList(1,rainDtos.size());
        else return new ArrayList<>();
    }

    /**
     * Method gets 5 humidity values of garden history
     * @param gardenId - garden id
     * @return List<HumidityDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<HumidityDto> getShortHistoryHumidity(Long gardenId) throws NotAllowedException {
        List<HumidityDto> humidityDtos = new ArrayList<>();
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Humidity humidity: humidityDao.findHistoryOfGarden(garden) ) {
            humidityDtos.add(translateService.translateHumidity(humidity));
        }
        if(humidityDtos.size()>1) return humidityDtos.subList(1,humidityDtos.size());
        else return new ArrayList<>();
    }

    /**
     * Method gets 5 pressure values of garden history
     * @param gardenId - garden id
     * @return List<PressureDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<PressureDto> getShortHistoryPressure(Long gardenId) throws NotAllowedException {
        List<PressureDto> pressureDtos = new ArrayList<>();
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Pressure pressure: pressureDao.findHistoryOfGarden(garden) ) {
             pressureDtos.add(translateService.translatePressure(pressure));
        }

        if(pressureDtos.size()>1) return pressureDtos.subList(1,pressureDtos.size());
        else return new ArrayList<>();
    }

    /**
     * Method gets 5 temperature values of garden history
     * @param gardenId - garden id
     * @return List<TemperatureDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<TemperatureDto> getShortHistoryTemperature(Long gardenId) throws NotAllowedException {
        List<TemperatureDto> temperatureDtos = new ArrayList<>();
        Garden garden = gardenDao.find(gardenId);
        User user = SecurityUtils.getCurrentUser();
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Temperature temperature: temperatureDao.findHistoryOfGarden(garden) ) {
            temperatureDtos.add(translateService.translateTemp(temperature));
        }

        if(temperatureDtos.size()>1) return temperatureDtos.subList(1,temperatureDtos.size());
        else return new ArrayList<>();
    }

    /**
     * Method gets all soil history of garden
     * @param gardenName - garden name
     * @return List<SoilDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<SoilDto> getHistorySoil(String gardenName) throws NotAllowedException {
        List<SoilDto> soilDtos = new ArrayList<>();
        User user = SecurityUtils.getCurrentUser();
        Garden garden = gardenDao.findByName(gardenName, user);
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Soil soil: soilDao.findHistoryOfGarden(garden) ) {
            soilDtos.add(translateService.translateSoil(soil));
        }

        return soilDtos;
    }

    /**
     * Method gets all humidity history of garden
     * @param gardenName - garden name
     * @return List<HumidityDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<HumidityDto> getHistoryHumidity(String gardenName) throws NotAllowedException {
        List<HumidityDto> humidityDtos = new ArrayList<>();
        User user = SecurityUtils.getCurrentUser();
        Garden garden = gardenDao.findByName(gardenName, user);
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Humidity humidity: humidityDao.findHistoryOfGarden(garden) ) {
            humidityDtos.add(translateService.translateHumidity(humidity));
        }

        return humidityDtos;
    }

    /**
     * Method gets all pressure history of garden
     * @param gardenName - garden name
     * @return List<PressureDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<PressureDto> getHistoryPressure(String gardenName) throws NotAllowedException {
        List<PressureDto> pressureDtos = new ArrayList<>();
        User user = SecurityUtils.getCurrentUser();
        Garden garden = gardenDao.findByName(gardenName, user);
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Pressure pressure: pressureDao.findHistoryOfGarden(garden) ) {
            pressureDtos.add(translateService.translatePressure(pressure));
        }

        return pressureDtos;
    }

    /**
     * Method gets all temperature history of garden
     * @param gardenName - garden name
     * @return List<TemperatureDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<TemperatureDto> getHistoryTemperature(String gardenName) throws NotAllowedException {
        List<TemperatureDto> temperatureDtos = new ArrayList<>();
        User user = SecurityUtils.getCurrentUser();
        Garden garden = gardenDao.findByName(gardenName, user);
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Temperature temperature: temperatureDao.findHistoryOfGarden(garden) ) {
            temperatureDtos.add(translateService.translateTemp(temperature));
        }
        return temperatureDtos;
    }


    /**
     * Method gets all rain history of garden
     * @param gardenName - garden name
     * @return  List<RainDto>
     * @throws NotAllowedException
     */
    @Transactional
    public List<RainDto> getHistoryRain(String gardenName) throws NotAllowedException {
        List<RainDto> rainDtos = new ArrayList<>();
        User user = SecurityUtils.getCurrentUser();
        Garden garden = gardenDao.findByName(gardenName, user);
        if(garden.getUser().getId() != user.getId()) throw new NotAllowedException("Not your garden");

        for (Rain rain: rainDao.findHistoryOfGarden(garden) ) {
            rainDtos.add(translateService.translateRain(rain));
        }
        return rainDtos;
    }
}
