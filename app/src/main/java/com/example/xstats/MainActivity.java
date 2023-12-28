package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import java.sql.*;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.xstats.RemoteServerConnection;

import java.sql.Connection;
import java.sql.DriverManager;


public class MainActivity extends AppCompatActivity {


    EditText Name, Passwd;
    Button Enter;

    ServerCommunication serverConnector;

    String sqlQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Enter = findViewById(R.id.button);
        Button Reg = findViewById(R.id.buttonreg);

        View.OnClickListener onBtn = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText UserName = findViewById(R.id.editTextTextEmailAddress);
                EditText UserPassw = findViewById(R.id.editTextTextPassword);
                sqlQuery = "login:SELECT * FROM goin WHERE username = '" + UserName.getText() + "' AND pass = '" + UserPassw.getText() + "';";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            serverConnector = ServerCommunication.getInstance();
                            serverConnector.sendMessage(sqlQuery);
                            String response = serverConnector.receiveMessage();
                            if (response.equals("true")){
                                Intent intent = new Intent(MainActivity.this, SecondActiv.class);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                            // Добавляем отладочный вывод в случае ошибки
                            Log.e("MainActivity", "Ошибка подключения к серверу: " + e.getMessage());
                        }
                    }
                }).start();

            }


        };
        Enter.setOnClickListener(onBtn);
        View.OnClickListener regbtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        };
        Reg.setOnClickListener(regbtn);
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}