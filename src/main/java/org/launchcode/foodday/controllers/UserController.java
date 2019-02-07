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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


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
        //model.addAttribute("id", userDao.findAll());
        model.addAttribute(new User());
        return "user/login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid User user, Errors errors,
                                   HttpServletResponse response, Model model) {

        List<User> user = userDao.getName(user.getName);

        if (user.isEmpty()) {
            model.addAttribute("message", "Invalid Name");
            model.addAttribute("title", "Login to Food Day!");
            return "user/login";
        }

        User loggedIn = user.get(0);

        if (loggedIn.getPassword().equals(user.getPassword())) {
            Cookie cookie = new Cookie("user", user.getName());
            cookie.setPath("/");
            response.addCookie(cookie);

            return "redirect:/date";

        } else {

            model.addAttribute("message", "Invalid Password");
            model.addAttribute("title", "Login to Food Day!");

            return "user/login";
        }
    }


    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

        }

        return "user/login";
    }

}
