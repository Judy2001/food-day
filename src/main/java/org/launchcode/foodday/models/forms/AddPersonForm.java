package org.launchcode.foodday.models.forms;


import org.launchcode.foodday.models.Foodday;
import org.launchcode.foodday.models.Person;

import javax.validation.constraints.NotNull;


public class AddPersonForm {

    @NotNull
    private int dateId;

    @NotNull
    private int personId;

    private Iterable<Person> persons;

    private Foodday date;


    public AddPersonForm() {}


    public AddPersonForm(Iterable<Person> persons, Foodday date) {
        this.persons = persons;
        this.date = date;
    }


    public int getDateId() {
        return dateId;
    }

/*    public void setDateId(int dateId) {
        this.dateId = dateId;
    }*/

    public int getPersonId() {
        return personId;
    }

/*
    public void setPersonId(int personId) {
        this.personId = personId;
    }
*/

    public Iterable<Person> getPersons() {
        return persons;
    }

    public Foodday getDate() {
        return date;
    }

    public void setDate(Foodday date) {
        this.date = date;
    }

}
