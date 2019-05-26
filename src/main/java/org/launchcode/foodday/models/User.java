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
    @Size(min=2, max=30, message = "Name must be between 2 and 30 characters long")
    private String name;

    @NotNull
    @Size(min=3, max=30, message = "Password must be 3-30 characters long")
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Foodday> dates;


    public User() { }


    public User createUser(){
        User newUser = new User();
        newUser.setName(this.name);
        newUser.setPassword(this.password);

        return (newUser);
    }


    public User(String name, String password) {
        this.name = name;
        this.password = password;
        createUser();
    }


    public static ArrayList<User> users = new ArrayList<>();


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
