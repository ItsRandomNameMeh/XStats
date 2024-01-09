package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.EditText;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;


public class CarActivity extends AppCompatActivity {

    Dialog dialog;

    String CAR_INF = "Mark: Toyota\nModel: Carina\nYear: 1999\nVIN: HF27255E864YT\nGosNumber: T635YE77";
    final String AddCost = "AddCost";

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        dialog = new Dialog(CarActivity.this);



        Button button4 =  findViewById(R.id.fuelbtn);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        }
    private void showInputDialog(Context context) {//метод реализующий логику кнопки "MyCar"
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText txt = new EditText(context);
        builder.setTitle("Данные о вашей машине: \n")
                .setMessage(CAR_INF)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
    public void MyInfo(View v){
        showInputDialog(CarActivity.this);
        setContentView(R.layout.activity_car);
    }

    public void AddCost(View v, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        EditText txt = new EditText(context);


        builder.setTitle("Сколько потратили в этот раз?\n")
                .setView(txt)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor ed = sPref.edit();

                        ed.putString(AddCost, txt.getText().toString());
                        ed.commit();
                        ShowInfo("Данные сохранены");
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(CarActivity.this, "Данные не сохранены", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void GasInfo(View v){//Метод реализующий кнопку Fines

    }
    private void ShowInfo(String text){
        Toast.makeText( this, text, Toast.LENGTH_LONG).show();
    }
}