package com.example.importCSVSpring.controllers;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.services.ImportService;
import com.example.importCSVSpring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ImportController {
    @Autowired
    ImportService importService;
    @Autowired
    PersonService personService;

    @GetMapping("/index")
    public String getPageImport(Model model){
        List<AnImport> importList = importService.getList();
        model.addAttribute("importList", importList);
        model.addAttribute("totalRows", importList.size());
        return "index";
    }


}
