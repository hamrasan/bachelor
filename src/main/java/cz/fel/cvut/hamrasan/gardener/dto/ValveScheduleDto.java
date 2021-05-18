package cz.fel.cvut.hamrasan.gardener.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ValveScheduleDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @NotNull(message = "Hour cannot be blank")
    @Max(24)
    @Min(0)
    private Integer hour;

    @NotNull(message = "Minutes cannot be blank")
    @Max(60)
    @Min(0)
    private Integer minutes;

    @NotNull(message = "Length minutes cannot be blank")
    @Min(0)
    @Max(1380)
    private Integer length;

    private List<Integer> days;

    @NotNull(message = "Valve  cannot be blank")
    private Long valveId;


    public ValveScheduleDto(@NotNull(message = "Id cannot be blank") Long id, @NotNull(message = "Hour cannot be blank") @Max(24) @Min(0) Integer hour, @NotNull(message = "Minutes cannot be blank") @Max(60) @Min(0) Integer minutes, @NotNull(message = "Length minutes cannot be blank") @Min(0) @Max(1380) Integer length, List<Integer> days, @NotNull(message = "Valve  cannot be blank") Long valveId) {

        this.id = id;
        this.hour = hour;
        this.minutes = minutes;
        this.length = length;
        this.days = days;
        this.valveId = valveId;
    }


    public ValveScheduleDto() {
    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public Integer getHour() {

        return hour;
    }


    public void setHour(Integer hour) {

        this.hour = hour;
    }


    public Integer getMinutes() {

        return minutes;
    }


    public void setMinutes(Integer minutes) {

        this.minutes = minutes;
    }


    public Integer getLength() {

        return length;
    }


    public void setLength(Integer length) {

        this.length = length;
    }


    public List<Integer> getDays() {

        return days;
    }


    public void setDays(List<Integer> days) {

        this.days = days;
    }


    public Long getValveId() {

        return valveId;
    }


    public void setValveId(Long valveId) {

        this.valveId = valveId;
    }
}
