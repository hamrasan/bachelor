package cz.fel.cvut.hamrasan.gardener.model;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Table(name = "APP_NOTIFICATION")
@NamedQueries({
        @NamedQuery(name = "Notification.find3Days", query = "SELECT n FROM Notification n WHERE n.date >= :date"),
        @NamedQuery(name = "Notification.notSeenOfUser", query = "SELECT n FROM Notification n WHERE n.user = :user AND n.seen = false"),
})
public class Notification extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @PastOrPresent
    private LocalDate date;

    @Basic(optional = false)
    @Column(nullable = false)
    private String message;

    @Basic(optional = false)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Basic(optional = false)
    @Column(nullable = false)
    private boolean seen;

    @ManyToOne
    private User user;


    public Notification(LocalDate date, String message, NotificationType type, User user) {

        this.date = date;
        this.message = message;
        this.type = type;
        this.user = user;
        this.seen = false;
    }


    public Notification() {
        this.seen = false;
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


    public User getUser() {

        return user;
    }


    public void setUser(User user) {

        this.user = user;
    }


    public boolean isSeen() {

        return seen;
    }


    public void setSeen(boolean seen) {

        this.seen = seen;
    }
}
