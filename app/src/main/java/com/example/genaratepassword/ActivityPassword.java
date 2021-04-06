package com.example.genaratepassword;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genaratepassword.logic.Encryption;
import com.example.genaratepassword.storage.MySavesPasswords;

import java.util.ArrayList;

public class ActivityPassword extends Activity {

    //static ArrayList<LinearLayout> layoutSavesPassword = new ArrayList<>();

    LinearLayout.LayoutParams lpPassword = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams lpObject = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    LinearLayout layoutPassword;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_password);

        layoutPassword = findViewById(R.id.layoutPassword);
        buttonBack = findViewById(R.id.buttonBack);

        MySavesPasswords.loadAll(this);
        loadPassword();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPassword.this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        });
    }

    private void loadPassword(){
        layoutPassword.removeAllViews();
        for ( int i = 0; i < MySavesPasswords.savesPasswords.size(); i++ ) {
            layoutPassword.addView(addPasswordLayout(Encryption.encryption(MySavesPasswords.savesPasswords.get(i)),
                    Encryption.encryption(MySavesPasswords.savesServices.get(i)), i), lpPassword);
        }
    }

    public LinearLayout addPasswordLayout(String strPassword, String strService, final int index){
        final LinearLayout layout = new LinearLayout(this);
        TextView password = new TextView(this);
        password.setText(strPassword);
        TextView service = new TextView(this);
        service.setText(strService);
        Button buttonDelete = new Button(this);
        buttonDelete.setText("-");
        lpObject.weight = 4;
        layout.addView(password, lpObject);
        lpObject.weight = 4;
        layout.addView(service, lpObject);
        lpObject.weight = 2;
        layout.addView(buttonDelete, lpObject);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySavesPasswords.delPassword(index, v.getContext());
                layoutPassword.removeAllViews();
                loadPassword();
                MyUtils.printToast("Пароль удалён", v.getContext());
            }
        });

        return layout;
    }
}
