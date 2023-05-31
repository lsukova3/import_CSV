package com.example.importCSVSpring.services;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PersonService {
    /**
     * Naplní tabulku person
     * @param fileName jméno csv souboru
     */
    void initFromFile(String fileName, AnImport anImport);

    public List<Person> getList();

}
