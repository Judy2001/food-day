package org.launchcode.foodday.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("food")
public class PersonController {

    static HashMap<String, String> persons = new HashMap<>();

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("persons", persons);
        model.addAttribute("title", "Food Day!");

        return "food/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddFoodForm(Model model) {

        model.addAttribute("title", "Add Person");

        return "food/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddFoodForm(@RequestParam String personName, @RequestParam String foodName) {

        persons.put(personName, foodName);

        return "redirect:";
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePersonForm(Model model) {

        model.addAttribute("persons", persons.keySet());
        model.addAttribute("title", "Remove Person");

        return "food/remove";
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePersonForm(@RequestParam ArrayList<String> person) {

        for (String aPerson : person) {
            persons.remove(aPerson);
        }

        return "redirect:";
    }

}