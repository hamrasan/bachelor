package cz.fel.cvut.hamrasan.gardener.rest;

import cz.fel.cvut.hamrasan.gardener.amqp.rpc.Tut6Client;
import cz.fel.cvut.hamrasan.gardener.dto.*;
import cz.fel.cvut.hamrasan.gardener.exceptions.NotAllowedException;
import cz.fel.cvut.hamrasan.gardener.security.SecurityConstants;
import cz.fel.cvut.hamrasan.gardener.security.SecurityUtils;
import cz.fel.cvut.hamrasan.gardener.service.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@CrossOrigin(origins = SecurityConstants.ORIGIN_URI, allowCredentials="true")
public class SensorsController {

    private SensorsService sensorsService;

    @Autowired
    public SensorsController(SensorsService sensorsService) {

        this.sensorsService = sensorsService;
    }

    @GetMapping(value = "/temperature/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public TemperatureDto getTemperature(@PathVariable Long gardenId) throws NotAllowedException {

        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        return sensorsService.getLatestTemperature(gardenId);
    }

    @GetMapping(value = "/humidity/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public HumidityDto getHumidity(@PathVariable Long gardenId) throws NotAllowedException {

        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        return sensorsService.getLatestHumidity(gardenId);
    }

    @GetMapping(value = "/pressure/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public PressureDto getPressure(@PathVariable Long gardenId) throws NotAllowedException {

        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        return sensorsService.getLatestPressure(gardenId);
    }

    @GetMapping(value = "/request/{minutes}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void setRequestData(@PathVariable String minutes) {
        sensorsService.setMeassureMinutes(minutes);
    }

    @GetMapping(value = "/rain/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public RainDto getRain(@PathVariable Long gardenId) throws NotAllowedException {

        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        return sensorsService.getLatestRain(gardenId);
    }

    @GetMapping(value = "/soil/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public SoilDto getSoil(@PathVariable Long gardenId) throws NotAllowedException {

        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        return sensorsService.getLatestSoil(gardenId);
    }

    @GetMapping(value = "/history/rain/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RainDto> getShortHistoryRain(@PathVariable Long gardenId) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getShortHistoryRain(gardenId);
    }

    @GetMapping(value = "/history_all/rain/{gardenName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RainDto> getHistoryRain(@PathVariable String gardenName) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getHistoryRain(gardenName);
    }

    @GetMapping(value = "/history/temperature/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TemperatureDto> getShortHistoryTemperature(@PathVariable Long gardenId) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getShortHistoryTemperature(gardenId);
    }

    @GetMapping(value = "/history_all/temperature/{gardenName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TemperatureDto> getHistoryTemperature(@PathVariable String gardenName) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");
        System.out.println("hello");
        return sensorsService.getHistoryTemperature(gardenName);
    }

    @GetMapping(value = "/history/pressure/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PressureDto> getShortHistoryPressure(@PathVariable Long gardenId) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getShortHistoryPressure(gardenId);
    }

    @GetMapping(value = "/history_all/pressure/{gardenName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PressureDto> getHistoryPressure(@PathVariable String gardenName) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getHistoryPressure(gardenName);
    }


    @GetMapping(value = "/history/humidity/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HumidityDto> getShortHistoryHumidity(@PathVariable Long gardenId) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getShortHistoryHumidity(gardenId);
    }

    @GetMapping(value = "/history_all/humidity/{gardenName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HumidityDto> getHistoryHumidity(@PathVariable String gardenName) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getHistoryHumidity(gardenName);
    }

    @GetMapping(value = "/history/soil/{gardenId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SoilDto> getShortHistorySoil(@PathVariable Long gardenId) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getShortHistorySoil(gardenId);
    }

    @GetMapping(value = "/history_all/soil/{gardenName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SoilDto> getHistorySoil(@PathVariable String gardenName) throws NotAllowedException {
        if (SecurityUtils.isAuthenticatedAnonymously()) throw new NotAllowedException("Login first");

        return sensorsService.getHistorySoil(gardenName);
    }
}
