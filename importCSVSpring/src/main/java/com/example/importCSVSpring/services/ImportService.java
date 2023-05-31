package com.example.importCSVSpring.services;

import com.example.importCSVSpring.model.AnImport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface ImportService {

    /**
     * Naplní tabulku import
     */
    void initFromDirectory();

    List<AnImport> getList();
}
