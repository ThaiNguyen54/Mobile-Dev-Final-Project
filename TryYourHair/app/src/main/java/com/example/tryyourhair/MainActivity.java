package com.example.tryyourhair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void OpenCameraActivity(View view) {
        Intent intent = new Intent(this, OpenCamera.class);
        startActivity(intent);
    }

    public void OpenHome(View view) {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}