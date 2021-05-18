package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "APP_TEMPERATURE")
@NamedQueries({
        @NamedQuery(name = "Temperature.findLatest", query = "SELECT t FROM Temperature t WHERE t.garden = :garden ORDER BY t.date DESC ")
})
public class Temperature extends AbstractSensor<Float>{

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;


    public Temperature(LocalDateTime date, float value, Garden garden) {

        this.date = date;
        this.value = value;
        this.garden = garden;
    }


    public Temperature() {

    }


    public LocalDateTime getDate() {

        return date;
    }


    public void setDate(LocalDateTime localDate) {

        this.date = localDate;
    }


    public float getValue() {

        return value;
    }


    public void setValue(float value) {

        this.value = value;
    }


    public Garden getGarden() {

        return garden;
    }


    public void setGarden(Garden garden) {

        this.garden = garden;
    }
}
