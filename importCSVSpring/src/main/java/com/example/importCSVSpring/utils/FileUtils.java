package com.example.importCSVSpring.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static void writeException(String fileName, String radek, int linenumber) throws IOException {
        BufferedWriter pw = null;
        try{
            pw = new BufferedWriter(new FileWriter(fileName+"_bad",true));
            pw.append("(line " + linenumber + ") " + radek);
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
}
