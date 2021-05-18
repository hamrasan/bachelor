package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "APP_RAIN")
@NamedQueries({
        @NamedQuery(name = "Rain.findLatest", query = "SELECT r FROM Rain r WHERE r.garden = :garden ORDER BY r.date DESC ")
})
public class Rain extends AbstractSensor<Boolean>{


        @ManyToOne
        @JoinColumn(name = "garden_id", nullable = false)
        private Garden garden;


        public Rain(LocalDateTime date, boolean raining, Garden garden) {

            this.date = date;
            this.value = raining;
            this.garden = garden;
        }

        public Rain() {

        }

        public LocalDateTime getDate() {

            return date;
        }


        public void setDate(LocalDateTime localDate) {

            this.date = localDate;
        }


        public boolean getRaining() {

            return value;
        }


        public void setRaining(boolean raining) {

            this.value = raining;
        }


        public Garden getGarden() {

            return garden;
        }


        public void setGarden(Garden garden) {

            this.garden = garden;
        }

}
