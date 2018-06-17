package com.example.morasiu.myplock;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Objects;

public class Informator extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informator);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        findViewById(R.id.InforOne).setOnClickListener(Informator.this);
        findViewById(R.id.InforTwo).setOnClickListener(Informator.this);
        findViewById(R.id.InforThree).setOnClickListener(Informator.this);
        findViewById(R.id.InforFour).setOnClickListener(Informator.this);
        findViewById(R.id.InforFive).setOnClickListener(Informator.this);
        findViewById(R.id.InforSix).setOnClickListener(Informator.this);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.string.main_color))));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, Article.class);
        switch (view.getId()){
            case R.id.InforOne:
                intent.putExtra("id", R.layout.infor_detail1);
                break;
            case R.id.InforTwo:
                intent.putExtra("id", R.layout.infor_detail2);
                break;
            case R.id.InforThree:
                intent.putExtra("id", R.layout.infor_detail3);
                break;
            case R.id.InforFour:
                intent.putExtra("id", R.layout.infor_detail4);
                break;
            case R.id.InforFive:
                intent.putExtra("id", R.layout.infor_detail5);
                break;
            case R.id.InforSix:
                intent.putExtra("id", R.layout.infor_detail6);
                break;
            default:
                Log.e("ARTICLE", "Wrong activity");
                break;
        }

        startActivity(intent);
    }
}
