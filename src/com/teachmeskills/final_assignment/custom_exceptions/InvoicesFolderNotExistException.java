package com.teachmeskills.final_assignment.custom_exceptions;

public class InvoicesFolderNotExistException extends Exception{
    public InvoicesFolderNotExistException(String message) {
        super(message);
    }
}
