package cz.fel.cvut.hamrasan.gardener.dto;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class SoilDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @PastOrPresent
    private LocalDateTime date;

    @Basic(optional = false)
    private float value;

    @Basic(optional = false)
    private Long garden;


    public SoilDto(@NotNull(message = "Id cannot be blank") Long id, @PastOrPresent LocalDateTime date, float value, Long garden) {

        this.id = id;
        this.date = date;
        this.value = value;
        this.garden = garden;
    }


    public SoilDto() {

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


    public float getValue() {

        return value;
    }


    public void setValue(float value) {

        this.value = value;
    }


    public Long getGarden() {

        return garden;
    }


    public void setGarden(Long garden) {

        this.garden = garden;
    }
}
