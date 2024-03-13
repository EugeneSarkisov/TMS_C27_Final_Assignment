package com.teachmeskills.final_assignment.util.date_sample;

/**
 * Contain method which return date and time in local format for logger
 *
 * @author EugeneSarkisov;
 */

import com.teachmeskills.final_assignment.util.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime extends Logger {

    /**
     * Created new date object and implementing him in local date format object
     * @return - string with date and time in format: "yyyy.MM.dd 'at' HH:mm:ss:S"
     */

    public static String getDateAndTimeForLogs(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss:S");
        return dateFormat.format(date);
    }
}
