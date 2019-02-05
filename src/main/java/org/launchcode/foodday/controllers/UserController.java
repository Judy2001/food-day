package org.launchcode.foodday.controllers;


import org.launchcode.foodday.models.Login;
import org.launchcode.foodday.models.User;
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


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "Food Day!");

        return "user/index";
    }


    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displaySignupForm(Model model) {

        model.addAttribute("title", "Register");
        model.addAttribute(new User());

        return "user/signup";
    }


    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processSignupForm(@ModelAttribute @Valid User user,
                                    Errors errors, String verify, Model model) {

        if (!errors.hasErrors() && user.getPassword().equals(verify)) {

            model.addAttribute("user", user);
            userDao.save(user);

            return "redirect:/date";

        } else {

            if (!user.getPassword().equals(verify)) {
                model.addAttribute("message", "Passwords must match");
                user.setPassword("");
            }

            return "user/signup";

        }

    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute("title", "Food Day!");
        model.addAttribute("id", userDao.findAll());
        model.addAttribute(new Login());
        return "user/login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid Login newLogin, Errors errors,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Food Day!");
            return "user/login";
        }

        User user = newLogin.loginUser();
        userDao.save(user);

        return "redirect:/date";
    }


/*    @RequestMapping(value= "logout", method = RequestMethod.POST)
    public String logout() {
        session.removeAttribute("username");
        session.invalidate();
    }*/

}
