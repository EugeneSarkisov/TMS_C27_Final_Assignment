package com.teachmeskills.final_assignment.util.validator;

import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;
import com.teachmeskills.final_assignment.custom_exceptions.IsFolderNotExistException;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.validator.validations.DirectoryValidation;

import java.io.File;

import static com.teachmeskills.final_assignment.util.consts.messages.ProgramMessages.PATH_ERROR_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.CHECK_THE_ERROR_LOG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.ValidatorMessages.END_VALIDATION;
import static com.teachmeskills.final_assignment.util.consts.messages.ValidatorMessages.START_VALIDATION;

/**
 * Contain method which uses for folder validation.
 * Throws IsFolderExistException, IsDirectoryEmptyException.
 *
 * @author EugeneSarkisov
 */
public class Validator {

    /**
     * Checking if folder empty or not exist. Get the methods from DirectoryValidation
     * class. All steps write in user log file.
     *
     * @param file - file from user path.
     * @return - true if folder exist and not empty.
     */

    public static boolean folderValidator(File file) {
        try {
            Logger.loggerWrite(START_VALIDATION);
            DirectoryValidation.isFileExistsValidation(file);
            DirectoryValidation.isDirectoryEmptyValidation(file);
            Logger.loggerWrite(END_VALIDATION);
        } catch (IsFolderNotExistException | IsDirectoryEmptyException e) {
            System.err.println(PATH_ERROR_MESSAGE);
            Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            Logger.loggerWriteError(e);
            return false;
        } catch (Exception e) {
            System.err.println(SOMETHING_WENT_WRONG_MESSAGE);
            Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            Logger.loggerWriteError(e);
            return false;
        }
        return true;
    }

}
