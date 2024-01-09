package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import java.io.IOException;
import java.util.Base64;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;


public class SecondActiv extends AppCompatActivity {

    private static final String SHARED_PREFS_NAME = "my_cache";
    final String DATA_KEY_TO = "Next_TO";
    private static final String ADD_GAS = "gasoline";

    SharedPreferences sPref;
    Registration reg = new Registration();

    MainActivity mainy = new MainActivity();
    RemoteServerConnection Server = new RemoteServerConnection();
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dialog = new Dialog(SecondActiv.this);
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

    private void EverToast(Context context, String current_km){
        showToast(current_km+"km");
    }
    private void MonthToast(Context context, String month_km){
        showToast(month_km+"km");
    }

    public void ButtnClick(View v){//Все кнопки вынесены вне класса инициализации, иначе после нажатия любой, остальные перестанут работать
        showInputDialog(SecondActiv.this);//TO
        setContentView(R.layout.activity_second);
    }

    public void AddGasClick(View v){
        showInputDialog2(SecondActiv.this, ADD_GAS);
        setContentView(R.layout.activity_second);
    }//Кнопка заправки

    public void MonthStat(View v){//Метод работы кнопки вывода информации об аккаунте
    }

    public void YearStat(View v){//Метод работы кнопки годовой статистики
    }

    public void CarInfo(View v){//Переход дублируется по той же причине, если этого не сделать, то он перестанет работать
        Intent intent = new Intent(SecondActiv.this, CarActivity.class);
        startActivity(intent);
    }
    private void showInputDialog(Context context) {//Метод, реализующий часть логики кнопки "следующее ТО"

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String Hu = DATA_KEY_TO+ mainy.findViewById(R.id.editTextTextEmailAddress).toString();
        sPref = getSharedPreferences(Hu , MODE_PRIVATE);
        EditText txt = new EditText(context);
        txt.setText(sPref.getString(Hu, "")+"\n(поставьте нужную дату или нажмите 'отмена')");
        builder.setTitle("Когда следующее ТО?\n")
                .setView(txt)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor ed = sPref.edit();

                        ed.putString(Hu, txt.getText().toString());
                        ed.apply();
                        showToast("Данные сохранены успешно! ");
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(SecondActiv.this, "Данные не сохранены", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showInputDialog2(Context context, String INUSE) {//Метод реализующий часть кнопки "Сколько заправили"

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        sPref = getSharedPreferences(ADD_GAS,MODE_PRIVATE);
        EditText txt = new EditText(context);

        builder.setTitle("Сколько заправили сейчас?\n")
                .setView(txt)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString(ADD_GAS,txt.getText().toString());
                        ed.commit();
                        showToast("Заправка добавлена!");
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(SecondActiv.this, "Данные не сохранены", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void showToast(String message) {
        Toast.makeText(SecondActiv.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //ShowInfo("Start");
    }

    @Override
    protected void onResume() {

        super.onResume();
       // showToast("Resume");
    }

    @Override
    protected void onPause() {

        super.onPause();
        //ShowInfo("Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //ShowInfo("Stop");
    }

    @Override
    protected void onRestart() {
        //ShowInfo("Restart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ShowInfo("Destroy");
    }

    private void ShowInfo(String text){
        Toast.makeText( this, text, Toast.LENGTH_LONG).show();
    }
}