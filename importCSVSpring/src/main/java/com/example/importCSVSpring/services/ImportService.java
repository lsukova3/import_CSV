package com.example.importCSVSpring.services;

import com.example.importCSVSpring.model.AnImport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface ImportService {

    /**
     * Naplní tabulku import
     */
    void initFromDirectory() throws IOException;

    List<AnImport> getList();
}
