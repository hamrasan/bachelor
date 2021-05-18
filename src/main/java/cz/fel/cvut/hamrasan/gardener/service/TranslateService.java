package cz.fel.cvut.hamrasan.gardener.service;

import cz.fel.cvut.hamrasan.gardener.dto.*;
import cz.fel.cvut.hamrasan.gardener.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class TranslateService {

    private final static Logger LOGGER = Logger.getLogger(ScheduleService.class.getName());

    @Transactional
    public UserDto translateUser(User user) {
        Objects.requireNonNull(user);

        List<GardenDto> gardenDtos = new ArrayList<GardenDto>();
        List<Garden> gardens = user.getGardens();


        if (gardens.size() > 0){
            gardens.forEach(garden-> gardenDtos.add(translateGarden(garden)));
        }

        return new UserDto(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(), gardenDtos, user.getGender());
    }

    @Transactional
    public PlantDto translateUserPlant(UserPlant plant){
        Objects.requireNonNull(plant);
        Long gardenDto = plant.getGarden().getId();
        Plant plant1 = plant.getPlant();


        return new PlantDto(plant.getId(), plant1.getName(), plant1.getPicture(), plant.getMinTemperature(),
                plant.getMaxTemperature(), plant.getDateOfPlant(), plant.getSeason(), translateSubCategory(plant1.getSubcategory()), gardenDto );
    }


    @Transactional
    public PlantWithoutDateDto translatePlant(Plant plant){
        Objects.requireNonNull(plant);

        return new PlantWithoutDateDto(plant.getId(), plant.getName(), plant.getPicture(), plant.getMinTemperature(),
                plant.getMaxTemperature(), plant.getSeason(), translateSubCategory(plant.getSubcategory()) );
    }

    @Transactional
    public SubcategoryDto translateSubCategory(Subcategory subcategory) {
        Objects.requireNonNull(subcategory);
        List<Long> plants = new ArrayList<Long>();

        for (Plant plant : subcategory.getPlantList()) {
            plants.add(plant.getId());
        }

        return new SubcategoryDto(subcategory.getId(), subcategory.getName(), translateCategory(subcategory.getCategory()), plants);
    }


    @Transactional
    public GardenDto translateGarden(Garden garden){
        Objects.requireNonNull(garden);
        List<TemperatureDto> temperatureDtos = new ArrayList<>();
        List<PressureDto> pressureDtos = new ArrayList<>();
        List<HumidityDto> humidityDtos = new ArrayList<>();
        List<PlantDto> plantDtos = new ArrayList<>();

        List<Temperature> temperatures = garden.getTemperatures();
        List<Humidity> humidities = garden.getHumidities();
        List<Pressure> pressures = garden.getPressures();
        List<UserPlant> plants = garden.getPlants();

        if (temperatures.size() > 0){
            temperatures.forEach(temperature -> temperatureDtos.add(translateTemp(temperature)));
        }

        if (humidities.size() > 0){
            humidities.forEach(humidity -> humidityDtos.add(translateHumidity(humidity)));
        }

        if (pressures.size() > 0){
            pressures.forEach(pressure -> pressureDtos.add(translatePressure(pressure)));
        }

        if (plants.size() > 0){
            plants.forEach(plant -> plantDtos.add(translateUserPlant(plant)));
        }

        return new GardenDto(garden.getId(), garden.getName(), garden.getLocation(), temperatureDtos, humidityDtos, pressureDtos,
                garden.getUser().getId(), plantDtos);
    }

    @Transactional
    public PlantCategoryDto translatePlantCategory(PlantCategory plantCategory){
        Objects.requireNonNull(plantCategory);
        return new PlantCategoryDto(plantCategory.getId(), plantCategory.getName());
    }

    @Transactional
    public TemperatureDto translateTemp(Temperature temperature){
        try{
            Objects.requireNonNull(temperature);
            return new TemperatureDto(temperature.getId(),temperature.getDate(), temperature.getValue(), temperature.getGarden().getId());
        }
         catch (NullPointerException e){
            LOGGER.info("Temperature is not available");
            return null;
        }
    }

    @Transactional
    public PressureDto translatePressure(Pressure pressure){
        try{
            Objects.requireNonNull(pressure);
            return new PressureDto(pressure.getId(),pressure.getDate(), pressure.getValue(), pressure.getGarden().getId());
        }
        catch (NullPointerException e){
            LOGGER.info("Pressure is not available");
            return null;
        }
    }

    @Transactional
    public HumidityDto translateHumidity(Humidity humidity){
        try{
            Objects.requireNonNull(humidity);
            return new HumidityDto(humidity.getId(),humidity.getDate(), humidity.getValue(), humidity.getGarden().getId());
        }
         catch (NullPointerException e){
            LOGGER.info("Humidity is not available");
            return null;
        }
    }

    @Transactional
    public CategoryDto translateCategory(PlantCategory plantCategory){
        Objects.requireNonNull(plantCategory);
        List<String> subcategoryNames = new ArrayList<String>();
        List<Long> subcategoryIds = new ArrayList<Long>();


        if(plantCategory.getSubcategories().size() > 0){
            for (Subcategory subcategory: plantCategory.getSubcategories()) {
                subcategoryIds.add(subcategory.getId());
                subcategoryNames.add(subcategory.getName());
            }
        }
        return new CategoryDto(plantCategory.getId(), plantCategory.getName(), subcategoryNames, subcategoryIds);
    }

    @Transactional
    public RainDto translateRain(Rain rain){
        try{
            Objects.requireNonNull(rain);
            return new RainDto(rain.getId(), rain.getDate(), rain.getRaining(), rain.getGarden().getId());
        }
        catch (NullPointerException e){
            LOGGER.info("Rain is not available");
            return null;
        }
    }

    @Transactional
    public ValveDto translateValve(Valve valve) {
        Objects.requireNonNull(valve);
        List<GardenDto> gardensDto = new ArrayList<GardenDto>();

        for (Garden garden : valve.getGardens()) {
            gardensDto.add(translateGarden(garden));
        }
        return new ValveDto(valve.getId(), valve.getName(), valve.getPicture(), gardensDto);
    }

    @Transactional
    public ValveWithScheduleDto translateValveWithSchedule(Valve valve) {
        Objects.requireNonNull(valve);
        List<GardenDto> gardensDto = new ArrayList<GardenDto>();
        List<ValveScheduleDto> valveScheduleDtos = new ArrayList<ValveScheduleDto>();

        for (Garden garden : valve.getGardens()) {
            gardensDto.add(translateGarden(garden));
        }

        for (ValveSchedule valveSchedule: valve.getValveScheduleList()) {
            valveScheduleDtos.add(translateValveSchedule(valveSchedule));
        }
        return new ValveWithScheduleDto(valve.getId(), valve.getName(), valve.getPicture(), gardensDto, valveScheduleDtos);
    }


    @Transactional
    public ValveScheduleDto translateValveSchedule(ValveSchedule valveSchedule) {
        Objects.requireNonNull(valveSchedule);

        return new ValveScheduleDto(valveSchedule.getId(), valveSchedule.getHour(), valveSchedule.getMinutes(), valveSchedule.getLength(), valveSchedule.getDays(), valveSchedule.getValve().getId());
    }

    @Transactional
    public SoilDto translateSoil(Soil soil) {
        try{
            Objects.requireNonNull(soil);
            return new SoilDto(soil.getId(), soil.getDate(), soil.getValue(), soil.getGarden().getId());
        }
        catch (NullPointerException e){
            LOGGER.info("Soil is not available");
            return null;
        }
    }

    @Transactional
    public NotificationDto translateNotification(Notification notification) {
        Objects.requireNonNull(notification);
        return new NotificationDto(notification.getId(), notification.getDate(), notification.getMessage(), notification.getType(), notification.isSeen(), notification.getUser().getId());
    }
}
