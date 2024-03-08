package com.teachmeskills.final_assignment.util.date_sample;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {
    public static String getDateAndTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss:S");
        return dateFormat.format(date);
    }

}
