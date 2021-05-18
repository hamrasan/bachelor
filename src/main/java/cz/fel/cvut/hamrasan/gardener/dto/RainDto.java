package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class RainDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @PastOrPresent
    private LocalDateTime date;

    @Basic(optional = false)
    private boolean raining;

    @Basic(optional = false)
    private Long garden;


    public RainDto(@NotNull(message = "Id cannot be blank") Long id, @PastOrPresent LocalDateTime date, boolean raining, Long garden) {

        this.id = id;
        this.date = date;
        this.raining = raining;
        this.garden = garden;
    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public LocalDateTime getDate() {

        return date;
    }


    public void setDate(LocalDateTime date) {

        this.date = date;
    }


    public boolean isRaining() {

        return raining;
    }


    public void setRaining(boolean raining) {

        this.raining = raining;
    }


    public Long getGarden() {

        return garden;
    }


    public void setGarden(Long garden) {

        this.garden = garden;
    }
}
