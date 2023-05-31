package com.example.importCSVSpring.controllers;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/detail")
    public String getPageDetail(Model model, String keyword){
        if(keyword==null||keyword.equals("")){
          List<Person> personList = personService.getList();
          model.addAttribute("personList", personList);
        } else {
          List<Person> personList = personService.findByKeyword(keyword);
          model.addAttribute("personList", personList);
        }
        return "detail";
    }

    @GetMapping("/detail/{importId}/{fileName}")
    public String getPageDetail(Model model,
                                @PathVariable("importId") Long importId,
                                @PathVariable("fileName") String fileName,
                                String keyword){
        if(keyword==null||keyword.equals("")){
            List<Person> personList = personService.findByImportId(importId);
            model.addAttribute("personList", personList);
        } else {
            List<Person> personList = personService.findByImportIdKeyword(importId,keyword);
            model.addAttribute("personList", personList);
        }
        model.addAttribute("fileName", fileName);
        model.addAttribute("importId", importId);
        return "detail";
    }
}
