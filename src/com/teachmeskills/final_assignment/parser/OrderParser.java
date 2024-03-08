package com.teachmeskills.final_assignment.parser;

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

public class OrderParser {

    public static void parseOrderInfo(File file){
        parseOrderInfo(sortOrder(Validator.folderValidator(file)));
    }

    private static List<File> sortOrder(File file) {
        //remove all unnecessary files
        List<File> orders = new ArrayList<>(List.of(file.listFiles()));
        Iterator<File> orderIter = orders.iterator();
        while (orderIter.hasNext()) {
            File order = orderIter.next();
            if (!order.getName().toLowerCase().matches("^2023_order_[0-9]{2,}[.]{1}txt$")) {
                try {
                    Files.move(Paths.get(order.getPath()),
                            Paths.get("C:\\Users\\Моргул\\Desktop\\temp\\" + order.getName()),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Error while moving file: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Something went wrong... Please, contact with the administrator.");
                }
                orderIter.remove();
            }
        }
        return orders;
    }

    private static void parseOrderInfo(List<File> orderList) {
        //parsing check info
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
                e.printStackTrace();
            }
        }
        //summing all necessary bills
        double orderSum = 0.0;
        for (String bill : orderBillList) {
            orderSum += Double.parseDouble(bill.substring(11).trim().replace(",", ""));
            System.out.println(orderSum);
        }
    }
}
