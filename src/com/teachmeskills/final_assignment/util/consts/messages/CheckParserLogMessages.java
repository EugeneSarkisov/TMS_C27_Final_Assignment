package com.teachmeskills.final_assignment.util.consts.messages;

/**
 * Contain constants of log messages from check parser;
 *
 * @author EugeneSarkisov
 */

public interface CheckParserLogMessages {
    String CHECK_CHECKS_FOLDER_MESSAGE = "Checking 'CHECKS' folder...";
    String ACCESS_CHECK_FOLDER_MESSAGE = "Getting access to the folder 'CHECKS'";
    String REMOVING_GARBAGE_CHECK_MESSAGE = "Removing all unnecessary files";
    String PARSING_CHECK_INFO_MESSAGE = "Parsing info from 'CHECKS'";
    String PARSING_CHECK_INFO_COMPLETE_MESSAGE = "Parsing complete. Summing all 'CHECKS' bills";
    String TRANSFER_CHECK_INFO_MESSAGE = "Summing 'CHECKS' bills complete. Transfer information to report generator.";
    String NO_VALUABLE_CHECKS_MESSAGE = "There are no valuable 'CHECKS'";
}
