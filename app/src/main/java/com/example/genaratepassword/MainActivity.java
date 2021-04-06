package com.example.genaratepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genaratepassword.logic.GeneratePassword;
import com.example.genaratepassword.logic.NumSymbols;
import com.example.genaratepassword.storage.MySavesPasswords;

public class MainActivity extends AppCompatActivity {

    Button buttonNumSymbolsLeft;
    Button buttonNumSymbolsRight;
    TextView textNumSymbols;

    CheckBox checkBoxSmallLetter;
    CheckBox checkBoxBigLetter;
    CheckBox checkBoxNumbers;
    CheckBox checkBoxSymbols;

    TextView textPassword;
    TextView textService;

    Button buttonGenerate;
    Button buttonSave;
    Button buttonMyPasswords;

    public static volatile MainActivity activity;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        buttonNumSymbolsLeft = findViewById(R.id.buttonNumSymbolsLeft);
        buttonNumSymbolsRight = findViewById(R.id.buttonNumSymbolsRight);
        textNumSymbols = findViewById(R.id.textNumSymbols);
        textNumSymbols.setText(Integer.toString(NumSymbols.getNumSymbols()));

        buttonNumSymbolsLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumSymbols.subNumSymbols();
                textNumSymbols.setText(Integer.toString(NumSymbols.getNumSymbols()));
            }
        });

        buttonNumSymbolsRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumSymbols.addNumSymbols();
                textNumSymbols.setText(Integer.toString(NumSymbols.getNumSymbols()));
            }
        });


        checkBoxSmallLetter = findViewById(R.id.checkBoxSmallLetter);
        checkBoxBigLetter = findViewById(R.id.checkBoxBigLetter);
        checkBoxNumbers = findViewById(R.id.checkBoxNumbers);
        checkBoxSymbols = findViewById(R.id.checkBoxSymbols);

        textPassword = findViewById(R.id.textPassword);
        textService = findViewById(R.id.textService);

        buttonGenerate = findViewById(R.id.buttonGenerate);

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPassword.setText(GeneratePassword.generatePassword(checkBoxSmallLetter.isChecked(),
                        checkBoxBigLetter.isChecked(), checkBoxNumbers.isChecked(), checkBoxSymbols.isChecked()));
            }
        });

        buttonMyPasswords = findViewById(R.id.buttonMyPasswords);

        buttonMyPasswords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityPassword.class);

                startActivity(intent);

                finish();
            }
        });
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = textPassword.getText().toString(),
                        service = textService.getText().toString();
                if (!password.isEmpty() && !service.isEmpty()){
                    MySavesPasswords.addPassword(password, service, v.getContext());
                    MyUtils.printToast("Пароль сохранён", v.getContext());
                }else{
                    MyUtils.printToast("Запоните поля \"Пароль\" и \"Сервис\"", v.getContext());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
