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
    @Size(min=6, max=10, message = "Please enter a valid date")
    private String date;

    @ManyToMany
    private List<Person> persons;


    public Foodday(String date) {
        this.date = date;
    }


    public Foodday() { }


    public void addPerson(Person person) {
        persons.add(person);
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

    public List<Person> getPersons() {
        return persons;
    }

}
