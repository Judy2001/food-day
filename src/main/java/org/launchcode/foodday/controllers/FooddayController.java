package org.launchcode.foodday.controllers;


import org.launchcode.foodday.models.AddPerson;
import org.launchcode.foodday.models.Foodday;
import org.launchcode.foodday.models.Person;
import org.launchcode.foodday.models.data.FooddayDao;
import org.launchcode.foodday.models.data.PersonDao;

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

    @Autowired
    private PersonDao personDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Food Day Dates");
        model.addAttribute("dates", fooddayDao.findAll());

        return "date/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddDateForm(Model model) {

        model.addAttribute("title", "Add Date");
        model.addAttribute(new Foodday());

        return "date/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddDateForm(Model model,
                                     @ModelAttribute @Valid Foodday date, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Date");
            return "date/add";
        }

        fooddayDao.save(date);

        return "redirect:view/" + date.getId();
    }


    @RequestMapping(value = "view/{dateId}", method = RequestMethod.GET)
    public String viewDate(Model model, @PathVariable int dateId) {

        Foodday date = fooddayDao.findOne(dateId);
        model.addAttribute("date", date);

        return "date/view";
    }


    @RequestMapping(value = "add-person/{dateId}", method = RequestMethod.GET)
    public String addPerson(Model model, @PathVariable int dateId) {

        Foodday date = fooddayDao.findOne(dateId);
        AddPerson form = new AddPerson(personDao.findAll(), date);

        model.addAttribute("title", "Add person to " + date.getDate() + " food day.");
        model.addAttribute("form", form);

        return "date/add-person";
    }


    @RequestMapping(value = "add-person", method = RequestMethod.POST)
    public String addPerson(Model model,
                            @ModelAttribute @Valid AddPerson form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "date/add-person";
        }

        Person person = personDao.findOne(form.getPersonId());
        Foodday date = fooddayDao.findOne(form.getDateId());
        date.addPerson(person);
        fooddayDao.save(date);

        return "redirect:/date/view/" + date.getId();
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
