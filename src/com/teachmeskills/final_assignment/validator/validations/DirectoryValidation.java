package com.teachmeskills.final_assignment.validator.validations;

import com.teachmeskills.final_assignment.custom_exceptions.IsFolderNotExistException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;

import java.io.File;

/**
 * Contain methods which uses in class "Validator". Allow to
 * check is folder empty or is folder exist.
 * Throws IsFolderExistException, IsDirectoryEmptyException.
 *
 * @author EugeneSarkisov
 */

public class DirectoryValidation {

    /**
     * Validate if folder exist with "if" operator. Validation makes
     * with file method .exists
     * @param file - package which user input in console.
     * @throws IsFolderNotExistException - if folder not exist.
     */

    public static void isFileExistsValidation(File file) throws IsFolderNotExistException {
        if(!file.exists()){
            throw new IsFolderNotExistException(file.getPath() + " isn't exist.");
        }
    }
    /**
     * Validate if folder empty with "if" operator. Validation makes
     * with file method .exists
     * @param file - package which user input in console.
     * @throws IsDirectoryEmptyException - if folder empty.
     */
    public static void isDirectoryEmptyValidation(File file) throws IsDirectoryEmptyException {
        if(file.listFiles() == null){
            throw new IsDirectoryEmptyException(file.getPath() + " is empty.");
        }
    }

}
