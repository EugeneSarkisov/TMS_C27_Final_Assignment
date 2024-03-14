package com.teachmeskills.final_assignment.service;

/**
 * uses to request data for authorisation,
 * check the session and report on the results of program execution
 *
 * @author KirillPalianitsa
 */

import com.teachmeskills.final_assignment.authorization.AuthorizationService;
import com.teachmeskills.final_assignment.authorization.Session;
import com.teachmeskills.final_assignment.generator.ReportGenerator;
import com.teachmeskills.final_assignment.parser.CheckParser;
import com.teachmeskills.final_assignment.parser.InvoiceParser;
import com.teachmeskills.final_assignment.parser.OrderParser;
import com.teachmeskills.final_assignment.util.logger.Logger;
import com.teachmeskills.final_assignment.util.validator.Validator;

import java.io.File;
import java.util.Scanner;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.messages.ValidatorMessages.VALIDATION_ERROR_MESSAGE;

public class FileProcessService {
    /**
     * doFileProcess requests authorisation data and processes it.
     * Checks whether the Session is in progress. Calls the class to create a report
     */
    public static void doFileProcess() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("For authorization please enter your login and password: ");
            String login = scanner.next();
            String password = scanner.next();
            Session session = AuthorizationService.authorization(login, password);
            if (session != null && session.isSessionStillAlive()) {
                File docs = new File(scanner.next());
                if (Validator.folderValidator(docs)) {
                    ReportGenerator.report(CheckParser.parseCheckInfo(docs),
                            InvoiceParser.parseInvoiceInfo(docs),
                            OrderParser.parseOrderInfo(docs));
                } else {
                    Logger.loggerWrite(VALIDATION_ERROR_MESSAGE);
                }
            } else {
                System.out.println("Session was expired or do not exists.");
            }
        } catch (Exception e) {
            Logger.loggerWrite(SOMETHING_WENT_WRONG_MESSAGE);
            Logger.loggerWriteError(e);
        }
    }
}

