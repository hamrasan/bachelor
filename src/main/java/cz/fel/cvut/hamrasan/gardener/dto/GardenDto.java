package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class GardenDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @Size(max = 100, min = 1, message = "Name is in incorrect format.")
    @NotNull(message = "Name cannot be blank")
    private String name;

    private String location;

    private List<TemperatureDto> temperatureDtos;
    private List<HumidityDto> humidityDtos;
    private List<PressureDto> pressureDtos;

    private Long user;
    private List<PlantDto> plantDtos;


    public GardenDto(@NotNull(message = "Id cannot be blank") Long id, @Size(max = 100, min = 1, message = "Name is in incorrect format.") @NotNull(message = "Name cannot be blank") String name,
                     String location, List<TemperatureDto> temperatureDtos,
                     List<HumidityDto> humidityDtos, List<PressureDto> pressureDtos, Long user, List<PlantDto> plantDtos) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.temperatureDtos = temperatureDtos;
        this.humidityDtos = humidityDtos;
        this.pressureDtos = pressureDtos;
        this.user = user;
        this.plantDtos = plantDtos;
    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    public String getLocation() {

        return location;
    }


    public void setLocation(String location) {

        this.location = location;
    }


    public List<TemperatureDto> getTemperatureDtos() {

        return temperatureDtos;
    }


    public void setTemperatureDtos(List<TemperatureDto> temperatureDtos) {

        this.temperatureDtos = temperatureDtos;
    }


    public List<HumidityDto> getHumidityDtos() {

        return humidityDtos;
    }


    public void setHumidityDtos(List<HumidityDto> humidityDtos) {

        this.humidityDtos = humidityDtos;
    }


    public List<PressureDto> getPressureDtos() {

        return pressureDtos;
    }


    public void setPressureDtos(List<PressureDto> pressureDtos) {

        this.pressureDtos = pressureDtos;
    }


    public Long getUser() {

        return user;
    }


    public void setUser(Long user) {

        this.user = user;
    }


    public List<PlantDto> getPlantDtos() {

        return plantDtos;
    }


    public void setPlantDtos(List<PlantDto> plantDtos) {

        this.plantDtos = plantDtos;
    }
}
