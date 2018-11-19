package org.launchcode.foodday.controllers;

import org.launchcode.foodday.models.FoodDay;
import org.launchcode.foodday.models.data.FoodDayDao;
import org.launchcode.foodday.models.data.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
@RequestMapping("date")
public class FoodDayController {


    @Autowired
    private FoodDayDao foodDayDao;

    @Autowired
    private PersonDao personDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Date(s)");
        model.addAttribute("dates", foodDayDao.findAll());

        return "date/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddDateForm(Model model) {

        model.addAttribute("title", "Add Date");
        model.addAttribute(new FoodDay());
        model.addAttribute("dates", foodDayDao.findAll());

        return "date/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddDateForm(@ModelAttribute  @Valid FoodDay newDate,
                                     Errors errors, @RequestParam int foodDayId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Date");
            return "date/add";
        }

        foodDayDao.save(newDate);

        return "redirect:";
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveDateForm(Model model) {
        model.addAttribute("title", "Remove Date");
        model.addAttribute("dates", foodDayDao.findAll());

        return "date/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveDateForm(@RequestParam int[] dateIds) {

        for (int dateId : dateIds) {
            foodDayDao.delete(dateId);
        }

        return "redirect:";
    }

}