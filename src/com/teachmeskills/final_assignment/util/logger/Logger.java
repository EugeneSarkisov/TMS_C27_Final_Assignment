package com.teachmeskills.final_assignment.util.logger;

import com.teachmeskills.final_assignment.util.date_sample.DateAndTime;

import java.io.*;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_ERROR_LOG;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_LOG;

public class Logger {
    public static void loggerWrite(String string) {
        File logFile = new File(PATH_TO_LOG);
        try (Writer writer = new FileWriter(logFile, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[INFO] ---> ");
            sb.append(DateAndTime.getDateAndTime());
            sb.append(" ---> ");
            sb.append(string);
            writer.write(sb.toString() + "\n");
            System.out.println(sb.toString());
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }

    public static void loggerWriteError(Exception exp) {
        File logFile = new File(PATH_TO_ERROR_LOG);
        try (Writer writer = new FileWriter(logFile, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[ERROR] ---> ");
            sb.append(DateAndTime.getDateAndTime());
            sb.append(" ---> ");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exp.printStackTrace(pw);
            sb.append(sw.toString());
            writer.write(sb.toString() + "\n");
            System.out.println(sb.toString());
            sw.close();
            pw.close();
        } catch (IOException e) {
            System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }
}
