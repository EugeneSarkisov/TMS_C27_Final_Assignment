package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.authorization.AuthorizationService;
import com.teachmeskills.final_assignment.parser.CheckParser;
import com.teachmeskills.final_assignment.parser.InvoiceParser;
import com.teachmeskills.final_assignment.parser.OrderParser;
import com.teachmeskills.final_assignment.authorization.Session;
import com.teachmeskills.final_assignment.validator.Validator;

import java.io.File;

public class FileProcessService {
    public static void startProcess(String login, String password, String path){
        doFileProcess(AuthorizationService.authorization(login, password), path);
    }

    private static void doFileProcess(Session session, String folderPath) {
        if (session != null) {
            if (session.isSessionStillAlive()) {
                File docs = new File(folderPath);
                Validator.folderValidator(docs);
                CheckParser.parseCheckInfo(docs);
                InvoiceParser.parseInvoiceInfo(docs);
                OrderParser.parseOrderInfo(docs);
                //TODO implement report generator
                //TODO implement exit method
            } else {
                System.out.println("Session was expired.");
            }
        } else {
            System.out.println("Session do not exists.");
        }
    }
}

