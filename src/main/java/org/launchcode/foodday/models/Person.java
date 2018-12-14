package org.launchcode.foodday.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//@Entity
public class Person {

    //@Id
    //@GeneratedValue
    private int personId;
    private int nextId;

    @NotNull
    @Size(min=2, max=25, message = "Name must be between 2 and 25 characters long")
    private String name;

    @NotNull
    @Size(min=3, message = "Bring some food!")
    private String food;

/*    @ManyToOne
    private FoodDay foodDay;*/


    public Person(String name, String food) {
        this.name = name;
        this.food = food;
    }


    public Person() {
        nextId=10;
        nextId++;
    }


    public int getNextId() {
        return nextId;
    }

    public int getPersonId() {
        return personId;
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
/*
    public void setFoodDay(FoodDay date) {
    }*/

}