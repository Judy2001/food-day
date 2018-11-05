package org.launchcode.foodday.models;

import java.util.ArrayList;

public class PersonData {

    static ArrayList<Person> persons = new ArrayList<>();


    public static ArrayList<Person> getAll() {
        return persons;
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

}