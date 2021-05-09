package cz.fel.cvut.hamrasan.gardener.service;

import cz.fel.cvut.hamrasan.gardener.dao.NotificationDao;
import cz.fel.cvut.hamrasan.gardener.dao.UserDao;
import cz.fel.cvut.hamrasan.gardener.dto.NotificationDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.model.Notification;
import cz.fel.cvut.hamrasan.gardener.model.NotificationType;
import cz.fel.cvut.hamrasan.gardener.model.User;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class NotificationService {

    private UserDao userDao;
    private NotificationDao notificationDao;
    private TranslateService translateService;

    @Autowired
    public NotificationService(UserDao userDao, NotificationDao notificationDao, TranslateService translateService) {

        this.userDao = userDao;
        this.notificationDao = notificationDao;
        this.translateService = translateService;

    }

    @Transactional
    public List<NotificationDto> getNotifications() throws NotAllowedException {
        if(SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        User user = userDao.find(SecurityUtils.getCurrentUser().getId());
        List<Notification> notifications = notificationDao.notSeenOfUser(user);
        List<NotificationDto> notificationDtos = new ArrayList<>();

        for (Notification notification: notifications) {
            notificationDtos.add(translateService.translateNotification(notification));
        }
        return notificationDtos;
    }

    @Transactional
    public void addNotification(LocalDate date, String message, NotificationType type, User user){
        Notification notification = new Notification(date, message, type, user);
        notificationDao.persist(notification);
    }

    @Transactional
    public void setSeenNotification(Long id) throws NotFoundException {
        Notification notification = notificationDao.find(id);
        if(notification==null) throw new NotFoundException("Notification not found");
        notification.setSeen(true);
        notificationDao.update(notification);
    }

//    private List<String> getNotifications(Long id) throws NotFoundException {
//        User user = userDao.find(id);
//        if (user == null) throw new NotFoundException("User not found");
//        return cache.get(user.getId());
//        return null;
//    }

//    @Transactional
//    public void createListNotifications(Long id) {
//        cache.put(id, new ArrayList<>());
//    }

//    @Transactional
//    public void addNotification(Long id, String message) throws NotFoundException {
//        User user = userDao.find(id);
//        if (user == null) throw new NotFoundException("User not found");
//        if(!cache.containsKey(user.getId())) throw new NotFoundException("User in cache not found");
//        List<String> nofications = getNotifications(user.getId());
//        nofications.add(message);
//        cache.replace(user.getId(), nofications);
//    }
}
