package com.teachmeskills.final_assignment.custom_exceptions;

public class OrdersFolderNotExistException extends Exception{
    public OrdersFolderNotExistException(String message) {
        super(message);
    }
}
