package org.launchcode.foodday.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=25, message = "Name must be between 2 and 25 characters long")
    private String name;

    @NotNull
    @Size(min=4, message = "Invalid password")
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Foodday> dates;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public User() { }


    private static ArrayList<User> users = new ArrayList<>();


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

    public List<Foodday> getDates() {
        return dates;
    }

}
