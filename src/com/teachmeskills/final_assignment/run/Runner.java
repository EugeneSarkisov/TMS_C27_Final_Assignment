package com.teachmeskills.final_assignment.run;

import com.teachmeskills.final_assignment.service.FileProcessService;

import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {
//        String input = "12345ABC";
//        System.out.println(Encoder.code(input));

//        String input = "wajbbaievkMTIzNDVBQkM=";
//        System.out.println(Encoder.decode(input));
//      Session session = AuthorizationService.authorization("qwerty", "12345ABC");
        Scanner scanner = new Scanner(System.in);
        FileProcessService.startProcess(scanner.next(), scanner.next(), scanner.next());
        scanner.close();
    }
}













