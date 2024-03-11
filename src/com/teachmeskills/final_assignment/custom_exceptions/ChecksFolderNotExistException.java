package com.teachmeskills.final_assignment.custom_exceptions;

public class ChecksFolderNotExistException extends Exception {
    public ChecksFolderNotExistException(String message) {
        super(message);
    }
}
