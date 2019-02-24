package org.launchcode.foodday.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Foodday {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=6, message = "Please enter a date")
    private String date;

    @ManyToMany
    private List<User> users;


/*    public Foodday(String date) {
        this.date = date;
    }*/


    public Foodday() { }


    public void addPerson(User user, String food) {
        users.add(user);
    }


    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<User> getUsers() {
        return users;
    }

}
