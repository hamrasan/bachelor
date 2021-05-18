package cz.fel.cvut.hamrasan.gardener.rest;

import cz.fel.cvut.hamrasan.gardener.dto.NotificationDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.security.SecurityConstants;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import cz.fel.cvut.hamrasan.gardener.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = SecurityConstants.ORIGIN_URI, allowCredentials="true")
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<NotificationDto> getAllOfUser() throws NotAllowedException {
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            return notificationService.getNotifications();
        }
        return new ArrayList<>();
    }

    @PostMapping(value = "/seen/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setSeenNotification(@PathVariable Long id) throws NotFoundException {
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            notificationService.setSeenNotification(id);
        }
    }
}
