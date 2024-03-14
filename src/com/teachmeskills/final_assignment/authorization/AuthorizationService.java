package com.teachmeskills.final_assignment.authorization;

/**
 * Uses the authentication method to authorise the user and give him access to the program
 *
 * @author KirillPalianitsa
 */

import com.teachmeskills.final_assignment.util.encoder.Encoder;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.storage.StorageMock;

import static com.teachmeskills.final_assignment.util.consts.messages.AuthorizationMessages.*;

public class AuthorizationService {
    /**
     * Accepts authorisation data as input.
     * Checks the entered data against the data stored in memory.
     * Gives the user access to the session
     * @param login - user login
     * @param password - user password
     * @return running session
     */
    public static Session authorization(String login, String password){
        StorageMock storage = new StorageMock();
        Logger.loggerWrite(GETTING_LOGIN_MESSAGE);
        String loginFromStorage = storage.getLogin();
        Logger.loggerWrite(GETTING_PASSWORD_MESSAGE);
        String passwordFromStorage = storage.getPassword();
        Logger.loggerWrite(CHECKING_DATA_MESSAGE);
        String decodedLogin = Encoder.decode(loginFromStorage);
        String decodedPassword = Encoder.decode(passwordFromStorage);
        if(login.toLowerCase().equals(decodedLogin) && password.equals(decodedPassword)){
            System.out.println("Access granted. Please enter path to the folder: ");
            return new Session();
        }else {
            System.err.println("Login or password is incorrect. Please try again.");
            return null;
        }
    }
}
