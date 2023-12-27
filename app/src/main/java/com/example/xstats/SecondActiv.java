package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;





import com.google.android.material.button.MaterialButton;

import java.util.Calendar;


public class SecondActiv extends AppCompatActivity {


    // Получение экземпляра SharedPreferences
    //SharedPreferences sharedPreferences = getSharedPreferences("my_cache", Context.MODE_PRIVATE);


    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dialog = new Dialog(SecondActiv.this);

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MonthToast(SecondActiv.this, "2131" );
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EverToast(SecondActiv.this, "317132");
            }
        });
        Button CarForm = findViewById(R.id.aboutcar);
        View.OnClickListener goCar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActiv.this, CarActivity.class);
                startActivity(intent);
            }
        };
        CarForm.setOnClickListener(goCar);

    }

//    private void showCustomDialog() {//NextTo button click realize
//        dialog.setContentView(R.layout.custom_dialog_layout);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(true);
//
//        Button Apply = dialog.findViewById(R.id.materialButtonApply);
//        Button Cancel = dialog.findViewById(R.id.materialButtonCansel);
//        EditText STO = findViewById(R.id.currentSTO);
//
//        STO.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(SecondActiv.this,"Completed!", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        dialog.show();
//    }
    private void EverToast(Context context, String current_km){
        showToast(current_km+"km");
    }
    private void MonthToast(Context context, String month_km){
        showToast(month_km+"km");
    }

    public void ButtnClick(View v){
        showInputDialog(SecondActiv.this);
        setContentView(R.layout.activity_second);
        String s = SetTxt("Типо подсказка");
        ShowInfo(s);
    }

    private void showInputDialog(Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Next To");

        final EditText input = new EditText(context);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("ОК",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String userInput = input.getText().toString();
                        showToast("дата следующего ТО (тут должна быть запись в БД): " + userInput);
                      //  SharedPreferences.Editor editor = sharedPreferences.edit();
                       // editor.putString("next_to", userInput);
                        //editor.apply();
                    }
                });

        alertDialog.setNegativeButton("Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(SecondActiv.this, message, Toast.LENGTH_SHORT).show();
    }

    public String SetTxt(String text)
    {
        String S = text;
        return  S;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ShowInfo("Start");
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
        ShowInfo("Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShowInfo("Stop");
    }

    @Override
    protected void onRestart() {
        ShowInfo("Restart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShowInfo("Destroy");
    }

    private void ShowInfo(String text){
        Toast.makeText( this, text, Toast.LENGTH_LONG).show();
    }
}