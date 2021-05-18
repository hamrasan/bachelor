package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.Basic;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class PlantCategoryDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;



    public PlantCategoryDto(@NotNull(message = "Id cannot be blank") Long id,
                            @NotBlank(message = "Name cannot be blank") String name) {

        this.id = id;
        this.name = name;
    }


    public PlantCategoryDto() {

    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }
}

