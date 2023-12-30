package com.example.xstats;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import java.util.Random;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.MessageFormat;

public class Registration extends AppCompatActivity {
    private String sqlQuery;
    public int UID;
    ServerCommunication serverConnector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button but= findViewById(R.id.button2);
        Random rnd = new Random();
        UID = rnd.nextInt(300000);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText log= findViewById(R.id.editTextLogin);
                EditText pas= findViewById(R.id.editTextPassword);
                sqlQuery = "INSERT INTO goin (username, pass, uid) VALUES ( '"+ log.getText()+"', '"+pas.getText()+"', '"+UID+"'); " +
                        "INSERT INTO gas (gas, uid) VALUES (0,'"+UID+"') ON CONFLICT DO NOTHING; INSERT INTO todate (dateto, uid) VALUES('12.12.12', '"+UID+"') ON CONFLICT DO NOTHING; " +
                        "INSERT INTO stat (monthst, yearst, uid) VALUES ('0','0', '"+UID+"') ON CONFLICT DO NOTHING";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            serverConnector = ServerCommunication.getInstance();
                            serverConnector.sendMessage(sqlQuery);
                            Log.d("Регистрация", "Успешная Регистрация");
                            // Переход на следующую активность
                            Intent intent = new Intent(Registration.this, SecondActiv.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
