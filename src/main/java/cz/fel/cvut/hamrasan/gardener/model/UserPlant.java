package cz.fel.cvut.hamrasan.gardener.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_USER_PLANT")
@NamedQueries({
        @NamedQuery(name = "UserPlant.findByName", query = "SELECT p FROM UserPlant p WHERE p.plant.name = :name")
})
public class UserPlant extends AbstractPlant {

    @Basic(optional = false)
    @Column(nullable = false)
    @PastOrPresent
    private LocalDate dateOfPlant;

    @ManyToOne
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;


    public UserPlant(@PastOrPresent LocalDate dateOfPlant, double minTemperature, double maxTemperature, String season, Plant plant, Garden garden) {

        this.dateOfPlant = dateOfPlant;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.season = season;
        this.plant = plant;
        this.garden = garden;
    }


    public UserPlant(){
    }


    public LocalDate getDateOfPlant() {

        return dateOfPlant;
    }


    public void setDateOfPlant(LocalDate dateOfPlant) {

        this.dateOfPlant = dateOfPlant;
    }


    public Plant getPlant() {

        return plant;
    }


    public void setPlant(Plant plant) {

        this.plant = plant;
    }


    public Garden getGarden() {

        return garden;
    }


    public void setGarden(Garden garden) {

        this.garden = garden;
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


    @Override
    public String toString() {

        return "UserPlant{" +
                "dateOfPlant=" + dateOfPlant +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", season='" + season + '\'' +
                ", plant=" + plant +
                ", garden=" + garden +
                '}';
    }
}
