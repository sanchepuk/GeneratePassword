package com.example.genaratepassword.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.genaratepassword.MainActivity;
import com.example.genaratepassword.logic.Encryption;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MySavesPasswords{

    static SharedPreferences sPref;
    private static final String keyPassword = "PASSWORD";
    private static final String keyService = "SERVICE";
    private static final String keyNum = "NUMBER";

    public static ArrayList<String> savesPasswords = new ArrayList<>();
    public static ArrayList<String> savesServices = new ArrayList<>();

    public static void addPassword(String password, String service, Context context){
        savesPasswords.add(Encryption.encryption(password));
        savesServices.add(Encryption.encryption(service));
        saveAll(context);
    }

    public static void delPassword(int index, Context context){
        savesPasswords.remove(index);
        savesServices.remove(index);
        saveAll(context);
    }

    static void saveAll(Context context) {
        sPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(keyNum, savesServices.size());
        for ( int i = 0; i < savesServices.size(); i++ ) {
            ed.putString(keyPassword + i, savesPasswords.get(i));
            ed.putString(keyService + i, savesServices.get(i));
        }
        ed.commit();
    }

    public static void loadAll(Context context) {
        savesPasswords.clear();
        savesServices.clear();
        sPref = PreferenceManager.getDefaultSharedPreferences(context);
        for ( int i = 0; i < sPref.getInt(keyNum, 0); i++ ) {
            savesPasswords.add(sPref.getString(keyPassword + i, ""));
            savesServices.add(sPref.getString(keyService + i, ""));
        }
    }
}
