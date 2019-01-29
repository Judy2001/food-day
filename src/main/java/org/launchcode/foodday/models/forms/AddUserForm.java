package org.launchcode.foodday.models.forms;


import org.launchcode.foodday.models.Foodday;
import org.launchcode.foodday.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AddUserForm {

    @NotNull
    private int dateId;

    @NotNull
    private int userId;

    private Iterable<User> users;

    @Size(min=3, message = "Bring some food!")
    private String food;

    private Foodday date;



    public AddUserForm() {
    }


    public AddUserForm(Iterable<User> users, Foodday date) {
        this.users = users;
        this.date = date;
    }


    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int personId) {
        this.userId = userId;
    }

    public Iterable<User> getUsers() {
        return users;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Foodday getDate() {
        return date;
    }

}
