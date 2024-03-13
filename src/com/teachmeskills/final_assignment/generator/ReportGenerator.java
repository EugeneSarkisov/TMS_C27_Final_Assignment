package com.teachmeskills.final_assignment.generator;

import com.teachmeskills.final_assignment.util.consts.path.Path;
import com.teachmeskills.final_assignment.util.date_sample.DateAndTime;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;

public class ReportGenerator {

    public static void report(double checkSum, double invoiceSum, double orderSum) {

        try (Writer output = new FileWriter(Path.PATH_TO_REPORT)) {
            output.write("REPORT " + DateAndTime.getDateAndTime() + "\n");
            output.write(" " + "\n");
            double totalSum = checkSum + invoiceSum + orderSum;
            output.write("TOTAL BILLS SUM " + totalSum + "\n");
            output.write(" " + "\n");
            output.write("TOTAL CHECKS SUM " + checkSum + "\n");
            output.write("TOTAL INVOICES SUM " + invoiceSum + "\n");
            output.write("TOTAL ORDERS SUM " + orderSum + "\n");


        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }
}