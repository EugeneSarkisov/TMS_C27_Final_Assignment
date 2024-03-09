package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.session.Session;

public class FileProcessService {
    public static void doFileProcess(Session session, String folderPath) {
        if (session != null) {
            if (session.isSessionStillAlive()) {
                System.out.println(); // TODO
            } else {
                System.out.println("Session was expired.");
            }
        } else {
            System.out.println("Session do not exists.");
        }
    }
}

