package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.authorization.AuthorizationService;
import com.teachmeskills.final_assignment.parser.ParseDocs;
import com.teachmeskills.final_assignment.run.Runner;
import com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages;
import com.teachmeskills.final_assignment.util.logger.Logger;

import java.util.Scanner;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.SOMETHING_WENT_WRONG_MESSAGE;

public class FileProcessService extends Runner {
    public static void doFileProcess(){
        try(Scanner scanner = new Scanner(System.in)){
            ParseDocs.doParsingFiles(AuthorizationService.authorization(scanner.next(), scanner.next()), scanner.next());
        } catch(Exception e){
            Logger.loggerWrite(SOMETHING_WENT_WRONG_MESSAGE);
            Logger.loggerWriteError(e);
        }
    }

}

