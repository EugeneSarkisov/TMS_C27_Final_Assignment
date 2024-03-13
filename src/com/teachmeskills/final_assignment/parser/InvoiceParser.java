package com.teachmeskills.final_assignment.parser;

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
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_GARBAGE_INVOICES;
import static com.teachmeskills.final_assignment.util.consts.regex.Regex.INVOICE_REGEX;

/**
 * Parse InvoiceFiles and get the sum of bills of all documents.
 * Allow to verification necessary files and remove garbage invoice
 * files to garbage package: data/temp/garbageInvoices.
 *
 * @author Kirril Palianitsa
 */

public class InvoiceParser {
    public static double parseInvoiceInfo(File file) {
        try {
            return parseInvoiceInfo(sortInvoice(findInvoiceFolder(file)));
        } catch (InvoicesFolderNotExistException e) {
            Logger.loggerWriteError(e);
            return 0.0;
        }
    }
    /**
     * findInvoiceFolder checks if folder invoices exist or not. Validation happened with
     * filtering files in package.
     * @param file - get the file from validator;
     * @return - if folder exist - return the file with package;
     * @throws InvoicesFolderNotExistException - if folder not exist;
     */
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

    /**
     * Collect all files from Invoices package into collection "invoices" and
     * sorting it while collection isn't empty. Garbage invoices moving to
     * the temp/garbageInvoices. Return the sort collection of invoices.
     * @param file all invoice files from package
     * @return invoices
     */

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
                    FileMover.moveFile(invoice, PATH_TO_GARBAGE_INVOICES);
                    invoiceIter.remove();
                }
            }
        } catch (IsDirectoryEmptyException e) {
            Logger.loggerWriteError(e);
        }
        return invoices;
    }

    /**
     * Parses sorted documents with for-loop and FileReader. Verification of
     * bill string happening when reader find string with key-word "Total".
     * Next, all strings collect in invoiceDocList, from every string we get
     * value of order and summing it in invoiceSum.
     * @param invoiceList get the sort collection from sortOrder method.
     * @return invoiceSum - all necessary bills summing together.
     */

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
            System.out.println(invoiceSum);
        }
        Logger.loggerWrite(TRANSFER_INVOICE_INFO_MESSAGE);
        return invoiceSum;
    }
}
