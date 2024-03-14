package com.teachmeskills.final_assignment.util.encoder;

import java.util.Base64;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Uses for encode and decode login data.
 * Contain method which allows encryption of login data.
 *
 * @author KirillPalianitsa
 */

public class Encoder {

    /**
     * code encodes user data for authorisation
     *
     * @param input - contains data entered by the user
     * @return the encoded string
     */
    public static String code(String input) {
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
        String result = addSalt(encodedString);
        return result;
    }

    /**
     * code decodes stored data for authorisation
     *
     * @param input - contains stored data entered by the user
     * @return the decoded string
     */
    public static String decode(String input) {
        byte[] bytes = Base64.getDecoder().decode(input.substring(10));
        String result = new String(bytes);
        return result;
    }

    /**
     * addSalt adds a certain number of characters, encrypting the stored data
     *
     * @param input - contains stored data entered by the user
     * @return the encrypted input
     */
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
