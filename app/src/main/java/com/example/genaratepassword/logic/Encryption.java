package com.example.genaratepassword.logic;

public class Encryption {
    private static final String  key = "3956293";
    public static String encryption(String str){
        char[] charArr = str.toCharArray();
        for ( int i = 0; i < str.length(); i++ ){
            int a = charArr[i];
            int c = key.charAt(i % (key.length() - 1));
            charArr[i] = (char) (a ^ c);
        }
        return new String(charArr);
    }
}
