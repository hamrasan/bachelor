package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_GARDEN")
@NamedQueries({
        @NamedQuery(name = "Garden.findByName", query = "SELECT g FROM Garden g WHERE g.name = :name AND g.user = :user")
})
public class Garden extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    @Size(max = 100, min = 1, message = "Name is in incorrect format.")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String location;

    @OneToMany(mappedBy = "garden")
    private List<Pressure> pressures;

    @OneToMany(mappedBy = "garden")
    private List<Temperature> temperatures;

    @OneToMany(mappedBy = "garden")
    private List<Humidity> humidities;

    @OneToMany(mappedBy = "garden")
    private List<Soil> soils;

    @OneToMany(mappedBy = "garden")
    private List<Rain> rains;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany( mappedBy = "garden")
    private List<UserPlant> plants;

    @ManyToMany
    private List<Valve> valves;


    public Garden(@Size(max = 100, min = 1, message = "Name is in incorrect format.") @NotBlank(message = "Name cannot be blank") String name,
                  String location, List<Pressure> pressures, List<Temperature> temperatures, List<Humidity> humidities,
                  User user, List<UserPlant> plants, List<Valve> valves, List<Soil> soils, List<Rain> rain) {

        this.name = name;
        this.location = location;
        this.pressures = pressures;
        this.temperatures = temperatures;
        this.humidities = humidities;
        this.user = user;
        this.plants = plants;
        this.valves = valves;
        this.soils = soils;
        this.rains = rain;
    }

    public Garden() {
        this.humidities = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.pressures = new ArrayList<>();
        this.temperatures = new ArrayList<>();
        this.valves = new ArrayList<>();
        this.soils = new ArrayList<>();
        this.rains = new ArrayList<>();
    }


    public Garden(@Size(max = 100, min = 1, message = "Name is in incorrect format.") @NotBlank(message = "Name cannot be blank") String name, String location, User user, List<UserPlant> plants) {

        this.name = name;
        this.user = user;
        this.location = location;
        this.plants = plants;
        this.humidities = new ArrayList<Humidity>();
        this.pressures = new ArrayList<Pressure>();
        this.temperatures = new ArrayList<Temperature>();
        this.valves = new ArrayList<>();
        this.soils = new ArrayList<>();
        this.rains = new ArrayList<>();
    }


    public Garden(@Size(max = 100, min = 1, message = "Name is in incorrect format.") @NotBlank(message = "Name cannot be blank") String name, String location, User user) {

        this.name = name;
        this.location = location;
        this.user = user;
        this.plants = new ArrayList<>();
        this.humidities = new ArrayList<Humidity>();
        this.pressures = new ArrayList<Pressure>();
        this.temperatures = new ArrayList<Temperature>();
        this.valves = new ArrayList<>();
        this.soils = new ArrayList<>();
        this.rains = new ArrayList<>();
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    public String getLocation() {

        return location;
    }


    public void setLocation(String location) {

        this.location = location;
    }


    public List<Pressure> getPressures() {

        return pressures;
    }


    public void setPressures(List<Pressure> pressures) {

        this.pressures = pressures;
    }


    public List<Temperature> getTemperatures() {

        return temperatures;
    }


    public void setTemperatures(List<Temperature> temperatures) {

        this.temperatures = temperatures;
    }


    public List<Humidity> getHumidities() {

        return humidities;
    }


    public void setHumidities(List<Humidity> humidities) {

        this.humidities = humidities;
    }


    public User getUser() {

        return user;
    }


    public void setUser(User user) {

        this.user = user;
    }


    public List<UserPlant> getPlants() {

        return plants;
    }


    public void setPlants(List<UserPlant> plants) {

        this.plants = plants;
    }


    public List<Valve> getValves() {

        return valves;
    }


    public void setValves( List<Valve> valves) {

        this.valves = valves;
    }


    public List<Soil> getSoils() {

        return soils;
    }


    public void setSoils(List<Soil> soils) {

        this.soils = soils;
    }


    public List<Rain> getRains() {

        return rains;
    }


    public void setRains(List<Rain> rains) {

        this.rains = rains;
    }


    @Override
    public String toString() {

        return "Garden{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", pressures=" + pressures +
                ", temperatures=" + temperatures +
                ", humidities=" + humidities +
                ", soils=" + soils +
                ", valves=" + valves +
                '}';
    }
}
