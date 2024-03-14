package com.teachmeskills.final_assignment.custom_exceptions;

/**
 * Class ChecksFolderNotExistException contain a pattern of exception for checks folder which not exist.
 *
 * @author EugeneSarkisov
 */

public class ChecksFolderNotExistException extends Exception {
    /**
     * Construct a new exception with specified detailed message.
     *
     * @param message - the detail message.
     *                The detail message is saved for later retrieval by the getMessage() method.
     */
    public ChecksFolderNotExistException(String message) {
        super(message);
    }
}
