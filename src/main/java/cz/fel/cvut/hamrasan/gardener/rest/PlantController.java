package cz.fel.cvut.hamrasan.gardener.rest;

import cz.fel.cvut.hamrasan.gardener.dto.PlantDto;
import cz.fel.cvut.hamrasan.gardener.dto.PlantWithoutDateDto;
import cz.fel.cvut.hamrasan.gardener.exceptions.MissingVariableException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotFoundException;
import cz.fel.cvut.hamrasan.gardener.model.Garden;
import cz.fel.cvut.hamrasan.gardener.model.Plant;
import cz.fel.cvut.hamrasan.gardener.model.UserPlant;
import cz.fel.cvut.hamrasan.gardener.security.SecurityConstants;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import cz.fel.cvut.hamrasan.gardener.service.PlantService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/plants")
@CrossOrigin(origins = SecurityConstants.ORIGIN_URI, allowCredentials="true")
public class PlantController {

    private PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<PlantWithoutDateDto> getAll() {
        return plantService.findAll();
    }


    @GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE )
    public List<PlantDto> getAllOfUSer(){
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            return plantService.getUserPlants();
        }
        else { return null; }
    }

    @GetMapping(value = "/garden/{gardenId}", produces =  MediaType.APPLICATION_JSON_VALUE )
    public List<PlantDto> getAllOfGarden(@PathVariable Long gardenId) throws NotAllowedException, NotFoundException {
        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            return plantService.getGardenPlants(gardenId);
        }
        else throw new NotAllowedException("Not allowed access");
    }

    @GetMapping(value = "/all/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public PlantWithoutDateDto getPlant(@PathVariable Long id) {
        return plantService.find(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public PlantDto getUserPlant(@PathVariable Long id) {

        if(!SecurityUtils.isAuthenticatedAnonymously()) {
            return plantService.findUserPlant(id);
        }
        else { return null; }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPlant(@RequestBody HashMap<String,String> hashMap) throws MissingVariableException, NotAllowedException {

        LocalDate date = LocalDate.parse(hashMap.get("date").substring(0,10));
        Long plantId = Long.parseLong(hashMap.get("plant"));
        String gardenName = hashMap.get("garden");
        float minTemperature = Float.parseFloat(hashMap.get("minTemperature"));
        float maxTemperature = Float.parseFloat(hashMap.get("maxTemperature"));
        String season = hashMap.get("season");

        plantService.create(date, minTemperature, maxTemperature, season, plantId, gardenName);
    }

    @GetMapping(value = "/{category}/{subcategory}", produces = MediaType.APPLICATION_JSON_VALUE )
    public List<PlantWithoutDateDto> getAllOfSubcategory(@PathVariable String category, @PathVariable String subcategory) throws NotFoundException {
        return plantService.findAllOfSubcategory(category, subcategory);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePlant(@RequestBody HashMap<String,String> hashMap) throws NotAllowedException {
        if(SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        plantService.updatePlant(Long.parseLong(hashMap.get("id")), Double.parseDouble(hashMap.get("minTemperature")),
                Double.parseDouble(hashMap.get("maxTemperature")), hashMap.get("season"));
    }

    @DeleteMapping(value = "/{id}")
    public void deletePlant(@PathVariable Long id){
        plantService.deletePlant(id);

//        if (!isRemoved) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
