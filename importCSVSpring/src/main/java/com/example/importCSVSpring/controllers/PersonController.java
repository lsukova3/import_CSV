package com.example.importCSVSpring.controllers;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/detail")
    public String getPageDetail(Model model){
        List<Person> personList = personService.getList();
        model.addAttribute("personList", personList);
      //  model.addAttribute("totalRows", personList.size());
        return "detail";
    }
}
