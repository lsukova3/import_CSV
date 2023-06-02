package com.example.importCSVSpring.services;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import org.springframework.data.domain.Page;

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
     * Vrátí požadovanou stránku
     * @param pageNumber číslo stránky
     * @param pageSize velikost stránky
     * @return seznam
     */
    Page<Person> getList(int pageNumber, int pageSize);

    /**
     * Vrátí seznam podle id importu
     * @param idImport
     * @return seznam
     */
    List<Person> findByImportId(Long idImport);

    /**
     * Vrátí seznam podle id importu
     * @param idImport
     * @param pageNumber číslo stránky
     * @param pageSize velikost stránky
     * @return seznam
     */
    Page<Person> findByImportId(Long idImport, int pageNumber, int pageSize);

    /**
     * Vrátí seznam podle klíčového slova
     * @param keyword
     * @return seznam
     */
    List<Person> findByKeyword(String keyword);

    /**
     * Vrátí seznam podle klíčového slova
     * @param keyword
     * @param pageNumber číslo stránky
     * @param pageSize velikost stránky
     * @return seznam
     */
    Page<Person> findByKeyword(String keyword, int pageNumber, int pageSize);

    /**
     * Vrátí seznam podle id importu a klíčového slova
     * @param idImport
     * @param keyword
     * @return seznam
     */
    List<Person> findByImportIdKeyword(Long idImport, String keyword);

    /**
     * Vrátí seznam podle id importu a klíčového slova
     * @param idImport
     * @param keyword
     * @param pageNumber číslo stránky
     * @param pageSize velikost stránky
     * @return seznam
     */
    Page<Person> findByImportIdKeyword(Long idImport, String keyword, int pageNumber, int pageSize);

}
