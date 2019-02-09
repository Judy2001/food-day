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
    @Size(min=4, message = "Password must be at least 4 characters long")
    private String password;

    @ManyToOne
    private Foodday foodday;

    private static ArrayList<User> users = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public User() { }


    public static void add(User newUser) {
        users.add(newUser);
    }


    public static User getById(int id) {

        User user = null;

        for (User candidateUser : users) {
            if (candidateUser.getId() == id) {
                user = candidateUser;
            }
        }

        return user;
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

    public Foodday getFoodday() {
        return foodday;
    }

    public void setFoodday(Foodday foodday) {
        this.foodday = foodday;
    }

/*    public User get(int i) {
        return User.getById((i));
    }*/
}
