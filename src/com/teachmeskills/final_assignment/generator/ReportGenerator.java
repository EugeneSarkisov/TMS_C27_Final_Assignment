package com.teachmeskills.final_assignment.generator;

import com.teachmeskills.final_assignment.util.consts.path.Path;
import com.teachmeskills.final_assignment.util.date_sample.DateAndTime;
import com.teachmeskills.final_assignment.util.logger.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static com.teachmeskills.final_assignment.util.consts.messages.GeneratorMessages.*;
import static com.teachmeskills.final_assignment.util.consts.messages.ProgramMessages.END_PROGRAM_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;

/**
 * Uses the report method for creating report information on the results of programme execution
 *
 * @author KirillPalianitsa
 */

public class ReportGenerator {

    /**
     * Takes data from parsers as input, calculates the total and generates a report in a separate folder
     *
     * @param checkSum   data from CheckParser
     * @param invoiceSum data from InvoiceParser
     * @param orderSum   data from OrderParser
     */
    public static void report(double checkSum, double invoiceSum, double orderSum) {
        if(checkSum == 0 || invoiceSum == 0 || orderSum == 0){
            System.err.println(EMPTY_DIRECTORY_MESSAGE);
        } else {
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
                System.out.println(ERROR_WHILE_GENERATING_REPORT_MESSAGE);
                Logger.loggerWrite(ERROR_WHILE_GENERATING_REPORT_MESSAGE);
                Logger.loggerWriteError(e);
            } catch (Exception e){
                System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
                Logger.loggerWrite(SOMETHING_WENT_WRONG_MESSAGE);
                Logger.loggerWriteError(e);
            }
        }
    }
}