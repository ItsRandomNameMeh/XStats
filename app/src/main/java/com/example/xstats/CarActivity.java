package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import java.sql.*;
import android.widget.EditText;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Dialog;
import android.view.View;
import java.util.Random;
import android.widget.Button;

import android.widget.Toast;


public class CarActivity extends AppCompatActivity {

    Dialog dialog;

    private static final String SHARED_PREFS_NAME = "my_cache";
    String CAR_INF = "Mark: Toyota\nModel: Carina\nYear: 1999\nVIN: HF27255E864YT\nGosNumber: T635YE77";
    private static final int G = Takegas();
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        dialog = new Dialog(CarActivity.this);



        Button button4 =  findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ShowInfo(Integer.toString(G));
            }
        });

        }
    private void showInputDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME,Context.MODE_PRIVATE);
        EditText txt = new EditText(context);
        builder.setTitle("Данные о вашей машине: \n")
                .setMessage(CAR_INF)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveDataToCache(txt.toString());
                        Toast.makeText(CarActivity.this, "^0^", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(CarActivity.this, ">0<", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void saveDataToCache(String TO) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CAR_INF, TO);
        editor.apply();
    }

    public void MyInfo(View v){
        showInputDialog(CarActivity.this);
        setContentView(R.layout.activity_second);
    }
    private static int Takegas(){
        Random rnd = new Random();
        return rnd.nextInt(20 - 4 + 1) + 4;
    }
    private void ShowInfo(String text){
        Toast.makeText( this, text, Toast.LENGTH_LONG).show();
    }
}