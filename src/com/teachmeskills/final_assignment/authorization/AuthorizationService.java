package com.teachmeskills.final_assignment.authorization;
//TODO write javadoc

import com.teachmeskills.final_assignment.util.consts.messages.AuthorizationMessages;
import com.teachmeskills.final_assignment.util.encoder.Encoder;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.storage.StorageMock;

import static com.teachmeskills.final_assignment.util.consts.messages.AuthorizationMessages.*;

public class AuthorizationService {

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
            System.out.println("Access granted");
            return new Session();
        }else {
            //TODO incorrect login counter
            System.err.println("Login or password is incorrect. Please try again.");
            return null;
        }
    }
}
