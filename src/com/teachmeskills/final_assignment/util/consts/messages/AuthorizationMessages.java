package com.teachmeskills.final_assignment.util.consts.messages;

/**
 * Contains constants of authorization messages for AuthorizationService
 *
 * @author KirillPalianitsa
 */

public interface AuthorizationMessages {
    String AUTHORIZATION_START_MESSAGE = "For authorization please enter your login and password: ";
    String LOGIN_MESSAGE = "Login: ";
    String PASSWORD_MESSAGE = "Password: ";
    String PATH_MESSAGE = "Access granted! Please enter the path to the data folder: ";
    String SESSION_NULL_MESSAGE = "Session was expired or do not exists.";
    String LOGIN_PASSWORD_INCORRECT_MESSAGE = "Login or password is incorrect. Please try again.";
    String GETTING_LOGIN_MESSAGE = "Getting login from storage.";
    String GETTING_PASSWORD_MESSAGE = "Getting password from storage.";
    String CHECKING_DATA_MESSAGE = "Checking the correctness of the entered data...";

}
