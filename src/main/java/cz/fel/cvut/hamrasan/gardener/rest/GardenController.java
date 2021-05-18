package cz.fel.cvut.hamrasan.gardener.rest;

import cz.fel.cvut.hamrasan.gardener.dto.GardenDto;
import cz.fel.cvut.hamrasan.gardener.security.SecurityConstants;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import cz.fel.cvut.hamrasan.gardener.service.GardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/garden")
@CrossOrigin(origins = SecurityConstants.ORIGIN_URI, allowCredentials="true")
public class GardenController {

    private GardenService gardenService;

    @Autowired
    public GardenController(GardenService gardenService) {

        this.gardenService = gardenService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<GardenDto> getAll() {
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            return gardenService.findAllOfUser();
        }
        return new ArrayList<>();
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE )
    public void create(@RequestBody HashMap<String, String> hashMap) {

        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            gardenService.create(hashMap.get("name"), hashMap.get("location"));
        }
    }
}
