package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.Basic;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryDto {


    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private CategoryDto category;

    private List<Long> plants;

    public SubcategoryDto() {
        plants = new ArrayList<Long>();
    }


    public SubcategoryDto(@NotNull(message = "Id cannot be blank") Long id, @NotBlank(message = "Name cannot be blank") String name,
                          CategoryDto category, List<Long> plants) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.plants = plants;
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


    public CategoryDto getCategory() {

        return category;
    }


    public void setCategory(CategoryDto category) {

        this.category = category;
    }


    public List<Long> getPlants() {

        return plants;
    }


    public void setPlants(List<Long> plants) {

        this.plants = plants;
    }
}
