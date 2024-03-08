package com.teachmeskills.final_assignment.parser;


import com.teachmeskills.final_assignment.util.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.teachmeskills.final_assignment.util.consts.messages.CheckParserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_GARBAGE_CHECKS;
import static com.teachmeskills.final_assignment.util.consts.regex.Regex.CHECK_REGEX;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.*;


public class CheckParser {
    public static void parseCheckInfo(File file) {
        parseInfo(sortChecks(file));
    }
    private static List<File> sortChecks(File file) {
        //remove all unnecessary files
        Logger.loggerWrite(ACCESS_CHECK_FOLDER_MESSAGE);
        List<File> checks = new ArrayList<>(List.of(file.listFiles()));
        Logger.loggerWrite(REMOVING_GARBAGE_CHECK_MESSAGE);
        Iterator<File> checkIter = checks.iterator();
        while (checkIter.hasNext()) {
            File check = checkIter.next();
            if (!check.getName().matches(CHECK_REGEX)) {
                try {
                    Files.move(Paths.get(check.getPath()),
                            Paths.get(PATH_TO_GARBAGE_CHECKS + check.getName()),
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
                checkIter.remove();
            }
        }
        Logger.loggerWrite(REMOVING_COMPLETE_MESSAGE);
        return checks;
    }
    private static void parseInfo(List<File> checkList) {
        //parsing check info
        Logger.loggerWrite(PARSING_CHECK_INFO_MESSAGE);
        List<String> billList = new ArrayList<>();
        for (File check : checkList) {
            try (BufferedReader checkReader = new BufferedReader(new FileReader(check))) {
                String line;
                while ((line = checkReader.readLine()) != null) {
                    if (line.startsWith("Bill")) {
                        billList.add(line);
                    }
                }
            } catch (Exception e) {
                System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
                Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
                Logger.loggerWriteError(e);
            }
        }
        Logger.loggerWrite(PARSING_CHECK_INFO_COMPLETE_MESSAGE);
        //summing all necessary bills
        double checkSum = 0.0;
        for (String bill : billList) {
            //TODO currencyConvertor
            checkSum += Double.parseDouble(bill.substring(23).trim().replace(",", "."));
            System.out.println(checkSum);
        }
    }
}

