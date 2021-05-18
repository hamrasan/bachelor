package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "APP_HUMIDITY")
@NamedQueries({
        @NamedQuery(name = "Humidity.findLatest", query = "SELECT h FROM Humidity h WHERE h.garden = :garden ORDER BY h.date DESC ")
})
public class Humidity extends AbstractSensor<Float> {

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;


    public Humidity(LocalDateTime date, float value, Garden garden) {

        this.date = date;
        this.value = value;
        this.garden = garden;
    }


    public Humidity() {

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
