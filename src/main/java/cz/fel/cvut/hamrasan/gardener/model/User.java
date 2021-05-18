package cz.fel.cvut.hamrasan.gardener.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "APP_USER")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    @Size(max = 30, min = 1, message = "First name is in incorrect format.")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 30, min = 1, message = "Last name is in incorrect format.")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotNull(message = "Gender cannot be blank")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic(optional = false)
    @Column(nullable = false)
    @Size(max = 255, min = 6, message = "Password is in incorrect format.")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Email(message = "Email should be valid")
    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Basic(optional = false)
    @Column(nullable = false)
    @Max(60)
    @Min(-50)
    private float lowTemperature;

    @Basic(optional = false)
    @Column(nullable = false)
    @Max(60)
    @Min(-50)
    private float highTemperature;

    @OneToMany(mappedBy = "user")
    private List<Garden> gardens;

    @OneToMany(mappedBy = "user")
    private List<Valve> valves;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    public User() {
        this.gardens = new ArrayList<>();
        this.valves = new ArrayList<>();
        this.lowTemperature = 0;
        this.highTemperature = 15;
        this.gender = Gender.MAN;
    }

    public User(@Size(max = 30, min = 1, message = "First name is in incorrect format.") @NotBlank(message = "First name cannot be blank") String firstName, @Size(max = 30, min = 1, message = "Last name is in incorrect format.") @NotBlank(message = "Last name cannot be blank") String lastName,
                @Size(max = 255, min = 6, message = "Password is in incorrect format.") @NotBlank(message = "Password cannot be blank") String password,
                @Email(message = "Email should be valid") @NotBlank(message = "Email cannot be blank") String email, List<Garden> gardens, @NotNull(message = "Gender cannot be blank") Gender gender) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.gardens = gardens;
        this.lowTemperature = 0;
        this.highTemperature = 15;
        this.notifications = new ArrayList<>();
        this.gender = gender;
    }


    public User(@Size(max = 30, min = 1, message = "First name is in incorrect format.") @NotBlank(message = "First name cannot be blank") String firstName, @Size(max = 30, min = 1, message = "Last name is in incorrect format.") @NotBlank(message = "Last name cannot be blank") String lastName,
                @Size(max = 255, min = 6, message = "Password is in incorrect format.") @NotBlank(message = "Password cannot be blank") String password, @Email(message = "Email should be valid") @NotBlank(message = "Email cannot be blank") String email,@NotNull(message = "Gender cannot be blank") Gender gender) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.gardens = new ArrayList<>();
        this.valves = new ArrayList<>();
        this.lowTemperature = 0;
        this.highTemperature = 15;
        this.notifications = new ArrayList<>();
        this.gender = gender;
    }


    public User(@Size(max = 30, min = 1, message = "First name is in incorrect format.") @NotBlank(message = "First name cannot be blank") String firstName, @Size(max = 30, min = 1, message = "Last name is in incorrect format.") @NotBlank(message = "Last name cannot be blank") String lastName, @Size(max = 255, min = 6, message = "Password is in incorrect format.") @NotBlank(message = "Password cannot be blank") String password,
                @Email(message = "Email should be valid") @NotBlank(message = "Email cannot be blank") String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.gardens = new ArrayList<>();
        this.valves = new ArrayList<>();
        this.lowTemperature = 0;
        this.highTemperature = 15;
        this.notifications = new ArrayList<>();
        this.gender = Gender.MAN;
    }


    public User(@Email(message = "Email should be valid") String email,
                @Size(max = 255, min = 6, message = "Password is in incorrect format.") String password) {
        this.email = email;
        this.password = password;
        this.gardens = new ArrayList<>();
        this.valves = new ArrayList<>();
        this.lowTemperature = 0;
        this.highTemperature = 15;
        this.notifications = new ArrayList<>();
        this.gender = Gender.MAN;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void erasePassword() {
        this.password = null;
    }

    public String getEmail() {
        return email;
    }

    public void encodePassword() {
        this.password = new BCryptPasswordEncoder().encode(password);
    }


    public List<Garden> getGardens() {

        return gardens;
    }


    public void setGardens(List<Garden> gardens) {

        this.gardens = gardens;
    }


    public List<Valve> getValves() {

        return valves;
    }


    public void setValves(List<Valve> valves) {

        this.valves = valves;
    }

    public void addValve(Valve valve) {

        this.valves.add(valve);
    }


    public float getLowTemperature() {

        return lowTemperature;
    }


    public void setLowTemperature(float lowTemperature) {

        this.lowTemperature = lowTemperature;
    }


    public float getHighTemperature() {

        return highTemperature;
    }


    public void setHighTemperature(float highTemperature) {

        this.highTemperature = highTemperature;
    }


    public List<Notification> getNotifications() {

        return notifications;
    }


    public void setNotifications(List<Notification> notifications) {

        this.notifications = notifications;
    }


    public Gender getGender() {

        return gender;
    }


    public void setGender(Gender gender) {

        this.gender = gender;
    }


    @Override
    public String toString() {

        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", valves=" + valves +
                '}';
    }
}
