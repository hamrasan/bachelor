package cz.fel.cvut.hamrasan.gardener.dto;

import cz.fel.cvut.hamrasan.gardener.model.Gender;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @Size(max = 30, min = 1, message = "First name is in incorrect format.")
    @NotNull(message = "First name cannot be blank")
    private String firstName;

    @Basic(optional = false)
    @Size(max = 30, min = 1, message = "Last name is in incorrect format.")
    @NotNull(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email should be valid")
    @Basic(optional = false)
    @NotNull(message = "Email cannot be blank")
    private String email;

    @Basic(optional = false)
    @NotNull(message = "Gender cannot be blank")
    private Gender gender;

//    private List<PlantDto> plants;
    private List<GardenDto> gardens;


    public UserDto(@NotNull(message = "Id cannot be blank") Long id, @Size(max = 30, min = 1, message = "First name is in incorrect format.") @NotNull(message = "First name cannot be blank") String firstName,
                   @Size(max = 30, min = 1, message = "Last name is in incorrect format.") @NotNull(message = "Last name cannot be blank") String lastName,
                   @Email(message = "Email should be valid") @NotNull(message = "Email cannot be blank") String email, List<GardenDto> gardens, Gender gender) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gardens = gardens;
        this.gender = gender;
//        this.plants = plants;
    }


//    public UserDto() {
//        plants = new ArrayList<PlantDto>();
//    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public String getFirstName() {

        return firstName;
    }


    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }


    public String getLastName() {

        return lastName;
    }


    public void setLastName(String lastName) {

        this.lastName = lastName;
    }


    public String getEmail() {

        return email;
    }


    public void setEmail(String email) {

        this.email = email;
    }


//    public List<PlantDto> getPlants() {
//
//        return plants;
//    }
//
//
//    public void setPlants(List<PlantDto> plants) {
//
//        this.plants = plants;
//    }


    public List<GardenDto> getGardens() {

        return gardens;
    }


    public void setGardens(List<GardenDto> gardens) {

        this.gardens = gardens;
    }


    public Gender getGender() {

        return gender;
    }


    public void setGender(Gender gender) {

        this.gender = gender;
    }
}
