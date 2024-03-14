package com.teachmeskills.final_assignment.custom_exceptions;

/**
 * Class IsDirectoryEmptyException contain a pattern for empty folder exception.
 *
 * @author EugeneSarkisov
 */

public class IsDirectoryEmptyException extends Exception {

    /**
     * Construct a new exception with specified detailed message.
     *
     * @param message - the detail message.
     *                The detail message is saved for later retrieval by the getMessage() method.
     */
    public IsDirectoryEmptyException(String message) {
        super(message);
    }
}
