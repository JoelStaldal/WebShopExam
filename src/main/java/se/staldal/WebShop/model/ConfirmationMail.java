package se.staldal.WebShop.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ConfirmationMail {
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
