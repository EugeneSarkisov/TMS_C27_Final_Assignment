package com.teachmeskills.final_assignment.util.mover;

import com.teachmeskills.final_assignment.util.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.*;
import static com.teachmeskills.final_assignment.util.consts.messages.UserLogMessages.CHECK_THE_ERROR_LOG_MESSAGE;
import static com.teachmeskills.final_assignment.util.consts.path.Path.PATH_TO_GARBAGE_CHECKS;

public class FileMover {
    public static void moveFile(File file){
        try {
            Files.move(Paths.get(file.getPath()),
                    Paths.get(PATH_TO_GARBAGE_CHECKS + file.getName()),
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
