package com.example.importCSVSpring.services;

import com.example.importCSVSpring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface PersonService {
    /**
     * Naplní tabulku person
     * @param fileName jméno csv souboru
     */
    void initFromFile(String fileName);

}
