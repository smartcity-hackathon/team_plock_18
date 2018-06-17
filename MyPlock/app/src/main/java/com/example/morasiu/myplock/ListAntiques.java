package com.example.morasiu.myplock;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class ListAntiques extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_antiques);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.main_color))));

        findViewById(R.id.antique1).setOnClickListener(this);
        findViewById(R.id.antique2).setOnClickListener(this);
        findViewById(R.id.antique3).setOnClickListener(this);
        findViewById(R.id.antique4).setOnClickListener(this);
        findViewById(R.id.antique5).setOnClickListener(this);
        findViewById(R.id.antique6).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.antique1:
                intent = new Intent(this, AntiqueDetails1.class);
                break;
            case R.id.antique2:
                intent = new Intent(this, AntiqueDetails2.class);
                break;
            case R.id.antique3:
                intent = new Intent(this, AntiqueDetails3.class);
                break;
            case R.id.antique4:
                intent = new Intent(this, AntiqueDetails4.class);
                break;
            case R.id.antique5:
                intent = new Intent(this, AntiqueDetails5.class);
                break;
            case R.id.antique6:
                intent = new Intent(this, AntiqueDetails6.class);
                break;

        }

        startActivity(intent);
    }
}
