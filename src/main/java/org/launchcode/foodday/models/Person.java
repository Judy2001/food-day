package org.launchcode.foodday.models;

public class Person {

    private int personId;
    private static int nextId =1;
    private String name;
    private String food;


    public Person(String name, String food) {
        this();
        this.name = name;
        this.food = food;
    }


    public Person() {
        personId = nextId;
        nextId++;
    }


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

}