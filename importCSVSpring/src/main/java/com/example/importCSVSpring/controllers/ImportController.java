package com.example.importCSVSpring.controllers;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.repositories.ImportRepository;
import com.example.importCSVSpring.services.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ImportController {
    @Autowired
    ImportService service;

    @GetMapping("/index")
    public String getPageImport(Model model){
        List<AnImport> importList = service.getList();
        model.addAttribute("importList", importList);
        model.addAttribute("totalRows", importList.size());
        return "index";
    }
}
