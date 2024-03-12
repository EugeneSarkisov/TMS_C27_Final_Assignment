package com.teachmeskills.final_assignment.parser.parsers;


import com.teachmeskills.final_assignment.custom_exceptions.ChecksFolderNotExistException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;
import com.teachmeskills.final_assignment.util.mover.FileMover;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.validator.validations.DirectoryValidation;

import java.io.*;
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
        }
    }

    /**
     * findCheckFolder checks if folder checks exist or not. Validation happened with
     * filtering files in package.
     * @param file - get the file from validator;
     * @return - if folder exist - return the file with package;
     * @throws ChecksFolderNotExistException - if folder not exist;
     */

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
     * @param file all order files from package
     * @return checks
     */
    private static List<File> sortChecks(File file) {
        //remove all unnecessary files
        List<File> checks = new ArrayList<>(List.of(file.listFiles()));
        try {
            Logger.loggerWrite(ACCESS_CHECK_FOLDER_MESSAGE);
            DirectoryValidation.isDirectoryEmptyValidation(file);
            Logger.loggerWrite(REMOVING_GARBAGE_CHECK_MESSAGE);
            Iterator<File> checkIter = checks.iterator();
            while (checkIter.hasNext()) {
                File check = checkIter.next();
                if (!check.getName().matches(CHECK_REGEX)) {
                    FileMover.moveFile(check, PATH_TO_GARBAGE_CHECKS);
                    checkIter.remove();
                }
            }
            Logger.loggerWrite(REMOVING_COMPLETE_MESSAGE);
        } catch (IsDirectoryEmptyException e) {
            Logger.loggerWriteError(e);
        }
        return checks;
    }

    /**
     * Parses sorted documents with for-loop and FileReader. Verification of
     * bill string happening when reader find string with key-word "Total".
     * Next, all strings collect in orderBillList, from every string we get
     * value of order and summing it in orderSum.
     * @param checkList get the sort collection from sortChecks method.
     * @return checkSum - all necessary bills summing together.
     */
    private static double parseCheckInfo(List<File> checkList) {
        //check is our collection empty
        if(checkList.isEmpty()){
            Logger.loggerWrite(NO_VALUABLE_CHECKS_MESSAGE);
            return 0;
        }
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
                System.err.println(SOMETHING_WENT_WRONG_MESSAGE);
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
        return checkSum;
    }
}


