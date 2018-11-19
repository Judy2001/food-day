package org.launchcode.foodday.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class FoodDay {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, message = "Enter a date")
    private String date;

    @OneToMany
    @JoinColumn(name = "food_day_id")
    private List<Person> persons = new ArrayList<>();


    public FoodDay(String date) {
        this.date = date;
    }


    public FoodDay() {
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

}
