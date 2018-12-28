package org.launchcode.foodday.controllers;

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
@RequestMapping("food")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private FooddayDao fooddayDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("persons", personDao.findAll());
        model.addAttribute("title", "Food Day!");
        //model.addAttribute("date", FoodDayData.getById(id));

        return "food/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPersonForm(Model model) {

        model.addAttribute("title", "Add Person");
        model.addAttribute(new Person());
        model.addAttribute("dates", fooddayDao.findAll());

        return "food/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPersonForm(@ModelAttribute @Valid Person newPerson, Errors errors,
                                       @ModelAttribute Foodday dates, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Person");
            model.addAttribute("dates", fooddayDao.findAll());
            return "food/add";
        }

        personDao.save(newPerson);

        return "redirect:";
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePersonForm(Model model) {
        model.addAttribute("title", "Remove Person");
        model.addAttribute("persons", personDao.findAll());

        return "food/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePersonForm(@RequestParam int[] personIds) {
        for (int personId : personIds) {

            personDao.delete(personId);
        }

        return "redirect:";
    }


    @RequestMapping(value = "edit/{personId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int personId) {

        Person aPerson = personDao.findOne(personId);
        if (aPerson == null) {
            return "redirect:";
        }

        model.addAttribute("title", "Edit Person Information");
        model.addAttribute("person", aPerson);
        return "food/edit";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Person person,
                                  Errors errors, @RequestParam int personId) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Edit");
            model.addAttribute("person", person);

            return "food/edit";
        }

        Person aPerson = personDao.findOne(personId);
        aPerson.setName(person.getName());
        aPerson.setFood(person.getFood());
        personDao.save(aPerson);

        return "redirect:";
    }

}
