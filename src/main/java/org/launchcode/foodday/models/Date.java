package org.launchcode.foodday.models;

public class Date {

    private int id;

    private String date;


    public Date(int id, String date) {
        this.id = id;
        this.date = date;
    }


    public Date() {
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