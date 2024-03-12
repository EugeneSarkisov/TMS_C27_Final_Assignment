package com.teachmeskills.final_assignment.parser.parsers;

import com.teachmeskills.final_assignment.custom_exceptions.InvoicesFolderNotExistException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;
import com.teachmeskills.final_assignment.util.mover.FileMover;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.validator.validations.DirectoryValidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.teachmeskills.final_assignment.util.consts.messages.InvoiceParserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.CHECK_THE_ERROR_LOG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.regex.Regex.INVOICE_REGEX;

public class InvoiceParser {
    public static void parseInvoiceInfo(File file) {
        try {
            parseInvoiceInfo(sortInvoice(findInvoiceFolder(file)));
        } catch (InvoicesFolderNotExistException e) {
            Logger.loggerWriteError(e);
        }
    }
    private static File findInvoiceFolder(File file) throws InvoicesFolderNotExistException {
        Logger.loggerWrite(CHECK_INVOICES_FOLDER_MESSAGE);
        List<File> dataFolders = Arrays.stream(file.listFiles())
                .filter(n -> n.getName().equals("invoices"))
                .toList();
        if (!dataFolders.isEmpty()) {
            return dataFolders.get(0);
        } else {
            throw new InvoicesFolderNotExistException("Invoices folder doesn't exist");
        }
    }
    private static List<File> sortInvoice(File file) {
        List<File> invoices = new ArrayList<>(List.of(file.listFiles()));
        try {
            Logger.loggerWrite(ACCESS_INVOICE_FOLDER_MESSAGE);
            DirectoryValidation.isDirectoryEmptyValidation(file);
            Logger.loggerWrite(REMOVING_GARBAGE_INVOICE_MESSAGE);
            Iterator<File> invoiceIter = invoices.iterator();
            while (invoiceIter.hasNext()) {
                File invoice = invoiceIter.next();
                if (!invoice.getName().matches(INVOICE_REGEX)) {
                    FileMover.moveFile(invoice);
                    invoiceIter.remove();
                }
            }
        } catch (IsDirectoryEmptyException e) {
            Logger.loggerWriteError(e);
        }
        return invoices;
    }

    private static double parseInvoiceInfo(List<File> invoiceList) {
        //check is our collection empty
        if(invoiceList.isEmpty()){
            Logger.loggerWrite(NO_VALUABLE_INVOICES);
            return 0;
        }
        //parsing info from orders
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
                System.err.println(SOMETHING_WENT_WRONG_MESSAGE);
                Logger.loggerWriteError(e);
                Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            }
        }
        Logger.loggerWrite(PARSING_INVOICE_INFO_COMPLETE_MESSAGE);
        //summing all orders bills
        double invoiceSum = 0.0;
        for (String bill : invoiceDocList) {
            invoiceSum += Double.parseDouble(bill.replaceAll("[a-zA-Z$]", "").trim());
            System.err.println(invoiceSum);
        }
        Logger.loggerWrite(TRANSFER_INVOICE_INFO_MESSAGE);
        return invoiceSum;
    }
}
