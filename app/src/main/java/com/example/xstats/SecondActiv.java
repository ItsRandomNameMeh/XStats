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
    private static final String DATA_KEY_TO = "Next_TO";
    private static final String ADD_GAS = "gasoline";
    ServerCommunication serverConnector;
     Registration reg = new Registration();
     int HH = reg.UID;
    String sqlquery;

    private SharedPreferences sharedPreferences;
    public String to;

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

    public void ButtnClick(View v){//Все кнопки вынесены вне класса инициализации, иначе после нажатия любой, остальные перестанут работать
        showInputDialog(SecondActiv.this, DATA_KEY_TO);
        setContentView(R.layout.activity_second);
    }

    public void AddGasClick(View v){
        showInputDialog2(SecondActiv.this, ADD_GAS);
        setContentView(R.layout.activity_second);
    }//Кнопка заправки

    public void MonthStat(View v){
        Handler handler = new Handler(Looper.getMainLooper());
        sqlquery = "SELECT monthst from stat where id_stat = '" +HH+"'";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverConnector = ServerCommunication.getInstance();
                    serverConnector.sendMessage(sqlquery);
                    String G = serverConnector.receiveMessage();
                    handler.post(new Runnable() {//
                        @Override
                        public void run() {
                            EverToast(SecondActiv.this, G);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void YearStat(View v){
        Handler handler = new Handler(Looper.getMainLooper());
        sqlquery = "SELECT yearst from stat where id_stat = '" +HH+"'";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverConnector = ServerCommunication.getInstance();
                    serverConnector.sendMessage(sqlquery);
                    String G = serverConnector.receiveMessage();
                    handler.post(new Runnable() {//
                        @Override
                        public void run() {
                            EverToast(SecondActiv.this, G);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void CarInfo(View v){//Переход дублируется по той же причине, если этого не сделать, то он перестанет работать
        Intent intent = new Intent(SecondActiv.this, CarActivity.class);
        startActivity(intent);
    }
    private void showInputDialog(Context context, String INUSE) {
        Handler handler = new Handler(Looper.getMainLooper());
        sqlquery = "SELECT dateto from todate WHERE uid = '" +HH+"'";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME,Context.MODE_PRIVATE);
        EditText txt = new EditText(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverConnector = ServerCommunication.getInstance();
                    serverConnector.sendMessage(sqlquery);
                    String G = serverConnector.receiveMessage();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ShowInfo("Прошлое ТО:  "+ G );
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        builder.setTitle("Когда следующее ТО?\n")
                .setView(txt)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sqlsend = "UPDATE todate SET dateto = '"+ txt.getText() +"' WHERE uid = '" +HH+ "';";
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    serverConnector = ServerCommunication.getInstance();
                                    serverConnector.sendMessage(sqlsend);
                                    String G = serverConnector.receiveMessage();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
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
        Handler handler = new Handler(Looper.getMainLooper());
        sqlquery = "SELECT gas from gas where uid = '"+HH+"'";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME,Context.MODE_PRIVATE);
        EditText txt = new EditText(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverConnector = ServerCommunication.getInstance();
                    serverConnector.sendMessage(sqlquery);
                    String G = serverConnector.receiveMessage();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ShowInfo("Прошлая заправка была на "+ G + " литров.");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        builder.setTitle("Сколько заправили сейчас?\n")
                .setView(txt)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sqlsend = "UPDATE gas SET gas = '"+ txt.getText() +"'  WHERE uid = '"+HH+"';";
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    serverConnector = ServerCommunication.getInstance();
                                    serverConnector.sendMessage(sqlsend);
                                    String G = serverConnector.receiveMessage();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
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