package com.teachmeskills.final_assignment.custom_exceptions;

/**
 * Class OrdersFolderNotExistException contain a pattern of exception for orders folder which not exist.
 *
 * @author EugeneSarkisov
 */

public class OrdersFolderNotExistException extends Exception {
    /**
     * Construct a new exception with specified detailed message.
     *
     * @param message - the detail message.
     *                The detail message is saved for later retrieval by the getMessage() method.
     */
    public OrdersFolderNotExistException(String message) {
        super(message);
    }
}
