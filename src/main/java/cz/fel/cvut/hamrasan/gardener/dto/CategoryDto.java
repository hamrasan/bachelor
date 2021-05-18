package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private List<String> subcategoryNames;
    private List<Long> subcategoryIds;


    public CategoryDto(@NotNull(message = "Id cannot be blank") Long id, @NotBlank(message = "Name cannot be blank") String name,
                       List<String> subcategoryNames, List<Long> subcategoryIds) {

        this.id = id;
        this.name = name;
        this.subcategoryNames = subcategoryNames;
        this.subcategoryIds = subcategoryIds;
    }


    public CategoryDto() {
        this.subcategoryIds = new ArrayList<Long>();
        this.subcategoryNames = new ArrayList<String>();
    }


    public List<String> getSubcategoryNames() {

        return subcategoryNames;
    }


    public void setSubcategoryNames(List<String> subcategoryNames) {

        this.subcategoryNames = subcategoryNames;
    }


    public List<Long> getSubcategoryIds() {

        return subcategoryIds;
    }


    public void setSubcategoryIds(List<Long> subcategoryIds) {

        this.subcategoryIds = subcategoryIds;
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
