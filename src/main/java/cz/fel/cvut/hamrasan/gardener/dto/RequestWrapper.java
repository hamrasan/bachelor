package cz.fel.cvut.hamrasan.gardener.dto;

import cz.fel.cvut.hamrasan.gardener.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestWrapper {

    private User user;

    @Size(max = 255, min = 6, message = "Password control is in incorrect format.")
    @NotBlank(message = "Password control cannot be blank")
    private String password_control;

    private int gender;


    public RequestWrapper(User user, String password_control, int gender) {
        this.user = user;
        this.password_control = password_control;
        this.gender = gender;
    }


    public RequestWrapper() {

    }


    public int getGender() {

        return gender;
    }


    public User getUser() {

        return user;
    }


    public String getPassword_control() {

        return password_control;
    }

    public void encodePassword() {
        this.password_control = new BCryptPasswordEncoder().encode(password_control);
    }

}
