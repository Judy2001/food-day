package org.launchcode.foodday.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Person {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=25, message = "Name must be between 2 and 25 characters long")
    private String name;

    @NotNull
    @Size(min=3, message = "Bring some food!")
    private String food;

    private Foodday date;

    @ManyToMany
    private List<Foodday> dates;


    public Person(String name, String food) {
        this.name = name;
        this.food = food;
    }


    public Person() { }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setDate(Foodday date) {
        this.date = date;
    }

    public Foodday getDate() {return date;}

}
