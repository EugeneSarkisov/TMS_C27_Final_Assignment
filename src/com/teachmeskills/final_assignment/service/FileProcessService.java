package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.authorization.AuthorizationService;
import com.teachmeskills.final_assignment.parser.ParseDocs;
import com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages;
import com.teachmeskills.final_assignment.util.logger.Logger;

import java.util.Scanner;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;

public class FileProcessService {
    public static void doFileProcess() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("For authorization please enter your login, password and path to the folder: ");
            ParseDocs.doParsingFiles(AuthorizationService.authorization(scanner.next(), scanner.next()), scanner.next());
        } catch (Exception e) {
            Logger.loggerWrite(SOMETHING_WENT_WRONG_MESSAGE);
            Logger.loggerWriteError(e);
        }
    }

}

