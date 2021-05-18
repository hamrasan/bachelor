package cz.fel.cvut.hamrasan.gardener.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_PLANT_CATEGORY")
@NamedQueries({
        @NamedQuery(name = "PlantCategory.findByName", query = "SELECT p FROM PlantCategory p WHERE p.name = :name")
})
public class PlantCategory extends AbstractEntity {

    @Basic(optional = false)
    @Column(unique = true, nullable = false, length = 50)
    @Size(max = 50, min = 1, message = "Name is in incorrect format.")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Subcategory> subcategories;



    public PlantCategory(@Size(max = 50, min = 1, message = "Name is in incorrect format.")
                         @NotBlank(message = "Name cannot be blank") String name, List<Subcategory> subcategories) {
        this.name = name;
        this.subcategories = subcategories;
    }


    public PlantCategory() {
        this.subcategories = new ArrayList<Subcategory>();
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Subcategory> getSubcategories() {

        return subcategories;
    }


    public void setSubcategories(List<Subcategory> subcategories) {

        this.subcategories = subcategories;
    }
}
