package com.teachmeskills.final_assignment.run;

import com.teachmeskills.final_assignment.service.FileProcessService;
import com.teachmeskills.final_assignment.util.date_sample.DateAndTime;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Hello! This is a program for processing payment documents and providing financial statements.");
        FileProcessService.doFileProcess();
    }
}













