package com.example.importCSVSpring.services.impl;

import com.example.importCSVSpring.exceptions.CSVException;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.repositories.PersonRepository;
import com.example.importCSVSpring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    public PersonRepository repository;

    @Override
    public void initFromFile(String fileName) {

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String radek;
            int linenumber = 0;
            while((radek = br.readLine()) != null){
                linenumber++;
                String[] radekSplit = radek.split("\\,");
                if(radekSplit.length==9) {
                    Person p = new Person();
                    p.setUserId(radekSplit[1]);
                    p.setFirstName(radekSplit[2]);
                    p.setLastName(radekSplit[3]);
                    p.setSex(radekSplit[4]);
                    p.setEmail(radekSplit[5]);
                    p.setPhone(radekSplit[6]);
                    //p.setDateOfBirth(Date.valueOf(radekSplit[7]));
                    p.setDateOfBirth(Date.valueOf(LocalDate.now()));
                    p.setJobTitle(radekSplit[8]);
                    repository.save(p);

                } else if(!radek.equals("")&&radek.matches("\\S*")) {  //pokud je na řádku nějaký non-whitespace znak
                    throw new CSVException(fileName, linenumber);
                }
            }
        }catch(IOException | CSVException e){
            System.err.println(e.getMessage());
        }
    }
}
