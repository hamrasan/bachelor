package cz.fel.cvut.hamrasan.gardener.rest;

import cz.fel.cvut.hamrasan.gardener.dto.ValveDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import cz.fel.cvut.hamrasan.gardener.service.ValveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/valve")
public class ValveController {

    private ValveService valveService;

    @Autowired
    public ValveController(ValveService valveService) {
        this.valveService = valveService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ValveDto> getAllOfUser() {
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            return valveService.getAllOfUser();
        }
        else { return null; }
    }

    @GetMapping(value = "/config")
    public void configApi() throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        valveService.setupApi();
    }

    @GetMapping(value = "/refresh")
    public void refreshApi() throws NoSuchAlgorithmException, InvalidKeyException, IOException, NotAllowedException {
        valveService.refreshApiToken();
    }

    @PostMapping(value = "/move")
    public void moveValve(@RequestBody HashMap<String,String> request) throws NoSuchAlgorithmException, InvalidKeyException, IOException, NotAllowedException {
        valveService.moveValve(request.get("deviceId"), request.get("onOffValve"));
    }
}
