package com.teachmeskills.final_assignment.parser;


import com.teachmeskills.final_assignment.custom_exceptions.ChecksFolderNotExistException;
import com.teachmeskills.final_assignment.util.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.teachmeskills.final_assignment.util.consts.currency.Currency.EUR_TO_USD_EXCHANGE;
import static com.teachmeskills.final_assignment.util.consts.messages.CheckParserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_GARBAGE_CHECKS;
import static com.teachmeskills.final_assignment.util.consts.regex.Regex.CHECK_REGEX;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.*;

/**
 * Parse ChecksFiles and get the sum of bills of all documents.
 * Allow to verification necessary files and remove garbage order
 * files to garbage package: data/temp/garbageChecks.
 *
 * @author EugeneSarkisov
 */
public class CheckParser {
    public static void parseCheckInfo(File file) {
        try {
            parseCheckInfo(sortChecks(findCheckFolder(file)));
        } catch (ChecksFolderNotExistException e) {
            Logger.loggerWriteError(e);
            System.err.println(e.getMessage());
        }
    }
    private static File findCheckFolder(File file) throws ChecksFolderNotExistException {
        Logger.loggerWrite(CHECK_CHECKS_FOLDER_MESSAGE);
        List<File> dataFolders = Arrays.stream(file.listFiles())
                .filter(n -> n.getName().equals("checks"))
                .toList();
        if (!dataFolders.isEmpty()) {
            return dataFolders.get(0);
        } else {
            throw new ChecksFolderNotExistException("Checks folder doesn't exist");
        }
    }

    /**
     * Collect all files from Check package into collection "checks" and
     * sorting it while collection isn't empty. Garbage checks moving to
     * the temp/garbageChecks. Return the sort collection of orders.
     *
     * @param file all order files from package
     * @return checks
     */
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

    /**
     * Parses sorted documents with for-loop and FileReader. Verification of
     * bill string happening when reader find string with key-word "Total".
     * Next, all strings collect in orderBillList, from every string we get
     * value of order and summing it in orderSum.
     *
     * @param checkList get the sort collection from sortChecks method.
     */
    private static void parseCheckInfo(List<File> checkList) {
        //parsing check info
        Logger.loggerWrite(PARSING_CHECK_INFO_MESSAGE);
        List<String> billCheckList = new ArrayList<>();
        for (File check : checkList) {
            try (BufferedReader checkReader = new BufferedReader(new FileReader(check))) {
                String line;
                while ((line = checkReader.readLine()) != null) {
                    if (line.startsWith("Bill")) {
                        billCheckList.add(line);
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
        for (String bill : billCheckList) {
            checkSum += EUR_TO_USD_EXCHANGE * Double.parseDouble(bill.substring(23).trim().
                                                                 replace(",", "."));
            System.out.println(checkSum);
        }
        Logger.loggerWrite(TRANSFER_CHECK_INFO_MESSAGE);
    }
}


