package org.launchcode.foodday.models;

public class Date {

    private int id;
    private static int nextId =1;
    private String date;


    public Date(String date) {
        this();
        this.id = id;
        this.date = date;
    }


    public Date() {
        id = nextId;
        nextId++;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
