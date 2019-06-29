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
import java.util.List;


@Controller
@RequestMapping(value = "date")
public class FooddayController {

    @Autowired
    private FooddayDao fooddayDao;

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "")
    public String index(Model model,
                        @CookieValue(value="user", defaultValue = "none") String name) {

        //User u = userDao.findByName(name).get(0);
        model.addAttribute("title", "Food Days");
        model.addAttribute("dates", fooddayDao.findAll());

        return "date/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddDateForm(Model model,
                                     @CookieValue(value="user", defaultValue = "none") String name) {

        if(name.equals("none")) {
            return "redirect:/user/index";
        }

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

        return "redirect:view/" + date.getId();
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveDateForm(Model model,
                                        @CookieValue(value="user", defaultValue = "none") String name) {

        if(name.equals("none")) {
            return "redirect:/user/index";
        }

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
    public String viewDate(Model model, @PathVariable int dateId,
                           @CookieValue(value="user", defaultValue = "none") String name) {

        Foodday date = fooddayDao.findOne(dateId);
        model.addAttribute("date", date);
        model.addAttribute("users", date.getUsers());
        model.addAttribute("dateId", date.getId());

        return "date/view";
    }


    @RequestMapping(value = "add-food/{dateId}", method = RequestMethod.GET)
    public String displayAddUserForm(Model model, @PathVariable int dateId,
                                     @ModelAttribute String food,
                                     @CookieValue(value="user", defaultValue = "none") String name) {

        if(name.equals("none")) {
            return "redirect:/user/index";
        }

        Foodday date = fooddayDao.findOne(dateId);
        AddUserForm form = new AddUserForm(userDao.findAll(), food, date);

        model.addAttribute("title", "Add Person to " + date.getDate());
        //model.addAttribute("date", date);
        model.addAttribute("form", form);

        return "date/add-food";
    }


    @RequestMapping(value = "add-food", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid AddUserForm form, Errors errors,
                                     @ModelAttribute String food, @RequestParam int dateId, @RequestParam int userId, Model model) {

        if (errors.hasErrors()) {
            Foodday date = fooddayDao.findOne(dateId);
            //AddUserForm form = new AddUserForm(userDao.findAll(), food, date);

            //model.addAttribute("title", "Add Person");
            model.addAttribute("title", "Add Person to " + date.getDate());
            model.addAttribute("date", date);
            model.addAttribute("form", form);

            return "date/add-food";
        }

        User user = userDao.findOne(userId);
        Foodday date = fooddayDao.findOne(form.getDateId());
        date.addPerson(user);
        fooddayDao.save(date);

        return "redirect:/date/view/" + date.getId();
    }


    @RequestMapping(value = "remove-food/{dateId}", method = RequestMethod.GET)
    public String displayRemovePersonForm(Model model,
                                          @CookieValue(value="user", defaultValue = "none") String name) {

        if(name.equals("none")) {
            return "redirect:/user/index";
        }

        model.addAttribute("title", "Remove Person");
        model.addAttribute("persons", userDao.findAll());

        return "date/remove-food";
    }


    @RequestMapping(value = "remove-food", method = RequestMethod.POST)
    public String processRemovePersonForm(@RequestParam int[] userIds,
                                          @ModelAttribute Foodday date) {
        for (int userId : userIds) {

            userDao.delete(userId);
        }

        return "redirect:view/" + date.getId();
    }


    @RequestMapping(value = "edit-food/{personId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int dateId,
                                  @RequestParam int userId, @ModelAttribute String food,
                                  @CookieValue(value="user", defaultValue = "none") String name) {

        if(name.equals("none")) {
            return "redirect:/user/index";
        }

        User aUser = userDao.findOne(userId);
        Foodday date = fooddayDao.findOne(dateId);
        AddUserForm form = new AddUserForm(userDao.findAll(), food, date);

        if (aUser == null) {
            return "redirect:/date/view/";
        }

        model.addAttribute("title", "Edit Information");
        model.addAttribute("user.name", aUser);
        return "date/edit-food";
    }


    @RequestMapping(value = "edit-food", method = RequestMethod.POST)
    public String processEditForm(Model model, @ModelAttribute @Valid User user,
                                  Errors errors, @RequestParam int userId,
                                  @PathVariable int dateId, @ModelAttribute String food) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Edit Information");
            model.addAttribute("user.name", user);

            return "date/edit-food";
        }

        User aUser = userDao.findOne(userId);
        aUser.setName(user.getName());
        Foodday date = fooddayDao.findOne(dateId);
        AddUserForm form = new AddUserForm(userDao.findAll(), food, date);
        form.setFood(form.getFood());
        userDao.save(aUser);

        return "redirect:/date/view/" + dateId;
    }

}
