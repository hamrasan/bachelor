package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_SUBCATEGORY")
public class Subcategory extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, length = 250)
    @Size(max = 250, min = 1, message = "Name is in incorrect format.")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @ManyToOne
    private PlantCategory category;

    @OneToMany(mappedBy = "subcategory")
    private List<Plant> plantList;


    public Subcategory(@Size(max = 250, min = 1, message = "Name is in incorrect format.") @NotBlank(message = "Name cannot be blank") String name,
                       PlantCategory category, List<Plant> plantList) {

        this.name = name;
        this.category = category;
        this.plantList = plantList;
    }


    public Subcategory() {
        this.plantList = new ArrayList<Plant>();
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    public PlantCategory getCategory() {

        return category;
    }


    public void setCategory(PlantCategory category) {

        this.category = category;
    }


    public List<Plant> getPlantList() {

        return plantList;
    }


    public void setPlantList(List<Plant> plantList) {

        this.plantList = plantList;
    }

}
