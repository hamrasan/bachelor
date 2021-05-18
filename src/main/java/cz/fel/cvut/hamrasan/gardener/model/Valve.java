package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "APP_VALVE")
@NamedQueries({
        @NamedQuery(name = "Valve.findByName", query = "SELECT v FROM Valve v WHERE v.name = :name")
})
public class Valve extends AbstractEntity {

    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    private String name;

    private String picture;

    @OneToMany(mappedBy = "valve")
    private List<ValveSchedule> valveScheduleList;

    @ManyToMany
    @JoinTable(
            name = "garden_valve",
            joinColumns = @JoinColumn(name = "valve_id"),
            inverseJoinColumns = @JoinColumn(name = "garden_id"))
    private List<Garden> gardens;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Valve(String name, String picture, List<ValveSchedule> valveScheduleList, List<Garden> gardens, User user) {

        this.name = name;
        this.picture = picture;
        this.valveScheduleList = valveScheduleList;
        this.gardens = gardens;
        this.user = user;
    }


    public Valve(String name, String picture, User user) {

        this.name = name;
        this.picture = picture;
        this.gardens = new ArrayList<Garden>();
        this.user = user;
        this.valveScheduleList = new ArrayList<ValveSchedule>();
    }


    public Valve() {
        this.gardens = new ArrayList<Garden>();
        this.valveScheduleList = new ArrayList<ValveSchedule>();
    }

    public void removeGarden(Garden garden){
        this.gardens.remove(garden);
    };

    public void addGarden(Garden garden){
        this.gardens.add(garden);
    };

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


    public List<ValveSchedule> getValveScheduleList() {

        return valveScheduleList;
    }


    public void setValveScheduleList(List<ValveSchedule> valveScheduleList) {

        this.valveScheduleList = valveScheduleList;
    }


    public List<Garden> getGardens() {

        return gardens;
    }


    public void setGardens(List<Garden> gardens) {

        this.gardens = gardens;
    }


    public User getUser() {

        return user;
    }


    public void setUser(User user) {

        this.user = user;
    }
}
