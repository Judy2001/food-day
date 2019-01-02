package org.launchcode.foodday.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Foodday {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, message = "Enter a date")
    private String date;

/*    @OneToMany
    @JoinColumn(name = "foodday_id")
    private ArrayList<Person> persons = new ArrayList<>();*/


    public Foodday(String date) {
        this.date = date;
    }


    public Foodday() { }


/*    public List<Person> getPersons() {
        return persons;
    }*/


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
