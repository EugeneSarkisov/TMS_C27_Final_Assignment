package com.teachmeskills.final_assignment.util.logger;

/**
 * Uses for collecting and writing logs about runtime process.
 *
 * @author EugeneSarkisov
 */

import com.teachmeskills.final_assignment.util.date_sample.DateAndTime;

import java.io.*;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_ERROR_LOG;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_LOG;

public class Logger {

    /**
     * loggerWrite writing user logs in runtime and showing this info in console.
     * All logs contain in log/log.txt
     * @param string - contain message which we need to write in logs.
     */

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
        //TODO date with logs

    /**
     * loggerWriteError writing error logs in runtime and showing this info in console.
     * All logs contain in log/error_log.txt
     * @param exp - contain exception and get exception message which we need to write in logs.
     */

    public static void loggerWriteError(Exception exp) {
        File logFile = new File(PATH_TO_ERROR_LOG);
        try (Writer writer = new FileWriter(logFile, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[ERROR] ---> ");
            sb.append(DateAndTime.getDateAndTime());
            sb.append(" ---> ");
            System.err.println(sb.toString() + exp.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exp.printStackTrace(pw);
            sb.append(sw.toString());
            writer.write(sb.toString() + "\n");
            sw.close();
            pw.close();
        } catch (IOException e) {
            System.err.println(SOMETHING_WENT_WRONG_MESSAGE);
        }
    }
}
