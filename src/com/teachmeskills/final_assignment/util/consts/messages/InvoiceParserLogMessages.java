package com.teachmeskills.final_assignment.util.consts.messages;

/**
 * Contain constants of log messages from invoice parser;
 *
 * @author EugeneSarkisov, Kirill Palianitsa
 */

public interface InvoiceParserLogMessages {
    String CHECK_INVOICES_FOLDER_MESSAGE = "Checking 'INVOICES' folder...";
    String ACCESS_INVOICE_FOLDER_MESSAGE = "Getting access to the folder 'INVOICE'";
    String REMOVING_GARBAGE_INVOICE_MESSAGE = "Removing all unnecessary files";
    String PARSING_INVOICE_INFO_MESSAGE = "Parsing info from 'INVOICE'";
    String PARSING_INVOICE_INFO_COMPLETE_MESSAGE = "Parsing complete. Summing all 'INVOICE' bills";
    String TRANSFER_INVOICE_INFO_MESSAGE ="Summing 'INVOICES' bills complete. Transfer information to report generator.";
    String NO_VALUABLE_INVOICES = "There are no valuable 'INVOICES'";
}
