package com.example.tryyourhair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tryyourhair.Singleton.Singleton;

public class GeneratedHair extends AppCompatActivity {
    ConstraintLayout constraintLayout_loading;
    ConstraintLayout constraintLayout_generated_hair;
    Singleton singleton;
    ImageView img_generated_hair;
    Button btn_home;
    Button btn_download;
    Button btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_hair);

        singleton = Singleton.getInstance();
        constraintLayout_loading = findViewById(R.id.constraint_layout_loading);
        constraintLayout_generated_hair = findViewById(R.id.constraint_layout_generated_hair);
        img_generated_hair = findViewById(R.id.img_generated_hair);
        btn_download = findViewById(R.id.btn_download);
        btn_home = findViewById(R.id.btn_home);
        btn_share = findViewById(R.id.btn_share);

        if (!singleton.getReceivedGeneratedHair()) {
            constraintLayout_generated_hair.setVisibility(View.INVISIBLE);
            constraintLayout_loading.setVisibility(View.VISIBLE);
        }
        if (singleton.getReceivedGeneratedHair()) {
            singleton.setReceivedGeneratedHair(false);
            constraintLayout_loading.setVisibility(View.INVISIBLE);
            constraintLayout_generated_hair.setVisibility(View.VISIBLE);
            Glide.with(this).load(singleton.getGeneratedURL()).into(img_generated_hair);
        }

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeIntent = new Intent(GeneratedHair.this, HomeScreen.class);
                startActivity(HomeIntent);
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent HomeScreenIntent = new Intent(this, HomeScreen.class);
        startActivity(HomeScreenIntent);
    }
}