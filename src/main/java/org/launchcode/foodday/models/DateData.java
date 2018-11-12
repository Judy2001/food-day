package org.launchcode.foodday.models;

import java.util.ArrayList;


public class DateData {

    static ArrayList<Date> dates = new ArrayList<>();


    public static ArrayList<Date> getAll() {
        return dates;
    }


    public static void add(Date newDate) {
        dates.add(newDate);
    }


    public static void remove(int id) {
        Date dateToRemove = getById(id);
        dates.remove(dateToRemove);
    }


    public static Date getById(int id) {

        Date theDate = null;

        for (Date candidateDate : dates) {
            if (candidateDate.getId() == id) {
                theDate = candidateDate;
            }
        }

        return theDate;
    }

}
