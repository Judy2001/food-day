package org.launchcode.foodday.models;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Signup extends User {

    @NotNull
    @Size(min=3, max=30, message = "Username must be 3-20 characters long")
    private String name;

    @NotNull
    @Size(min=3, max=20, message = "Password must be 3-20 characters long")
    private String password;

    //@Transient
    @NotNull(message = "Passwords must match")
    private String verify;


    public Signup(String name, String password, String verify) {
        this.name = name;
        this.password = password;
        this.verify = verify;
    }

    public Signup() { }


    public void setSignup(Signup newSignup) {
    }


    public User createUser(){
        User newUser = new User();
        newUser.setName(this.name);
        newUser.setPassword(this.password);

        return (newUser);
    }


    private void checkPassword() {

        if (password != null && verify != null && !password.equals(verify)) {
            verify = "";
            }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
        checkPassword();
    }

}
