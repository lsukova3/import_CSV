package com.example.importCSVSpring.services;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.repositories.ImportRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface ImportService {

    /**
     * Naplní tabulku import
     */
    void initFromDirectory() throws IOException;

    /**
     * Vrátí seznam všech importních souborů
     * @return seznam
     */
    List<AnImport> getList();

    /**
     * Vrátí seznam podle klíčového slova
     * @param keyword klíčové slovo
     * @return seznam
     */
    List<AnImport> findByKeyword(String keyword);
}
