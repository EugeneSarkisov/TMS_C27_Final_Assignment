package com.teachmeskills.final_assignment.util.encoder;

import java.util.Base64;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Encoder {
    public static String code(String input) {
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
        String result = addSalt(encodedString);
        return result;
    }

    public static String decode(String input) {
        byte[] bytes = Base64.getDecoder().decode(input.substring(10));
        String result = new String(bytes);
        return result;
    }

    private static String addSalt(String input) {
        String symbol = "abcdefghijklmnopqrstuvwxyz0123456789";

        String salt = new Random().ints(10, 0, symbol.length())
                .mapToObj(symbol::charAt)
                .map(Objects::toString)
                .collect(Collectors.joining());
        String result = salt + input;
        return result;
    }

}
