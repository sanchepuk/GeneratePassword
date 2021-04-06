package com.example.genaratepassword.logic;

import java.util.Random;

public class GeneratePassword {
    static public String generatePassword (boolean smallLetter, boolean bigLetter, boolean numbers, boolean symbols){
        Random random = new Random();
        String availableSymbols = "";
        String result = "";

        if (smallLetter){
            availableSymbols += "abcdefghijklmnopqrstuvwxyz";
        }
        if (bigLetter) {
            availableSymbols += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }

        if (numbers) {
            availableSymbols += "1234567890";
        }

        if (symbols) {
            availableSymbols += "!@#$%&*";
        }

        if (!availableSymbols.isEmpty()){
            for ( int i = 0; i < NumSymbols.getNumSymbols(); i++ ) {
                result += availableSymbols.toCharArray()[random.nextInt(availableSymbols.length())];
            }
        }

        return result;
    }
}
