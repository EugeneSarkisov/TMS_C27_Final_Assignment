package com.teachmeskills.final_assignment.parser;

import com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages;
import com.teachmeskills.final_assignment.util.logger.Logger;

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

import static com.teachmeskills.final_assignment.util.consts.messages.InvoiceParserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.CHECK_THE_ERROR_LOG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_GARBAGE_INVOICES;
import static com.teachmeskills.final_assignment.util.consts.regex.Regex.INVOICE_REGEX;

public class InvoiceParser {
    public static void parseInvoiceInfo(File file) {
        parseInvoiceInfo(sortInvoice(file));
    }

    private static List<File> sortInvoice(File file) {
        Logger.loggerWrite(ACCESS_INVOICE_FOLDER_MESSAGE);
        List<File> invoices = new ArrayList<>(List.of(file.listFiles()));
        Logger.loggerWrite(REMOVING_GARBAGE_INVOICE_MESSAGE);
        Iterator<File> invoiceIter = invoices.iterator();
        while (invoiceIter.hasNext()) {
            File invoice = invoiceIter.next();
            if (!invoice.getName().matches(INVOICE_REGEX)) {
                try {
                    Files.move(Paths.get(invoice.getPath()),
                            Paths.get(PATH_TO_GARBAGE_INVOICES + invoice.getName()),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println(ERROR_WHILE_MOVING_FILE_MESSAGE + e.getMessage());
                    Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
                    Logger.loggerWriteError(e);
                } catch (Exception e) {
                    System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
                    Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
                    Logger.loggerWriteError(e);
                }
                invoiceIter.remove();
            }
        }
        return invoices;
    }

    private static void parseInvoiceInfo(List<File> invoiceList) {
        Logger.loggerWrite(PARSING_INVOICE_INFO_MESSAGE);
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
                System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
                Logger.loggerWriteError(e);
                Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            }
        }
        Logger.loggerWrite(PARSING_INVOICE_INFO_COMPLETE_MESSAGE);
        double invoiceSum = 0.0;
        for (String bill : invoiceDocList) {
            invoiceSum += Double.parseDouble(bill.substring(11).trim());
            System.out.println(invoiceSum);
        }
    }
}
