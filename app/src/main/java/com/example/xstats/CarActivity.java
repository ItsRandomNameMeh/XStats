package com.example.xstats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import java.sql.*;
import android.os.Bundle;
import android.app.Dialog;


public class CarActivity extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        dialog = new Dialog(CarActivity.this);



        }
}