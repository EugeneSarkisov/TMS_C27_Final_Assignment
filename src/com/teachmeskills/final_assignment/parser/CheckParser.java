package com.teachmeskills.final_assignment.parser;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CheckParser {
    public static void parseCheckInfo() {
        //TODO validationMethod
        sortChecks();
        parseInfo();
    }

    private static List<File> sortChecks() {
        //remove all unnecessary files
        File checkFolder = new File("data/checks");
        List<File> checks = new ArrayList<>(List.of(checkFolder.listFiles()));
        Iterator<File> checkIter = checks.iterator();
        while (checkIter.hasNext()) {
            File check = checkIter.next();
            if (!check.getName().matches("^2023_Electric_Bill_[0-9]{2,}[.]{1}txt$")) {
                try {
                    Files.move(Paths.get(check.getPath()),
                            Paths.get("data/temp/garbageChecks/" + check.getName()),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Error while moving file: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Something went wrong... Please, contact with the administrator.");
                }
                checkIter.remove();
            }
        }
        return checks;
    }

    private static void parseInfo() {
        //parsing check info
        List<String> billList = new ArrayList<>();
        for (File check : sortChecks()) {
            try (BufferedReader checkReader = new BufferedReader(new FileReader(check))) {
                String line;
                while ((line = checkReader.readLine()) != null) {
                    if (line.startsWith("Bill")) {
                        billList.add(line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //summing all necessary bills
        double checkSum = 0.0;
        for (String bill : billList) {
            //TODO currencyConvertor
            checkSum += Double.parseDouble(bill.substring(23).trim().replace(",", "."));
            System.out.println(checkSum);
        }
    }
}

