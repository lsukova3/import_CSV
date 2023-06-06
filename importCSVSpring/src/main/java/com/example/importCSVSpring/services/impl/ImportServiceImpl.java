package com.example.importCSVSpring.services.impl;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.model.Person;
import com.example.importCSVSpring.repositories.ImportRepository;
import com.example.importCSVSpring.services.ImportService;
import com.example.importCSVSpring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    ImportRepository repository;
    @Autowired
    PersonService personService;

    @Override
    public void initFromDirectory() throws IOException {
        String dir = "C:\\Users\\lenka\\IdeaProjects\\importCSVSpring\\csv2sql\\csv";
        Path path = Paths.get(dir);
        File directory = path.toFile();

        for(File f: directory.listFiles()){
            if(f.getName().matches("people[0-9]*\\.csv")){
                AnImport anImport = new AnImport();
                anImport.setDirectory(dir);
                anImport.setFileName(f.getName());
                anImport.setFileSize(Files.size(f.toPath()));
                anImport.setTimestamp(Timestamp.from(Instant.now()));
                anImport.setImportId(repository.save(anImport).getImportId());
                personService.initFromFile(f.getAbsolutePath(), anImport);
                //uloží se, zda je person file OK
                repository.save(anImport).getImportId();
            }
        }
    }

    @Override
    public List<AnImport> getList() {
        Iterator<AnImport> iterator;
        iterator = repository.findAll().iterator();


        List<AnImport> importList = new ArrayList<>();
        while(iterator.hasNext()){
            importList.add(iterator.next());
        }
        return importList;
    }

    @Override
    public List<AnImport> findByKeyword(String keyword) {
        return repository.findByKeyword(keyword);
    }

}
