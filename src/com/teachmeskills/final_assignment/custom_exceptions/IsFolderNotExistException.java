package com.teachmeskills.final_assignment.custom_exceptions;

/**
 * Class IsFolderExistException contain a pattern of exception for folder which not exist.
 *
 * @author EugeneSarkisov
 */

public class IsFolderNotExistException extends Exception {

    /**
     * Construct a new exception with specified detailed message.
     *
     * @param message - the detail message.
     *                The detail message is saved for later retrieval by the getMessage() method.
     */
    public IsFolderNotExistException(String message) {
        super(message);
    }
}
