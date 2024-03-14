package com.teachmeskills.final_assignment.custom_exceptions;

/**
 * Class InvoicesFolderNotExistException contain a pattern of exception for invoices folder which not exist.
 *
 * @author EugeneSarkisov
 */

public class InvoicesFolderNotExistException extends Exception {
    /**
     * Construct a new exception with specified detailed message.
     *
     * @param message - the detail message.
     *                The detail message is saved for later retrieval by the getMessage() method.
     */
    public InvoicesFolderNotExistException(String message) {
        super(message);
    }
}
