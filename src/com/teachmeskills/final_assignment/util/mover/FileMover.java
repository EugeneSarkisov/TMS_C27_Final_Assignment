package com.teachmeskills.final_assignment.util.mover;

/**
 * Uses for moving garbage files. Allow to replace same files.
 * Implement in sorting method of parsers.
 * @author EugeneSarkisov
 */

import com.teachmeskills.final_assignment.parser.InvoiceParser;
import com.teachmeskills.final_assignment.util.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.CHECK_THE_ERROR_LOG_MESSAGE;

public class FileMover extends InvoiceParser {

    /**
     * Get the path of file which need to be moved and path of way to moving.
     * @param file - file, which need to be moved (from parsers)
     * @param path - way for files where they need to be moved.
     */

    public static void moveFile(File file, String path){
        try {
            Files.move(Paths.get(file.getPath()),
                    Paths.get(path + file.getName()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(ERROR_WHILE_MOVING_FILE_MESSAGE + e.getMessage());
            Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            Logger.loggerWriteError(e);
        } catch (Exception e) {
            System.err.println(SOMETHING_WENT_WRONG_MESSAGE);
            Logger.loggerWrite(e.getMessage() + CHECK_THE_ERROR_LOG_MESSAGE);
            Logger.loggerWriteError(e);
        }
    }
}
