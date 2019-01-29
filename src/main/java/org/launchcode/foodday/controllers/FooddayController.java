package org.launchcode.foodday.controllers;


import org.launchcode.foodday.models.Foodday;
import org.launchcode.foodday.models.User;
import org.launchcode.foodday.models.data.FooddayDao;
import org.launchcode.foodday.models.data.UserDao;

import org.launchcode.foodday.models.forms.AddUserForm;
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
    private UserDao userDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Food-Day Dates");
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
    public String processAddDateForm(@ModelAttribute @Valid Foodday date, Errors errors,
                                     Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Date");
            return "date/add";
        }

        fooddayDao.save(date);

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


    @RequestMapping(value = "view/{dateId}", method = RequestMethod.GET)
    public String viewDate(Model model, @PathVariable int dateId) {

        Foodday date = fooddayDao.findOne(dateId);
        model.addAttribute("date", date);
        model.addAttribute("persons", date.getUsers());
        model.addAttribute("dateId", date.getId());

        return "date/view";
    }


    @RequestMapping(value = "add-person/{dateId}", method = RequestMethod.GET)
    public String displayAddUserForm(Model model, @PathVariable int dateId) {

        Foodday date = fooddayDao.findOne(dateId);
        AddUserForm form = new AddUserForm(userDao.findAll(), date);

        model.addAttribute("title", "Add Person to " + date.getDate());
        model.addAttribute("date", date);
        model.addAttribute("form", form);

        return "date/add-person";
    }


    @RequestMapping(value = "add-person", method = RequestMethod.POST)
    public String ProcessAddUserForm(@ModelAttribute @Valid AddUserForm form, Errors errors,
                                     Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "date/add-person";
        }

        User user = userDao.findOne(form.getUserId());
        Foodday date = fooddayDao.findOne(form.getDateId());
        String food = "";
        date.addPerson(user, food);
        fooddayDao.save(date);

        return "redirect:/date/view/" + date.getId();
    }


    @RequestMapping(value = "remove-person/{personId}", method = RequestMethod.GET)
    public String displayRemovePersonForm(Model model) {
        model.addAttribute("title", "Remove Person");
        model.addAttribute("persons", userDao.findAll());

        return "food/remove";
    }


    @RequestMapping(value = "remove-person", method = RequestMethod.POST)
    public String processRemovePersonForm(@RequestParam int[] userIds,
                                          @ModelAttribute Foodday date) {
        for (int userId : userIds) {

            userDao.delete(userId);
        }

        return "redirect:view/" + date.getId();
    }


    @RequestMapping(value = "edit/{personId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int dateId,
                                  @RequestParam int userId) {

        User aUser = userDao.findOne(userId);
        Foodday date = fooddayDao.findOne(dateId);
        AddUserForm form = new AddUserForm(userDao.findAll(), date);

        if (aUser == null) {
            return "redirect:view/";
        }

        model.addAttribute("title", "Edit Information");
        model.addAttribute("user.name", aUser);
        return "food/edit";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid User user,
                                  Errors errors, @RequestParam int userId,
                                  @PathVariable int dateId) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Edit Information");
            model.addAttribute("user.name", user);

            return "food/edit";
        }

        User aUser = userDao.findOne(userId);
        aUser.setName(user.getName());
        Foodday date = fooddayDao.findOne(dateId);
        AddUserForm form = new AddUserForm(userDao.findAll(), date);
        form.setFood(form.getFood());
        userDao.save(aUser);

        return "redirect:/date/view/" + dateId;
    }

}
