package com.example.importCSVSpring.services.impl;

import com.example.importCSVSpring.exceptions.CSVException;
import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.repositories.PersonRepository;
import com.example.importCSVSpring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    public PersonRepository repository;

    @Override
    public void initFromFile(String fileName, AnImport anImport) {

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String radek;
            int linenumber = 0;
            while((radek = br.readLine()) != null){
                String[] radekSplit = radek.split("\\,");
                //vynech záhlaví
                if (++linenumber==1){
                    continue;
                }
                //řádek má očekávaný počet hodnot
                else if(radekSplit.length==9) {
                    Person p = new Person();
                    p.setIndex(Long.parseLong(radekSplit[0]));
                    p.setUserId(radekSplit[1]);
                    p.setFirstName(radekSplit[2]);
                    p.setLastName(radekSplit[3]);
                    p.setSex(radekSplit[4]);
                    p.setEmail(radekSplit[5]);
                    p.setPhone(radekSplit[6]);
                    p.setDateOfBirth(Date.valueOf(radekSplit[7]));
                    p.setJobTitle(radekSplit[8]);
                    p.setAnImport(anImport);
                    repository.save(p);

                }
                //řádek není prázdný a obsahuje něco jiného než bílé znaky, vyhoď chybu
                else if(!radek.equals("")&&radek.matches("\\S*")) {
                    throw new CSVException(fileName, linenumber);
                }
            }
        }catch(IOException | CSVException e){
            System.err.println(e.getMessage());
        }
    }

    //nestránkovaný
    @Override
    public List<Person> getList() {
        Iterator<Person> iterator;
        iterator = repository.findAll().iterator();

        List<Person> personList = new ArrayList<>();
        while(iterator.hasNext()){
            personList.add(iterator.next());
        }
        return personList;
    }

    //stránkovaný
    @Override
    public Page<Person> getList(int pageNumber, int pageSize) {
        List<Person> personList = getList();
        return new PageImpl<Person>(findPage(personList, pageNumber-1,pageSize), PageRequest.of(pageNumber-1,pageSize), personList.size());
    }

    @Override
    public List<Person> findByImportId(Long idImport) {
        return repository.findByImportId(idImport);
    }

    @Override
    public Page<Person> findByImportId(Long idImport, int pageNumber, int pageSize){
        List<Person> personList = findByImportId(idImport);
        return new PageImpl<Person>(findPage(findByImportId(idImport), pageNumber-1, pageSize), PageRequest.of(pageNumber-1,pageSize), personList.size());
    }

    @Override
    public List<Person> findByKeyword(String keyword) {
        return repository.findByKeyword(keyword);
    }

    @Override
    public Page<Person> findByKeyword(String keyword, int pageNumber, int pageSize) {
        List<Person> personList = findByKeyword(keyword);
        return new PageImpl<Person>(findPage(findByKeyword(keyword), pageNumber-1, pageSize), PageRequest.of(pageNumber-1,pageSize),personList.size());
    }

    @Override
    public List<Person> findByImportIdKeyword(Long idImport, String keyword) {
        return repository.findByImportIdKeyword(idImport, keyword);
    }

    @Override
    public Page<Person> findByImportIdKeyword(Long idImport, String keyword, int pageNumber, int pageSize){
        List<Person> personList = findByImportIdKeyword(idImport, keyword);
        return new PageImpl<Person>(findPage(findByImportIdKeyword(idImport, keyword), pageNumber, pageSize), PageRequest.of(pageNumber, pageSize), personList.size());
    }

    /**
     * Vrátí požadovanou stránku ze zadaného seznamu
     * @param personList seznam
     * @param pageNumber stránka
     * @param pageSize velikost stránky
     * @return seznam záznamů na stránce
     */
    private List<Person> findPage(List<Person> personList, int pageNumber, int pageSize){
        int startItem = pageNumber * pageSize;
        List<Person> list;

        if (personList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, personList.size());
            list = personList.subList(startItem, toIndex);
        }

        return list;
    }

}
