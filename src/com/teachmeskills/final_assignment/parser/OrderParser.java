package com.teachmeskills.final_assignment.parser;

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

import static com.teachmeskills.final_assignment.util.consts.messages.OrderParserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_GARBAGE_ORDERS;
import static com.teachmeskills.final_assignment.util.consts.regex.Regex.ORDER_REGEX;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.*;

public class OrderParser {

    public static void parseOrderInfo(File file){
        parseOrderInfo(sortOrder(file));
    }

    private static List<File> sortOrder(File file) {
        //remove all unnecessary files
        Logger.loggerWrite(ACCESS_ORDER_FOLDER_MESSAGE);
        List<File> orders = new ArrayList<>(List.of(file.listFiles()));
        Logger.loggerWrite(REMOVING_GARBAGE_ORDER_MESSAGE);
        Iterator<File> orderIter = orders.iterator();
        while (orderIter.hasNext()) {
            File order = orderIter.next();
            if (!order.getName().toLowerCase().matches(ORDER_REGEX)) {
                try {
                    Files.move(Paths.get(order.getPath()),
                            Paths.get(PATH_TO_GARBAGE_ORDERS + order.getName()),
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
                orderIter.remove();
            }
        }
        Logger.loggerWrite(REMOVING_COMPLETE_MESSAGE);
        return orders;
    }

    private static void parseOrderInfo(List<File> orderList) {
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
    }
}
