package com.example.morasiu.myplock;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class Report extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner dropdown = findViewById(R.id.chooseService);
        String[] items = new String[]{"Urząd miasta", "Komunikacja miejska", "Zarząd dróg", "Straż miejska"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        findViewById(R.id.sendReportBtn).setOnClickListener(this);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.main_color))));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sendReportBtn)
            Toast.makeText(this, "Wysłano zgłoszenie",Toast.LENGTH_SHORT).show();
    }
}
