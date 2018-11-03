package org.launchcode.foodday.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("food")
public class PersonController {

    static ArrayList<String> persons = new ArrayList<>();

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("persons", persons);
        model.addAttribute("title", "Food Day");

        return "food/index";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddFoodForm(Model model) {

        model.addAttribute("title", "Add Person");

        return "food/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddFoodForm(@RequestParam String personName) {

        persons.add(personName);

        return "redirect:";
    }

}
