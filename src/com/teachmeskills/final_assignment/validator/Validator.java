package com.teachmeskills.final_assignment.validator;

import com.teachmeskills.final_assignment.custom_exceptions.ExistFolderException;
import com.teachmeskills.final_assignment.custom_exceptions.IsDirectoryEmptyException;
import com.teachmeskills.final_assignment.validator.validations.DirectoryValidation;

import java.io.File;

public class Validator {
    public static File folderValidator(File file){
        try {
            DirectoryValidation.isFileExistsValidation(file);
            DirectoryValidation.isDirectoryEmptyValidation(file);
        } catch (ExistFolderException e) {
            System.out.printf(e.getMessage());
        } catch (IsDirectoryEmptyException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Something went wrong... Please, contact with the administrator.");
        }
        return file;
    }
}
