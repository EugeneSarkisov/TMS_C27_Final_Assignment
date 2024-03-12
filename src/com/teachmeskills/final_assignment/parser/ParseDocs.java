package com.teachmeskills.final_assignment.parser;

import com.teachmeskills.final_assignment.authorization.Session;
import com.teachmeskills.final_assignment.parser.parsers.CheckParser;
import com.teachmeskills.final_assignment.parser.parsers.InvoiceParser;
import com.teachmeskills.final_assignment.parser.parsers.OrderParser;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.validator.Validator;

import java.io.File;

import static com.teachmeskills.final_assignment.util.consts.messages.ValidatorMessages.VALIDATION_ERROR_MESSAGE;

public class ParseDocs {
    public static void doParsingFiles(Session session, String folderPath) {
        if (session != null && session.isSessionStillAlive()) {
            File docs = new File(folderPath);
            if(Validator.folderValidator(docs)){
                CheckParser.parseCheckInfo(docs);
                InvoiceParser.parseInvoiceInfo(docs);
                OrderParser.parseOrderInfo(docs);
                //TODO implement report generator
                //TODO implement exit method
            } else{
                Logger.loggerWrite(VALIDATION_ERROR_MESSAGE);

            }
        } else {
            System.out.println("Session was expired or do not exists.");
        }
    }
}

