package com.example.importCSVSpring.controllers;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
          model.addAttribute("currentPage", 1);
          model.addAttribute("totalPages", 1);
          model.addAttribute("totalItems", personList.size());
        } else {
          List<Person> personList = personService.findByKeyword(keyword);
          model.addAttribute("personList", personList);
        }

        return "detail";
    }
    @GetMapping("/detail/page/{pageNumber}/{pageSize}")
    public String getPageDetail(Model model,
                                String keyword,
                                @PathVariable("pageNumber") int pageNumber,
                                @PathVariable("pageSize") int pageSize){
        if(keyword==null||keyword.equals("")){
            Page<Person> personList = personService.getList(pageNumber, pageSize);
            model.addAttribute("personList", personList);
            model.addAttribute("currentPage", pageNumber);
            model.addAttribute("totalPages", personList.getTotalPages());
            model.addAttribute("totalItems", personList.getTotalElements());
        } else {
            Page<Person> personList = personService.findByKeyword(keyword, pageNumber, pageSize);
            model.addAttribute("personList", personList);
            model.addAttribute("currentPage", pageNumber);
            model.addAttribute("totalPages", personList.getTotalPages());
            model.addAttribute("totalItems", personList.getTotalElements());
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
    @GetMapping("/detail/{importId}/{fileName}/page/{pageNumber}/{pageSize}")
    public String getPageDetail(Model model,
                                @PathVariable("importId") Long importId,
                                @PathVariable("fileName") String fileName,
                                @PathVariable("pageNumber") int pageNumber,
                                @PathVariable("pageSize") int pageSize,
                                String keyword){
        if(keyword==null||keyword.equals("")){
            Page<Person> personList = personService.findByImportId(importId, pageNumber, pageSize);
            model.addAttribute("personList", personList);
            model.addAttribute("currentPage", pageNumber);
            model.addAttribute("totalPages", personList.getTotalPages());
            model.addAttribute("totalItems", personList.getTotalElements());
        } else {
            Page<Person> personList = personService.findByImportIdKeyword(importId,keyword, pageNumber, pageSize);
            model.addAttribute("personList", personList);
            model.addAttribute("currentPage", pageNumber);
            model.addAttribute("totalPages", personList.getTotalPages());
            model.addAttribute("totalItems", personList.getTotalElements());
        }
        model.addAttribute("fileName", fileName);
        model.addAttribute("importId", importId);
        return "detail";
    }

    @GetMapping("/detail/view/{id}")
    public String getRecord(Model model, @PathVariable("id") Long id){
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("mode", "view");
        return "record";
    }

    @GetMapping("/detail/edit/{id}")
    public String editRecord(Model model, @PathVariable("id") Long id){
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("mode", "edit");
        return "record";
    }
    @PostMapping("detail/edit")
    public String editRecord(Model model, @ModelAttribute("person") Person person){
        personService.update(person);
        return "redirect:/detail";
    }

    @GetMapping("/detail/{importId}/{fileName}/edit/{id}")
    public String editRecord(Model model,
                             @PathVariable("importId") Long importId,
                             @PathVariable("fileName") String fileName,
                             @PathVariable("id") Long id){
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("importId", importId);
        model.addAttribute("fileName", fileName);
        model.addAttribute("mode", "edit");
        return "record";
    }
    @PostMapping("detail/{importId}/{fileName}/edit")
    public String editRecord(Model model,
                             @ModelAttribute("person") Person person,
                             @PathVariable("importId") Long importId,
                             @PathVariable("fileName") String fileName){
        personService.update(person);
        return "redirect:/detail/" + importId + "/" + fileName;
    }

    @GetMapping("detail/delete/{id}")
    public String deleteRecord (Model model, @PathVariable("id") Long id){
        personService.delete(id);
        model.addAttribute("personId", null);
        return "redirect:/detail";
    }

    @GetMapping("detail/{importId}/{fileName}/delete/{id}")
    public String deleteRecord (Model model,
                                @PathVariable("importId") Long importId,
                                @PathVariable("fileName") String fileName,
                                @PathVariable("id") Long id){
        personService.delete(id);
        return "redirect:/detail/" + importId + "/" + fileName;
    }
}
