package cz.fel.cvut.hamrasan.gardener.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class RequestWrapperSchedule {

    private List<Integer> days;

    @Size(max = 5, min = 5, message = "Time is in incorrect format.")
    @NotBlank(message = "Time cannot be blank")
    private String time;

    @Min(5)
    @Max(1380)
    private Integer valvingLength;


    public RequestWrapperSchedule() {

    }


    public RequestWrapperSchedule(List<Integer> days, @Size(max = 5, min = 5, message = "Time is in incorrect format.") @NotBlank(message = "Time cannot be blank") String time,
                                  @Min(5) @Max(1380) @NotBlank(message = "Valving length cannot be blank") Integer valvingLength) {

        this.days = days;
        this.time = time;
        this.valvingLength = valvingLength;
    }


    public List<Integer> getDays() {

        return days;
    }


    public void setDays(List<Integer> days) {

        this.days = days;
    }


    public String getTime() {

        return time;
    }


    public void setTime(String time) {

        this.time = time;
    }


    public Integer getValvingLength() {

        return valvingLength;
    }


    public void setValvingLength(Integer valvingLength) {

        this.valvingLength = valvingLength;
    }
}
