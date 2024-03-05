package com.teachmeskills.final_assignment.validation;

import com.teachmeskills.final_assignment.custom_exceptions.EmptyFolderException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryException;

import java.io.File;
import java.util.Arrays;

public class DirectoryValidation {
    public static void isEmptyValidation(File file) throws EmptyFolderException {
        if(Arrays.asList(file.listFiles()).size() == 0){
            throw new EmptyFolderException(file.getPath() + " is empty");
        }
    }
    public static void isDirectoryValidation(File file) throws IsDirectoryException {
        if(file.isDirectory()){
            throw new IsDirectoryException(file.getPath() + " is directory");
        }
    }
}
