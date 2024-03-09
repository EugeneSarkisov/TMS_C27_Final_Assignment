package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.encoder.Encoder;
import com.teachmeskills.final_assignment.session.Session;
import com.teachmeskills.final_assignment.storage.StorageMock;

public class AuthorizationService {

    public static Session authorization(String login, String password){
        StorageMock storage = new StorageMock();
        String loginFromStorage = storage.getLogin();
        String passwordFromStorage = storage.getPassword();

        String decodedLogin = Encoder.decode(loginFromStorage);
        String decodedPassword = Encoder.decode(passwordFromStorage);

        if(login.toLowerCase().equals(decodedLogin) && password.equals(decodedPassword)){
            System.out.println("Access");
            return new Session();
        }else {
            // TODO incorrect login counter
            System.out.println("Login or password is incorrect. Please try again.");
            return null;
        }

    }


}
