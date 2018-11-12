package org.launchcode.foodday.controllers;

import org.launchcode.foodday.models.Date;
import org.launchcode.foodday.models.DateData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("date")
public class DateController {


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Dates");
        model.addAttribute("dates", DateData.getAll());

        return "date/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddDateForm(Model model) {

        model.addAttribute("title", "Add Date");

        return "date/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddDateForm(@ModelAttribute Date newDate) {

        DateData.add(newDate);

        return "redirect:";
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveDateForm(Model model) {
        model.addAttribute("title", "Remove Date");
        model.addAttribute("dates", DateData.getAll());

        return "date/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveDateForm(@RequestParam int[] dateIds) {

        for (int dateId : dateIds) {
            DateData.remove(dateId);
        }

        return "redirect:";
    }

}