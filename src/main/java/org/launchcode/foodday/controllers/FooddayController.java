package org.launchcode.foodday.controllers;


import org.launchcode.foodday.models.Foodday;
import org.launchcode.foodday.models.data.FooddayDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "date")
public class FooddayController {

    @Autowired
    private FooddayDao fooddayDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Food Day Dates");
        model.addAttribute("dates", fooddayDao.findAll());

        return "date/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddDateForm(Model model) {

        model.addAttribute("title", "Add Date");

        return "date/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddDateForm(@ModelAttribute @Valid Foodday newDate, Errors errors, Model model) {

        fooddayDao.save(newDate);

        return "redirect:";
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveDateForm(Model model) {
        model.addAttribute("title", "Remove Date");
        model.addAttribute("dates", fooddayDao.findAll());

        return "date/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveDateForm(@RequestParam int[] dateIds) {

        for (int dateId : dateIds) {
            fooddayDao.delete(dateId);
        }

        return "redirect:";
    }

}
