package org.launchcode.foodday.controllers;

import org.launchcode.foodday.models.Person;
import org.launchcode.foodday.models.PersonData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
@RequestMapping("food")
public class PersonController {


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("persons", PersonData.getAll());
        model.addAttribute("title", "Food Day!");

        return "food/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPersonForm(Model model) {

        model.addAttribute("title", "Add Person");

        return "food/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPersonForm(@ModelAttribute Person newPerson) {

        PersonData.add(newPerson);

        return "redirect:";
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePersonForm(Model model) {

        model.addAttribute("persons", PersonData.getAll());
        model.addAttribute("title", "Remove Person");

        return "food/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePersonForm(@RequestParam int[] personIds) {

        for (int personId : personIds) {
            PersonData.remove(personId);
        }

        return "redirect:";
    }

}