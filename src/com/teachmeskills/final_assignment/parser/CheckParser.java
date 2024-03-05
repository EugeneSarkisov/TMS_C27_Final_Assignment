package com.teachmeskills.final_assignment.parser;

import com.teachmeskills.final_assignment.custom_exceptions.EmptyFolderException;
import com.teachmeskills.final_assignment.validation.DirectoryValidation;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CheckParser {
    public static void parseCheckInfo(){
        File checkFolder = new File("data/checks");
        try {
            //making validations on folder
            //DirectoryValidation.isDirectoryValidation(checkFolder);
            DirectoryValidation.isEmptyValidation(checkFolder);
            //remove all unnecessary files
            List<File> checks = new ArrayList<>(Arrays.asList(checkFolder.listFiles()));
            Iterator<File> checkIter = checks.iterator();
            while (checkIter.hasNext()){
                File checkList = checkIter.next();
                if (!checkList.getName().endsWith(".txt") || !checkList.getName().startsWith("2023")){
                    checkIter.remove();
                }
            }
            //read all necessary checks and get strings with bill sum
            List<String> billList = new ArrayList<>();
            for (File files : checks){
                BufferedReader checkReader = new BufferedReader(new FileReader(files));
                String line;
                while ((line = checkReader.readLine()) != null){
                    if (line.startsWith("Bill")){
                        billList.add(line);
                    }
                }
                checkReader.close();
            }
            //summing the bill sums
            double checkSum = 0.0;
            for (String bill : billList) {
                checkSum += Double.parseDouble(bill.substring(23).trim().replace(",", "."));
                System.out.println(checkSum);
            }
        } catch (EmptyFolderException e) {
            System.err.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        parseCheckInfo();
    }
}
