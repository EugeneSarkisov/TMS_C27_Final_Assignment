package com.teachmeskills.final_assignment.run;

import com.teachmeskills.final_assignment.encoder.Encoder;
import com.teachmeskills.final_assignment.service.AuthorizationService;
import com.teachmeskills.final_assignment.service.FileProcessService;
import com.teachmeskills.final_assignment.session.Session;


public class Runner {
    public static void main(String[] args) {
//        String input = "12345ABC";
//        System.out.println(Encoder.code(input));

//        String input = "wajbbaievkMTIzNDVBQkM=";
//        System.out.println(Encoder.decode(input));

        Session session = AuthorizationService.authorization("qwerty", "12345ABC");
        FileProcessService.doFileProcess(session, "path to folder");
    }
}













