package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "APP_PLANT")
@NamedQueries({
        @NamedQuery(name = "Plant.findByName", query = "SELECT p FROM Plant p WHERE p.name = :name")
})
public class Plant extends AbstractPlant{

    @Basic(optional = false)
    @Column(unique = true, nullable = false, length = 30)
    @Size(max = 30, min = 1, message = "Name is in incorrect format.")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String picture;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    @OneToMany
    private List<UserPlant> userPlants;


    public Plant(@Size(max = 30, min = 1, message = "Name is in incorrect format.") @NotBlank(message = "Name cannot be blank") String name, String picture, double minTemperature,
                 double maxTemperature, String season, Subcategory subcategory) {

        this.name = name;
        this.picture = picture;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.season = season;
        this.subcategory = subcategory;
        this.userPlants = new ArrayList<UserPlant>();
    }


    public Plant() {
        this.userPlants = new ArrayList<UserPlant>();
    }


    @Override
    public String toString() {

        return "Plant{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", season='" + season + '\'' +
                ", subcategory=" + subcategory +
                ", userPlants=" + userPlants +
                '}';
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


    public double getMinTemperature() {

        return minTemperature;
    }


    public void setMinTemperature(double minTemperature) {

        this.minTemperature = minTemperature;
    }


    public double getMaxTemperature() {

        return maxTemperature;
    }


    public void setMaxTemperature(double maxTemperature) {

        this.maxTemperature = maxTemperature;
    }

    public String getSeason() {

        return season;
    }


    public void setSeason(String season) {

        this.season = season;
    }


    public Subcategory getSubcategory() {

        return subcategory;
    }


    public void setSubcategory(Subcategory subcategory ) {

        this.subcategory = subcategory;
    }


    public List<UserPlant> getUserPlants() {

        return userPlants;
    }


    public void setUserPlants(List<UserPlant> userPlants) {

        this.userPlants = userPlants;
    }
}
