package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @Size(max = 30, min = 1, message = "Name is in incorrect format.")
    @NotNull(message = "Name cannot be blank")
    private String name;

    private String picture;

    @Basic(optional = false)
    private double minTemperature;

    @Basic(optional = false)
    private double maxTemperature;

    @Basic(optional = false)
    @PastOrPresent
    private LocalDate dateOfPlant;

    @Basic(optional = false)
    private String season;

    private SubcategoryDto subcategoryDto;

//    private List<Long> users;

    private Long garden;

    public PlantDto(){
    }



    public PlantDto(@NotNull(message = "Id cannot be blank") Long id, @Size(max = 30, min = 1, message = "Name is in incorrect format.") @NotNull(message = "Name cannot be blank") String name, String picture, double minTemperature,
                    double maxTemperature, @PastOrPresent LocalDate dateOfPlant, String season, SubcategoryDto subcategoryDto, Long garden) {

        this.id = id;
        this.name = name;
        this.picture = picture;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.dateOfPlant = dateOfPlant;
        this.season = season;
        this.subcategoryDto = subcategoryDto;
        this.garden = garden;
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


    public LocalDate getDateOfPlant() {

        return dateOfPlant;
    }


    public void setDateOfPlant(LocalDate dateOfPlant) {

        this.dateOfPlant = dateOfPlant;
    }


    public String getSeason() {

        return season;
    }


    public void setSeason(String season) {

        this.season = season;
    }


    public SubcategoryDto getSubcategoryDto() {

        return subcategoryDto;
    }


    public void setSubcategoryDto(SubcategoryDto subcategoryDto) {

        this.subcategoryDto = subcategoryDto;
    }


    //    public List<Long> getUsers() {
//
//        return users;
//    }
//
//
//    public void setUsers(List<Long> users) {
//
//        this.users = users;
//    }


    public Long getGarden() {

        return garden;
    }


    public void setGarden(Long garden) {

        this.garden = garden;
    }
}
