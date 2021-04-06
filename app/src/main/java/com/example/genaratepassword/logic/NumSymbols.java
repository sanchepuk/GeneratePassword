package com.example.genaratepassword.logic;

public class NumSymbols {
    static private int numSymbols = 1;

    static public int getNumSymbols() {
        return numSymbols;
    }

    static public void subNumSymbols (){
        if (numSymbols > 1){
            numSymbols--;
        }
    }

    static public void addNumSymbols (){
        if (numSymbols < 12){
            numSymbols++;
        }
    }
}
