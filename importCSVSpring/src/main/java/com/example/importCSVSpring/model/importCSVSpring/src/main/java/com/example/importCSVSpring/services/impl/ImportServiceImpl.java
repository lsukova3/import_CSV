package com.example.importCSVSpring.services.impl;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.repositories.ImportRepository;
import com.example.importCSVSpring.services.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    ImportRepository repository;

    @Override
    public void initFromDirectory() {
        Path path = Paths.get("C:\\Users\\lenka\\IdeaProjects\\importCSVSpring\\csv2sql\\csv");
        File directory = path.toFile();

        for(File f: directory.listFiles()){
            if(f.getName().matches("people[0-9]*\\.csv")){
                AnImport anImport = new AnImport();
                anImport.setFileName(f.getAbsolutePath());
                anImport.setTimestamp(Timestamp.from(Instant.now()));
                repository.save(anImport);
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



}
