package org.launchcode.foodday.models;

import javax.validation.constraints.NotNull;

public class AddPerson {

    @NotNull int dateId;

    @NotNull
    private int personId;

    private Iterable<Person> persons;

    private Foodday date;


    public AddPerson() {}


    public AddPerson(Iterable<Person> persons, Foodday date) {
        this.persons = persons;
        this.date = date;
    }


    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersond(int personId) {
        this.personId = personId;
    }

    public Iterable<Person> getPersons() {
        return persons;
    }

    public Foodday getDate() {
        return date;
    }

}
