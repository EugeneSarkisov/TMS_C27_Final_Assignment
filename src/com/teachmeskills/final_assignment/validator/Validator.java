package com.teachmeskills.final_assignment.validator;

import com.teachmeskills.final_assignment.custom_exceptions.IsFolderNotExistException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.validator.validations.DirectoryValidation;

import java.io.File;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.CHECK_THE_ERROR_LOG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;


public class Validator {
    public static boolean folderValidator(File file){
        try {
            Logger.loggerWrite("Validate folder...");
            DirectoryValidation.isFileExistsValidation(file);
            DirectoryValidation.isDirectoryEmptyValidation(file);
            Logger.loggerWrite("Validation complete");
        } catch (IsFolderNotExistException e) {
            System.out.printf(e.getMessage());
            Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            Logger.loggerWriteError(e);
            return false;
        } catch (IsDirectoryEmptyException e){
            System.out.println(e.getMessage());
            Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            Logger.loggerWriteError(e);
            return false;
        } catch (Exception e){
            System.out.println(SOMETHING_WENT_WRONG_MESSAGE);
            Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            Logger.loggerWriteError(e);
            return false;
        }
        return true;
    }
}
