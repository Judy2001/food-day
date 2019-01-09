package org.launchcode.foodday.controllers;


import org.launchcode.foodday.models.Foodday;
import org.launchcode.foodday.models.User;
import org.launchcode.foodday.models.data.FooddayDao;
import org.launchcode.foodday.models.data.UserDao;

import org.launchcode.foodday.models.forms.AddPersonForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("food")
public class FoodController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FooddayDao fooddayDao;


    @RequestMapping(value = "add/{dateId}", method = RequestMethod.GET)
    public String displayAddPersonForm(Model model, @PathVariable int dateId) {

        Foodday date = fooddayDao.findOne(dateId);
        AddPersonForm form = new AddPersonForm(userDao.findAll(), date);

        model.addAttribute("title", "Add Person to " + date.getDate());
        model.addAttribute("form", form);

        return "food/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String ProcessAddPersonForm(@ModelAttribute @Valid AddPersonForm form, Errors errors,
                            Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "food/add";
        }

        User user = userDao.findOne(form.getPersonId());
        Foodday date = fooddayDao.findOne(form.getDateId());
        date.addPerson(user);
        fooddayDao.save(date);

        return "redirect:/food/view/" + date.getId();
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePersonForm(Model model) {
        model.addAttribute("title", "Remove Person");
        model.addAttribute("persons", userDao.findAll());

        return "food/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePersonForm(@RequestParam int[] personIds,
                                          @ModelAttribute Foodday date) {
        for (int personId : personIds) {

            userDao.delete(personId);
        }

        return "redirect:view/" + date.getId();
    }


    @RequestMapping(value = "edit/{personId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int dateId,
                                  @RequestParam int personId) {

        User aUser = userDao.findOne(personId);
        Foodday date = fooddayDao.findOne(dateId);

        if (aUser == null) {
            return "redirect:view/";
        }

        model.addAttribute("title", "Edit Information");
        model.addAttribute("person.user.html", aUser);
        return "food/edit";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid User user,
                                  Errors errors, @RequestParam int personId,
                                  @PathVariable int dateId) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Edit Information");
            model.addAttribute("person.user.html", user);

            return "food/edit";
        }

        User aUser = userDao.findOne(personId);
        aUser.setName(user.getName());
        aUser.setFood(user.getFood());
        userDao.save(aUser);

        return "redirect:view/" + dateId;
    }


    @RequestMapping(value = "view/{dateId}", method = RequestMethod.GET)
    public String viewDate(Model model, @PathVariable int dateId) {

        Foodday date = fooddayDao.findOne(dateId);
        model.addAttribute("date", date);
        model.addAttribute("persons", date.getUsers());
        model.addAttribute("dateId", date.getId());

        return "food/view/" + date.getId();
    }

}
