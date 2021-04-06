package com.example.genaratepassword;

import android.content.Context;
import android.widget.Toast;

public class MyUtils {
    public static void printToast(String text, Context context){
        Toast toast = Toast.makeText(context,
                text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
