package com.example.importCSVSpring.exceptions;

/**
 * Ošetřuje chybu na řádku csv souboru
 */
public class CSVException extends Exception{
    /**
     * Název souboru
     */
    private String filename;
    /**
     * Číslo řádku
     */
    private int line = 0;

    public CSVException(String filename, int line) {
        super("V souboru " + filename + " na řádku " + line + " se vyskytla chyba.");
    }
}
