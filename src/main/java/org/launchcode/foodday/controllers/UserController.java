package org.launchcode.foodday.controllers;

import org.launchcode.foodday.models.Foodday;
import org.launchcode.foodday.models.User;
import org.launchcode.foodday.models.Signup;
import org.launchcode.foodday.models.data.FooddayDao;
import org.launchcode.foodday.models.data.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

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


    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displaySignupForm(Model model) {

        model.addAttribute("title", "My 401k");
        model.addAttribute("id", userDao.findAll());
        model.addAttribute(new Signup());

        return "person.user.html/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processSignupForm(@ModelAttribute @Valid Signup newSignup,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "My 401k");
            return "person.user.html/signup";
        }

        User newUser = newSignup.createUser();
        userDao.save(newUser);

        return "redirect:/investment_choices/display_form";

    }




}
