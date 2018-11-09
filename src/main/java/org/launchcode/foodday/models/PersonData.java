package org.launchcode.foodday.models;

import java.util.ArrayList;

public class PersonData {


    public static String date;


    static ArrayList<Person> persons = new ArrayList<>();


    public static ArrayList<Person> getAll() {
        return persons;
    }


    public String getIt() {
        return date;
    }


    public static void add(Person newPerson) {
        persons.add(newPerson);
    }


    public static void remove(int id) {
        Person personToRemove = getById(id);
        persons.remove(personToRemove);
    }


    public static Person getById(int id) {

        Person thePerson = null;

        for (Person candidatePerson : persons) {
            if (candidatePerson.getPersonId() == id) {
                thePerson = candidatePerson;
            }
        }

        return thePerson;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}