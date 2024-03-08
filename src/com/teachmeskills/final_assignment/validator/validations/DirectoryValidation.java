package com.teachmeskills.final_assignment.validator.validations;

import com.teachmeskills.final_assignment.custom_exceptions.IsFolderExistException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;

import java.io.File;

public class DirectoryValidation {
    public static void isFileExistsValidation(File file) throws IsFolderExistException {
        if(!file.exists()){
            throw new IsFolderExistException(file.getPath() + " isn't exist.");
        }
    }
    public static void isDirectoryEmptyValidation(File file) throws IsDirectoryEmptyException {
        if(file.listFiles() == null){
            throw new IsDirectoryEmptyException(file.getPath() + " is empty.");
        }
    }

}
