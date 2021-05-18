package cz.fel.cvut.hamrasan.gardener.rest;

import cz.fel.cvut.hamrasan.gardener.dto.RequestWrapper;
import cz.fel.cvut.hamrasan.gardener.dto.UserDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.BadPassword;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.exceptions.UnauthorizedException;
import cz.fel.cvut.hamrasan.gardener.security.SecurityConstants;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import cz.fel.cvut.hamrasan.gardener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = SecurityConstants.ORIGIN_URI, allowCredentials="true", allowedHeaders = "*")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    public UserDto getCurrent() throws UnauthorizedException {
        return userService.getCurrentUser();
    }

    @GetMapping(value = "/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE )
    public UserDto getUserByID(@PathVariable Long id) {
        return userService.find(id);
    }

    @GetMapping(value = "/lowTemp" ,produces = MediaType.APPLICATION_JSON_VALUE )
    public double getUserLowTemperature() throws NotAllowedException {
        if(SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        return userService.getUserLowTemperature();
    }

    @GetMapping(value = "/highTemp" ,produces = MediaType.APPLICATION_JSON_VALUE )
    public double getUserHighTemperature() throws NotAllowedException {
        if(SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        return userService.getUserHighTemperature();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody RequestWrapper requestWrapper) throws BadPassword, NotAllowedException {
        userService.createUser(requestWrapper.getUser(), requestWrapper.getPassword_control(), requestWrapper.getGender());
    }

    @PostMapping(value = "/notifications", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setupTempValues(@RequestBody HashMap<String, Float> hashMap) throws NotAllowedException {
        if(SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        else userService.setupTempValues(hashMap.get("lowTemp"), hashMap.get("highTemp"));
    }
}
