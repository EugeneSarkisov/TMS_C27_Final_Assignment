package com.teachmeskills.final_assignment.validator.validations;

import com.teachmeskills.final_assignment.custom_exceptions.ExistFolderException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;

import java.io.File;

public class DirectoryValidation {
    public static void isFileExistsValidation(File file) throws ExistFolderException {
        if(!file.exists()){
            throw new ExistFolderException(file.getPath() + " isn't exist");
        }
    }
    public static void isDirectoryEmptyValidation(File file) throws IsDirectoryEmptyException {
        if(file.listFiles().length == 0){
            throw new IsDirectoryEmptyException(file.getPath() + " is empty");
        }
    }

}
