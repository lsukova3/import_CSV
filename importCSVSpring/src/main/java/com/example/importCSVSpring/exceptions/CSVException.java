package com.example.importCSVSpring.exceptions;

import com.example.importCSVSpring.utils.FileUtils;

/**
 * Ošetřuje chybu na řádku csv souboru
 */
public class CSVException extends Exception{
    /**
     * Název souboru
     */
    private String filename;

    public CSVException(String filename) {
        super("V souboru " + filename + " se vyskytla chyba.");
    }
}
