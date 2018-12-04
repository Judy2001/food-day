package org.launchcode.foodday.controllers;

import org.launchcode.foodday.models.Person;
import org.launchcode.foodday.models.data.PersonDao;
import org.launchcode.foodday.models.FoodDay;
import org.launchcode.foodday.models.data.FoodDayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("food")
public class PersonController {


    @Autowired
    private PersonDao personDao;

    @Autowired
    private FoodDayDao foodDayDao;


    @RequestMapping(value = "")
    public String index(Model model, @ModelAttribute FoodDay date) {

        model.addAttribute("persons", personDao.findAll());
        model.addAttribute("title", "Food Day!");
        model.addAttribute("date", "${foodDay.date}");

        return "food/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPersonForm(Model model) {

        model.addAttribute("title", "Add Person");
        model.addAttribute(new Person());
        model.addAttribute("dates", foodDayDao.findAll());

        return "food/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPersonForm(@ModelAttribute  @Valid Person newPerson, @ModelAttribute FoodDay newFoodDay,
                                       Errors errors, @RequestParam int foodDayId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Person");
            return "food/add";
        }

        FoodDay date = foodDayDao.findOne(foodDayId);
        //newPerson.setFoodDay(date);
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


    @RequestMapping(value = "date", method = RequestMethod.GET)
    public String date(Model model, @RequestParam int id) {

        FoodDay date = foodDayDao.findOne(id);
        List<Person> persons = date.getPersons();
        model.addAttribute("date", date);
        model.addAttribute("title", "Food brought on " + date.getDate());
        return "food/index";
    }

}