package com.teachmeskills.final_assignment.parser.parsers;

import com.teachmeskills.final_assignment.custom_exceptions.ChecksFolderNotExistException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;
import com.teachmeskills.final_assignment.custom_exceptions.OrdersFolderNotExistException;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.mover.FileMover;
import com.teachmeskills.final_assignment.util.validator.validations.DirectoryValidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.teachmeskills.final_assignment.util.consts.messages.InvoiceParserLogMessages.NO_VALUABLE_INVOICES;
import static com.teachmeskills.final_assignment.util.consts.messages.OrderParserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_GARBAGE_ORDERS;
import static com.teachmeskills.final_assignment.util.consts.regex.Regex.ORDER_REGEX;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.*;

/**
 * Parse OrderFiles and get the sum of bills of all documents.
 * Allow to verification necessary files and remove garbage order
 * files to garbage package: data/temp/garbageOrders.
 *
 * @author EugeneSarkisov
 */
public class OrderParser {

    public static void parseOrderInfo(File file){
        try {
            parseOrderInfo(sortOrders(findOrderFolder(file)));
        } catch (OrdersFolderNotExistException e) {
            Logger.loggerWriteError(e);
        }
    }
    private static File findOrderFolder(File file) throws OrdersFolderNotExistException {
        Logger.loggerWrite(CHECK_ORDER_FOLDER_MESSAGE);
        List<File> dataFolders = Arrays.stream(file.listFiles())
                .filter(n -> n.getName().equals("orders"))
                .toList();
        if (!dataFolders.isEmpty()) {
            return dataFolders.get(0);
        } else {
            throw new OrdersFolderNotExistException("Orders folder doesn't exist");
        }
    }

    /**
     * Collect all files from Order package into collection "orders" and
     * sorting it while collection isn't empty. Garbage orders moving to
     * the temp/garbageOrders. Return the sort collection of orders.
     * @param file all order files from package
     * @return orders
     */

    private static List<File> sortOrders(File file) {
        List<File> orders = new ArrayList<>(List.of(file.listFiles()));
        //remove all unnecessary files
        try {
            Logger.loggerWrite(ACCESS_ORDER_FOLDER_MESSAGE);
            DirectoryValidation.isDirectoryEmptyValidation(file);
            Logger.loggerWrite(REMOVING_GARBAGE_ORDER_MESSAGE);
            Iterator<File> orderIter = orders.iterator();
            while (orderIter.hasNext()) {
                File order = orderIter.next();
                if (!order.getName().toLowerCase().matches(ORDER_REGEX)) {
                    FileMover.moveFile(order);
                    orderIter.remove();
                }
            }
            Logger.loggerWrite(REMOVING_COMPLETE_MESSAGE);
        } catch (IsDirectoryEmptyException e){
            Logger.loggerWriteError(e);
        }
        return orders;
    }

    /**
     * Parses sorted documents with for-loop and FileReader. Verification of
     * bill string happening when reader find string with key-word "Total".
     * Next, all strings collect in orderBillList, from every string we get
     * value of order and summing it in orderSum.
     * @param orderList get the sort collection from sortOrder method.
     */

    private static double parseOrderInfo(List<File> orderList) {
        if(orderList.isEmpty()){
            Logger.loggerWrite(NO_VALUABLE_INVOICES);
            return 0;
        }
        //parsing check info
        Logger.loggerWrite(PARSING_ORDER_INFO_MESSAGE);
        List<String> orderBillList = new ArrayList<>();
        for (File check : orderList) {
            try (BufferedReader checkReader = new BufferedReader(new FileReader(check))) {
                String line;
                while ((line = checkReader.readLine()) != null) {
                    if (line.contains("Total")) {
                        orderBillList.add(line);
                    }
                }
            } catch (Exception e) {
                System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
                Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
                Logger.loggerWriteError(e);
            }
        }
        Logger.loggerWrite(PARSING_ORDER_INFO_COMPLETE_MESSAGE);
        //summing all necessary bills
        double orderSum = 0.0;
        for (String bill : orderBillList) {
            orderSum += Double.parseDouble(bill.substring(11).trim().replace(",", ""));
            System.out.println(orderSum);
        }
        Logger.loggerWrite(TRANSFER_ORDER_INFO_MESSAGE);
        return orderSum;
    }
}
