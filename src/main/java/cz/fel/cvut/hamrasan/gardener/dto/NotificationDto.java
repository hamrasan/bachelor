package cz.fel.cvut.hamrasan.gardener.dto;

import cz.fel.cvut.hamrasan.gardener.model.NotificationType;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class NotificationDto {

    @NotNull(message = "Id cannot be blank")
    private Long id;

    @Basic(optional = false)
    @PastOrPresent
    private LocalDate date;

    @Basic(optional = false)
    private String message;

    @Basic(optional = false)
    private NotificationType type;

    @Basic(optional = false)
    private boolean seen;

    @Basic(optional = false)
    private Long userId;


    public NotificationDto(@NotNull(message = "Id cannot be blank") Long id, @PastOrPresent LocalDate date, String message, NotificationType type, boolean seen, Long userId) {

        this.id = id;
        this.date = date;
        this.message = message;
        this.type = type;
        this.seen = seen;
        this.userId = userId;
    }


    public NotificationDto() {

    }


    public Long getId() {

        return id;
    }


    public void setId(Long id) {

        this.id = id;
    }


    public LocalDate getDate() {

        return date;
    }


    public void setDate(LocalDate date) {

        this.date = date;
    }


    public String getMessage() {

        return message;
    }


    public void setMessage(String message) {

        this.message = message;
    }


    public NotificationType getType() {

        return type;
    }


    public void setType(NotificationType type) {

        this.type = type;
    }


    public boolean isSeen() {

        return seen;
    }


    public void setSeen(boolean seen) {

        this.seen = seen;
    }


    public Long getUserId() {

        return userId;
    }


    public void setUserId(Long userId) {

        this.userId = userId;
    }
}
