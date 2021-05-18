package cz.fel.cvut.hamrasan.gardener.rest;

import cz.fel.cvut.hamrasan.gardener.dto.RequestWrapperSchedule;
import cz.fel.cvut.hamrasan.gardener.dto.ValveDto;
import cz.fel.cvut.hamrasan.gardener.dto.ValveWithScheduleDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.AlreadyExistsException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.security.SecurityConstants;
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
@CrossOrigin(origins = SecurityConstants.ORIGIN_URI, allowCredentials="true")
public class ValveController {

    private ValveService valveService;

    @Autowired
    public ValveController(ValveService valveService) {
        this.valveService = valveService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ValveDto> getAllOfUserRaw() throws NotFoundException {
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            return valveService.getAllOfUserRaw();
        }
        else { return null; }
    }

    @GetMapping(value = "/allsc", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ValveWithScheduleDto> getAllOfUserFull() throws NotFoundException {
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            return valveService.getAllOfUserFull();
        }
        else { return null; }
    }

    @PostMapping(value = "/create/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createValve(@PathVariable String name) throws NotAllowedException, NoSuchAlgorithmException, InvalidKeyException, IOException, AlreadyExistsException, NotFoundException {

//        @RequestBody HashMap<String,List<Integer>> hashMap
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
//            List<Integer> ids = hashMap.get("gardens");
            valveService.createValve(name);
        }
    }

    @PostMapping(value = "add_gardens/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addGardensToValve(@PathVariable Long id, @RequestBody HashMap<String, List<Long>> hashMap){

        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            List<Long> gardens = hashMap.get("gardens");
            valveService.updateGardensToValve(id, gardens);
        }
    }

    @PostMapping(value = "/immediately/{valveName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void valvingImmediately(@PathVariable String valveName, @RequestBody HashMap<String, Integer> hashMap ) throws NoSuchAlgorithmException, InvalidKeyException, IOException, NotFoundException, NotAllowedException {
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            valveService.valvingImmediately(valveName, hashMap.get("length"));
        }
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
    public void moveValve(@RequestBody HashMap<String,String> request) throws NoSuchAlgorithmException, InvalidKeyException, IOException, NotAllowedException, NotFoundException {
        valveService.moveValve(request.get("deviceId"), request.get("onOffValve"));
    }

}
