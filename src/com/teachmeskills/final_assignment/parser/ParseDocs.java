package com.teachmeskills.final_assignment.parser;

//TODO write javadoc

import com.teachmeskills.final_assignment.authorization.Session;
import com.teachmeskills.final_assignment.generator.ReportGenerator;
import com.teachmeskills.final_assignment.parser.parsers.CheckParser;
import com.teachmeskills.final_assignment.parser.parsers.InvoiceParser;
import com.teachmeskills.final_assignment.parser.parsers.OrderParser;
import com.teachmeskills.final_assignment.service.FileProcessService;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.validator.Validator;

import java.io.*;

import static com.teachmeskills.final_assignment.util.consts.messages.ValidatorMessages.VALIDATION_ERROR_MESSAGE;

public class ParseDocs extends FileProcessService {
    public static void doParsingFiles(Session session, String folderPath) {
        if (session != null && session.isSessionStillAlive()) {
            File docs = new File(folderPath);
            if (Validator.folderValidator(docs)) {
                ReportGenerator.report(CheckParser.parseCheckInfo(docs),
                        InvoiceParser.parseInvoiceInfo(docs),
                        OrderParser.parseOrderInfo(docs));


                //TODO implement exit method
            } else {
                Logger.loggerWrite(VALIDATION_ERROR_MESSAGE);

            }
        } else {
            System.out.println("Session was expired or do not exists.");


        }
    }
}



