package org.launchcode.foodday.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Login extends User {

    @NotNull
    @Size(min=3, max=30, message = "Username must be 3-30 characters long")
    private String name;

    @NotNull
    @Size(min=3, max=20, message = "Password must be 3-20 characters long")
    private String password;

    private int id;


    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public Login() { }


    public void setLogin(Signup newLogin) {
    }


    public User loginUser(){
        User user = new User();
        user.setName(this.name);
        user.setPassword(this.password);

        return (user);
    }


    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getId() {
        return id;
    }

}
