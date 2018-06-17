package com.example.morasiu.myplock;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class MyAccount extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Spinner dropdown = findViewById(R.id.accountSpinner);
        String[] items = new String[]{"Filtrowanie wydarzeń", "Społecznościowe", "Sportowe", "Biznesowe", "Kulturalne", "Użytkowników"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        findViewById(R.id.changePassword).setOnClickListener(this);
        findViewById(R.id.harmonogramBtn).setOnClickListener(this);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.main_color))));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.changePassword){
            Toast.makeText(this, "Nie zmieniaj hasła, proszę :(", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.harmonogramBtn){
            Toast.makeText(this, "Wszystko jest zapamiętane w głowie.", Toast.LENGTH_SHORT).show();
        }
    }
}
