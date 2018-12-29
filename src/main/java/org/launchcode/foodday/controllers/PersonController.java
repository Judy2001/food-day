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
import java.util.List;


@Controller
@RequestMapping(value = "food")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private FooddayDao fooddayDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Food Day!");

        model.addAttribute("persons", personDao.findAll());
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
    public String processAddPersonForm(@ModelAttribute @Valid Person newPerson,
                                       Errors errors, @RequestParam int dateId,
                                       Model model) {
        //Removed from parameters:  @ModelAttribute Foodday dates
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Person");
            model.addAttribute("dates", fooddayDao.findAll());

            return "food/add";
        }

        Foodday fd = fooddayDao.findOne(dateId);
        newPerson.setDate(fd);
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

        model.addAttribute("title", "Edit Information");
        model.addAttribute("person", aPerson);
        model.addAttribute("dates", fooddayDao.findAll());
        return "food/edit";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Person person,
                                  Errors errors, @RequestParam int personId) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Edit Information");
            model.addAttribute("person", person);

            return "food/edit";
        }

        Person aPerson = personDao.findOne(personId);
        aPerson.setName(person.getName());
        aPerson.setFood(person.getFood());
        aPerson.setDate(person.getDate());
        personDao.save(aPerson);

        return "redirect:";
    }


    @RequestMapping(value = "date", method = RequestMethod.GET)
    public String date(Model model, @RequestParam int id) {

        Foodday date = fooddayDao.findOne(id);
        List<Person> persons = date.getPersons();

        model.addAttribute("title", date.getDate() + " Food Day");
        model.addAttribute("persons", persons);

        return "person/index";
    }

}
