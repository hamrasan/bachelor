package cz.fel.cvut.hamrasan.gardener.service;

import cz.fel.cvut.hamrasan.gardener.dao.UserDao;
import cz.fel.cvut.hamrasan.gardener.dto.UserDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.BadPassword;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.exceptions.UnauthorizedException;
import cz.fel.cvut.hamrasan.gardener.model.Gender;
import cz.fel.cvut.hamrasan.gardener.model.User;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    UserDao userDao;
    TranslateService translateService;
    NotificationService notificationService;

    @Autowired
    public UserService(UserDao userDao, TranslateService translateService, NotificationService notificationService) {
        this.userDao = userDao;
        this.translateService = translateService;
        this.notificationService = notificationService;
    }

    @Transactional
    public List<UserDto> findAll() {
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : userDao.findAll()) {
            userDtos.add(translateService.translateUser(user));
        }
        return userDtos;
    }


    public UserDto find(Long id) {
        Objects.requireNonNull(id);
        return translateService.translateUser(userDao.find(id));
    }


    public UserDto find(String email) {
        Objects.requireNonNull(email);
        return translateService.translateUser(userDao.findByEmail(email));
    }

    @Transactional
    public void createUser(User user, String passwordAgain, int gender) throws BadPassword, NotAllowedException {
        Objects.requireNonNull(user);
        Gender userGender = Gender.MAN;
        if (!user.getPassword().equals(passwordAgain)) throw new BadPassword();
        if(userDao.findByEmail(user.getEmail()) != null) throw new NotAllowedException("Email already exists!");
        user.encodePassword();

        if(gender == 2) userGender = Gender.WOMAN;
        user.setGender(userGender);
        userDao.persist(user);
//        notificationService.createListNotifications(user.getId());
    }

    @Transactional(readOnly = true)
    public UserDto getCurrentUser() throws UnauthorizedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new UnauthorizedException();
        else return translateService.translateUser(SecurityUtils.getCurrentUser());
    }

    @Transactional
    public void setupTempValues(float lowTemp, float highTemp) {
        User user = userDao.find(SecurityUtils.getCurrentUser().getId());
        user.setHighTemperature(highTemp);
        user.setLowTemperature(lowTemp);
        userDao.update(user);
    }

    @Transactional
    public float getUserHighTemperature() {
        User user = userDao.find(SecurityUtils.getCurrentUser().getId());
        return user.getHighTemperature();
    }

    @Transactional
    public float getUserLowTemperature() {
        User user = userDao.find(SecurityUtils.getCurrentUser().getId());
        return user.getLowTemperature();
    }

}
