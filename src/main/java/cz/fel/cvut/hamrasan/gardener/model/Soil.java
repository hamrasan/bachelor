package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "APP_SOIL")
@NamedQueries({
        @NamedQuery(name = "Soil.findLatest", query = "SELECT s FROM Soil s WHERE s.garden = :garden ORDER BY s.date DESC ")
})
public class Soil extends AbstractSensor<Float> {

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;


    public Soil(LocalDateTime date, float value, Garden garden) {

        this.date = date;
        this.value = value;
        this.garden = garden;
    }


    public Soil() {

    }


    public LocalDateTime getDate() {

        return date;
    }


    public void setDate(LocalDateTime date) {

        this.date = date;
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
