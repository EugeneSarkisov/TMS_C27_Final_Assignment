package com.teachmeskills.final_assignment.util.validator;

import com.teachmeskills.final_assignment.custom_exceptions.IsFolderNotExistException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.validator.validations.DirectoryValidation;

import java.io.File;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.CHECK_THE_ERROR_LOG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.ValidatorMessages.END_VALIDATION;
import static com.teachmeskills.final_assignment.util.consts.messages.ValidatorMessages.START_VALIDATION;


public class Validator {
    public static boolean folderValidator(File file) {
        try {
            Logger.loggerWrite(START_VALIDATION);
            DirectoryValidation.isFileExistsValidation(file);
            DirectoryValidation.isDirectoryEmptyValidation(file);
            Logger.loggerWrite(END_VALIDATION);
        } catch (IsFolderNotExistException | IsDirectoryEmptyException e) {
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
