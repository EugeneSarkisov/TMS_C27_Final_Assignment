package com.teachmeskills.final_assignment.parser;

import com.teachmeskills.final_assignment.consts.Consts;
import com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.validator.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InvoiceParser {
    public static void parseInvoiceInfo(File file){
//        parseInvoiceInfo(sortInvoice(Validator.folderValidator(file)));
    }

    private static List<File> sortInvoice (File file) {
        List<File> invoices = new ArrayList<>(List.of(file.listFiles()));
        Iterator<File> invoiceIter = invoices.iterator();
        while (invoiceIter.hasNext()) {
            File invoice = invoiceIter.next();
            if (!invoice.getName().matches("(^INVOICE_[0-9]{2}_2023\\.txt$)|(^[a-z]{7}_[0-9]{2}_2023\\.txt$)")) {
                try {
                    Files.move(Paths.get(invoice.getPath()),
                            Paths.get("D:\\1 Java\\TMS_C27_Final_Assignment\\testFolder" + invoice.getName()),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Error while moving file: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Something went wrong... Please, contact with the administrator.");
                }
                invoiceIter.remove();
            }
        }
        return invoices;
    }

    private static void parseInvoiceInfo(List<File> invoiceList) {
        List<String> invoiceDocList = new ArrayList<>();
        for (File check : invoiceList) {
            try (BufferedReader checkReader = new BufferedReader(new FileReader(check))) {
                String line;
                while ((line = checkReader.readLine()) != null) {
                    if (line.contains("Total")) {
                        invoiceDocList.add(line);
                    }
                }
            } catch (Exception e) {
                System.out.println(UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE);
                Logger.loggerWriteError(e);
            }
        }
        double invoiceSum = 0.0;
        for (String bill : invoiceDocList) {
            invoiceSum += Double.parseDouble(bill.substring(11).trim());
            System.out.println(invoiceSum);
        }
    }
}
