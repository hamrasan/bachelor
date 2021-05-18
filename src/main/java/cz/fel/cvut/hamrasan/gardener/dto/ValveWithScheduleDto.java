package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ValveWithScheduleDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    private String name;

    private String picture;

    private List<GardenDto> gardens;

    private List<ValveScheduleDto> valveScheduleDtos;


    public ValveWithScheduleDto(@NotNull(message = "Id cannot be blank") Long id, String name, String picture, List<GardenDto> gardens, List<ValveScheduleDto> valveScheduleDtos) {

        this.id = id;
        this.name = name;
        this.picture = picture;
        this.gardens = gardens;
        this.valveScheduleDtos = valveScheduleDtos;
    }


    public ValveWithScheduleDto() {
        this.valveScheduleDtos = new ArrayList<ValveScheduleDto>();
        this.gardens =  new ArrayList<GardenDto>();
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


    public String getPicture() {

        return picture;
    }


    public void setPicture(String picture) {

        this.picture = picture;
    }


    public List<GardenDto> getGardens() {

        return gardens;
    }


    public void setGardens(List<GardenDto> gardens) {

        this.gardens = gardens;
    }


    public List<ValveScheduleDto> getValveScheduleDtos() {

        return valveScheduleDtos;
    }


    public void setValveScheduleDtos(List<ValveScheduleDto> valveScheduleDtos) {

        this.valveScheduleDtos = valveScheduleDtos;
    }
}
