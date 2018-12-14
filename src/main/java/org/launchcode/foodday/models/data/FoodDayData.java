package org.launchcode.foodday.models.data;

import org.launchcode.foodday.models.FoodDay;

import java.util.ArrayList;


public class FoodDayData {

    static ArrayList<FoodDay> dates = new ArrayList<>();


    public static ArrayList<FoodDay> getAll() {
        return dates;
    }


    public static void add(FoodDay newDate) {
        dates.add(newDate);
    }


    public static void remove(int id) {
        FoodDay dateToRemove = getById(id);
        dates.remove(dateToRemove);
    }


    public static FoodDay getById(int id) {

        FoodDay theDate = null;

        for (FoodDay candidateDate : dates) {
            if (candidateDate.getId() == id) {
                theDate = candidateDate;
            }
        }

        return theDate;
    }

}
