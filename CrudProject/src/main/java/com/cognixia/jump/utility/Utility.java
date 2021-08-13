package com.cognixia.jump.utility;

import java.util.Random;

public class Utility {
    
    public static String randomIsbn() {
        String isbn = "";

        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        isbn = salt.toString();

        return isbn;
    }

}
