package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    EditText Name, Pass;
    Button Enter;

    SharedPreferences sPref;
    final String SAVED_USER = "saved_user";
    final String SAVED_PASS = "saved_pass";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Enter = findViewById(R.id.button);
        Button Reg = findViewById(R.id.buttonreg);
        Name = findViewById(R.id.editTextTextEmailAddress);
        Pass = findViewById(R.id.editTextTextPassword);
        sPref = getSharedPreferences(SAVED_USER,MODE_PRIVATE);
        sPref = getSharedPreferences(SAVED_PASS,MODE_PRIVATE);
        View.OnClickListener onBtn = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveAcc();
            }

            private void saveAcc() {

                String getName = Name.getText().toString();
                String getPass = Pass.getText().toString();

                if (getPass.equals(sPref.getString(getName,""))){

                    showToast("Вы вошли в свой аккаунт!");
                    Intent intent = new Intent(MainActivity.this, SecondActiv.class);
                    startActivity(intent);}
                else if(!sPref.contains(getName)){
                    showToast("Такого аккаунта не существует, нажмити кнопку 'Registration' ");
                }
                else if (!getPass.equals(sPref.getString(getName,""))) {

                    showToast("Пароль введен не верно");

                }

            }


        };
        Enter.setOnClickListener(onBtn);
        View.OnClickListener regbtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAcc();
                Intent intent = new Intent(MainActivity.this, SecondActiv.class);
                startActivity(intent);
            }

            private void loadAcc() {
                String getName = Name.getText().toString();
                String getPass = Pass.getText().toString();

                SharedPreferences.Editor ed = sPref.edit();

                ed.putString(getName, getPass);
                ed.apply();
                showToast("Аккаунт успешно создан!");

            }
        };
        Reg.setOnClickListener(regbtn);
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
