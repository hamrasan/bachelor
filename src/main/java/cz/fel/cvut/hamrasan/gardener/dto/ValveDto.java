package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ValveDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    private String name;

    private String picture;

    private List<GardenDto> gardens;


    public ValveDto(@NotNull(message = "Id cannot be blank") Long id, String name, String picture, List<GardenDto> gardens) {

        this.id = id;
        this.name = name;
        this.picture = picture;
        this.gardens = gardens;
    }


    public ValveDto() {
        gardens = new ArrayList<GardenDto>();
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
}
