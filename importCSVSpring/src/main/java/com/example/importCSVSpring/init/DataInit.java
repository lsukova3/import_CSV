package com.example.importCSVSpring.init;

import com.example.importCSVSpring.model.AnImport;
import com.example.importCSVSpring.repositories.ImportRepository;
import com.example.importCSVSpring.services.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.sql.Timestamp;


@Component
public class DataInit  implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ImportService service;

    /**
     * Inicializuje import
     * Z adresáře csv načte soubory personx.csv a uloží do tabulky import
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            service.initFromDirectory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
