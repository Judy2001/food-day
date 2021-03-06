package org.launchcode.foodday.controllers;


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

        model.addAttribute("title", "Sign Up");
        model.addAttribute(new User());

        return "user/signup";
    }


    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processSignupForm(@ModelAttribute @Valid User user, Errors errors,
                                    String verify, Model model) {

        List<User> nameExists = userDao.findByName(user.getName());

        if (!errors.hasErrors() && user.getPassword().equals(verify) && nameExists.isEmpty()) {
            model.addAttribute("user", user);
            userDao.save(user);

            return "redirect:/date";

        } else {

            model.addAttribute("title", "Sign Up");
            model.addAttribute("user", user);

            if (!user.getPassword().equals(verify)) {
                model.addAttribute("message", "Passwords must match");
                user.setPassword("");
            }

            if(!nameExists.isEmpty()) {
                model.addAttribute("message", "Username is taken; please select another username");
                user.setName("");
            }

            return "user/signup";
        }
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {

        model.addAttribute("title", "Food Day!");
        model.addAttribute(new User());

        return "user/login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid User user, Errors errors,
                                   HttpServletResponse response, Model model) {

        List<User> u = userDao.findByName(user.getName());
        Iterable<User> users = userDao.findAll();

        if (u.isEmpty()) {

            model.addAttribute("error", "Invalid name");
            model.addAttribute("title", "Login to Food Day!");

            return "user/login";
        }

        User loggedIn = u.get(0);

        if (!errors.hasErrors() && loggedIn.getPassword().equals(user.getPassword())) {

            Cookie c = new Cookie("user", user.getName());
            c.setPath("/");
            c.setMaxAge(3600);
            response.addCookie(c);

            return "redirect:/date";

        } else {

            model.addAttribute("error", "Invalid password");
            model.addAttribute("title", "Login to Food Day!");

            return "user/login";
        }
    }


    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response,
                         Model model) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                c.setMaxAge(0);
                c.setPath("/");
                response.addCookie(c);
            }
        }

        model.addAttribute("title", "Food Day!");

        return "user/index";
    }

}
