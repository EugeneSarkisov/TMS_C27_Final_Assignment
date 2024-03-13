package com.teachmeskills.final_assignment.generator;

import com.teachmeskills.final_assignment.util.consts.path.Path;
import com.teachmeskills.final_assignment.util.date_sample.DateAndTime;
import com.teachmeskills.final_assignment.util.logger.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static com.teachmeskills.final_assignment.util.consts.messages.GeneratorMessages.END_GENERATE_MESSAGES;
import static com.teachmeskills.final_assignment.util.consts.messages.GeneratorMessages.START_GENERATE_MESSAGES;
import static com.teachmeskills.final_assignment.util.consts.messages.ProgramMessages.END_PROGRAM_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;

public class ReportGenerator {
    public static void report(double checkSum, double invoiceSum, double orderSum) {
        try (Writer output = new FileWriter(Path.PATH_TO_REPORT)) {
            Logger.loggerWrite(START_GENERATE_MESSAGES);
            output.write("REPORT " + DateAndTime.getDateAndTime() + "\n");
            output.write(" " + "\n");
            double totalSum = checkSum + invoiceSum + orderSum;
            output.write("TOTAL BILLS SUM " + totalSum + "\n");
            output.write(" " + "\n");
            output.write("TOTAL CHECKS SUM " + checkSum + "\n");
            output.write("TOTAL INVOICES SUM " + invoiceSum + "\n");
            output.write("TOTAL ORDERS SUM " + orderSum + "\n");
            Logger.loggerWrite(END_GENERATE_MESSAGES);
            System.out.println(END_PROGRAM_MESSAGE);
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
            Logger.loggerWriteError(e);
        }
    }
}