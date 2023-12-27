package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
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
//    Connection connection = null;
//    Statement statement = null;
//    ResultSet resultSet = null;
//    ServerCommunication serverCon;
//
//    {
//        try {
//            serverCon = new ServerCommunication();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    RemoteServerConnection Server = new RemoteServerConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.editTextTextEmailAddress);
        Passwd = findViewById(R.id.editTextTextPassword);
        Button Enter = findViewById(R.id.button);


        View.OnClickListener onBtn = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String UserName = Name.getText().toString();
                String UserPassw = Name.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActiv.class);
                startActivity(intent);
            }


        };
        Enter.setOnClickListener(onBtn);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!Server.isStarted()) {

                        Server.setServerInfo("82.179.140.18", 45889);
                        Server.connect();


                        // Добавляем отладочный вывод
                        Log.d("MainActivity", "Подключение к серверу успешно");

                        // Перед отправкой сообщения, лучше добавить проверку isStarted()
                        // и обработку ситуации, если подключение не удалось
                        Server.sendMessage("SELECT username FROM dataus");
                        Log.d("Test SQL", Server.receiveMessage());
                        Name.setText(Server.receiveMessage());
//                        serverCon = ServerCommunication.getInstance();
//                        serverCon.sendMessage("SELECT username FROM dataus");
//                        Log.d("TestSQL2", serverCon.receiveMessage());



                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    // Добавляем отладочный вывод в случае ошибки
                    Log.e("MainActivity", "Ошибка подключения к серверу: " + e.getMessage());
                }
            }
        }).start();

    }

}