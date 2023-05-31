package com.example.importCSVSpring.services;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;

import java.util.List;

public interface PersonService {
    /**
     * Naplní tabulku person
     * @param fileName jméno csv souboru
     * @param anImport import
     */
    void initFromFile(String fileName, AnImport anImport);

    /**
     * Vrátí seznam všech osob
     * @return seznam
     */
     List<Person> getList();

    /**
     * Vrátí seznam podle id importu
     * @param idImport
     * @return seznam
     */
    List<Person> findByImportId(Long idImport);

    /**
     * Vrátí seznam podle klíčového slova
     * @param keyword
     * @return seznam
     */
    List<Person> findByKeyword(String keyword);

    /**
     * Vrátí seznam podle id importu a klíčového slova
     * @param idImport
     * @param keyword
     * @return seznam
     */
    List<Person> findByImportIdKeyword(Long idImport, String keyword);
}
