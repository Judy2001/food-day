package org.launchcode.foodday.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=25, message = "Name must be between 2 and 25 characters long")
    private String name;

    @NotNull
    private String password;

    private String food;

    @ManyToOne
    private Foodday foodday;

    private static ArrayList<User> users = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public User() { }


    public void setAUser(User newUser) {
    }


    public static void add(User newUser) {
        users.add(newUser);
    }


    public static User getById(int id) {

        User theUser = null;

        for (User candidateUser : users) {
            if (candidateUser.getId() == id) {
                theUser = candidateUser;
            }
        }

        return theUser;
    }


    public int getId() {
        return id;
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
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Foodday getFoodday() {
        return foodday;
    }

    public void setFoodday(Foodday foodday) {
        this.foodday = foodday;
    }

}
