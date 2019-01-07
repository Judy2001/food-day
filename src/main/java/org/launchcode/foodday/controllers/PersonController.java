package org.launchcode.foodday.controllers;

import org.launchcode.foodday.models.Foodday;
import org.launchcode.foodday.models.Person;
import org.launchcode.foodday.models.data.FooddayDao;
import org.launchcode.foodday.models.data.PersonDao;

import org.launchcode.foodday.models.forms.AddPersonForm;
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
/*

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("persons", personDao.findAll());
        model.addAttribute("title", "Food Day!");
        //model.addAttribute("date", fooddayDao.findOne(dateId));

        return "food/index";
    }*/


    @RequestMapping(value = "add/{dateId}", method = RequestMethod.GET)
    public String displayAddPersonForm(Model model, @PathVariable int dateId) {

        Foodday date = fooddayDao.findOne(dateId);
        AddPersonForm form = new AddPersonForm(personDao.findAll(), date);

        model.addAttribute("title", "Add Person to " + date.getDate());
        model.addAttribute("form", form);

        return "food/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPersonForm(@ModelAttribute @Valid AddPersonForm form, Errors errors,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Person");
            return "food/add";
        }

        Person person = personDao.findOne(form.getPersonId());
        Foodday date = fooddayDao.findOne(form.getDateId());
        date.addPerson(person);
        fooddayDao.save(date);

        return "redirect:view/" + date.getId();
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePersonForm(Model model) {
        model.addAttribute("title", "Remove Person");
        model.addAttribute("persons", personDao.findAll());

        return "food/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePersonForm(@RequestParam int[] personIds,
                                          @ModelAttribute Foodday date) {
        for (int personId : personIds) {

            personDao.delete(personId);
        }

        return "redirect:view/" + date.getId();
    }


    @RequestMapping(value = "edit/{personId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int dateId,
                                  @RequestParam int personId) {

        Person aPerson = personDao.findOne(personId);
        Foodday date = fooddayDao.findOne(dateId);

        if (aPerson == null) {
            return "redirect:view/";
        }

        model.addAttribute("title", "Edit Information");
        model.addAttribute("person", aPerson);
        return "food/edit";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid Person person,
                                  Errors errors, @RequestParam int personId,
                                  @PathVariable int dateId) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Edit Information");
            model.addAttribute("person", person);

            return "food/edit";
        }

        Person aPerson = personDao.findOne(personId);
        aPerson.setName(person.getName());
        aPerson.setFood(person.getFood());
        personDao.save(aPerson);

        return "redirect:view/" + dateId;
    }


    @RequestMapping(value = "view/{dateId}", method = RequestMethod.GET)
    public String viewDate(Model model, @PathVariable int dateId) {

        Foodday date = fooddayDao.findOne(dateId);
        model.addAttribute("date", date);
        model.addAttribute("persons", date.getPersons());
        model.addAttribute("dateId", date.getId());

        return "food/view/" + date.getId();
    }

}
