package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import java.io.IOException;
import java.util.Random;
import java.util.Base64;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;


public class SecondActiv extends AppCompatActivity {

    private static final String SHARED_PREFS_NAME = "my_cache";
    private static final String DATA_KEY_TO = "Next_TO";
    private static final String ADD_GAS = "gasoline";

    private SharedPreferences sharedPreferences;
    public String to;
    public int km = Generative(20,2000);

    RemoteServerConnection Server = new RemoteServerConnection();
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dialog = new Dialog(SecondActiv.this);
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);

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

    public static int Generative(int min, int max){
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(max - min + 1) + min;
        return randomNumber;
    }
    public void ButtnClick(View v){//Все кнопки вынесены вне класса инициализации, иначе после нажатия любой, остальные перестанут работать
        showInputDialog(SecondActiv.this, DATA_KEY_TO);
        setContentView(R.layout.activity_second);
    }

    public void AddGasClick(View v){
        showInputDialog2(SecondActiv.this, ADD_GAS);
        setContentView(R.layout.activity_second);
    }

    public void MonthStat(View v){
        MonthToast(SecondActiv.this, Integer.toString(km) );
    }

    public void YearStat(View v){
        EverToast(SecondActiv.this, Integer.toString(km*18));
    }

    public void CarInfo(View v){//Переход дублируется по той же причине, если этого не сделать, то он перестанет работать
        Intent intent = new Intent(SecondActiv.this, CarActivity.class);
        startActivity(intent);
    }
    private void showInputDialog(Context context, String INUSE) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME,Context.MODE_PRIVATE);
        EditText txt = new EditText(context);

        ShowInfo("Прошлое ТО было: "+ to +sharedPreferences.getString(INUSE,""));
        builder.setTitle("Введите дату следующего ТО\n")
                .setView(txt)
                .setMessage("Сохранить?")
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveDataToCache(txt.toString());
                        Toast.makeText(SecondActiv.this, "Данные сохранены", Toast.LENGTH_SHORT).show();
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

    private void showInputDialog2(Context context, String INUSE) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME,Context.MODE_PRIVATE);
        EditText txt = new EditText(context);

        ShowInfo("Прошлая заправка была на "+sharedPreferences.getString(INUSE,"") + " литров.");
        builder.setTitle("Сколько заправили сейчас?\n")
                .setView(txt)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveDataToCache2(txt.toString());
                        Toast.makeText(SecondActiv.this, "Данные сохранены", Toast.LENGTH_SHORT).show();
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
    private void saveDataToCache(String TO) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(DATA_KEY_TO, TO);
            editor.commit();
        }
    private void saveDataToCache2(String TO) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ADD_GAS, TO);
        editor.apply();
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
        updateData();
        restartAnimations();
        registerEventListeners();
    }

    private void updateData() {
        // Обновление данных
    }

    private void restartAnimations() {
        // Перезапуск анимаций
    }

    private void registerEventListeners() {
        // Регистрация слушателей событий
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