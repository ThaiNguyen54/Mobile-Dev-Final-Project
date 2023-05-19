package com.example.tryyourhair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeScreen extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    ImageView chose_hairstyle_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        chose_hairstyle_img = findViewById(R.id.img_chose_hairstyle);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.item_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_camera:
                        OpenCameraActivity();
                        break;

                    case R.id.item_hairstyle:
                        OpenListHairActivity();
                        break;
                }
                return true;
            }
        });
        String Url = getIntent().getStringExtra("URL");
        String Chose_Hairstyle_Name = getIntent().getStringExtra("NAME");
        Glide.with(this).load(Url).into(chose_hairstyle_img);
    }

    public void OpenCameraActivity() {
        Intent CameraIntent = new Intent(this, OpenCamera.class);
        startActivity(CameraIntent);
    }

    public void OpenGallery(){}

    public void OpenHomeActivity(){
        Intent HomeIntent = new Intent(this, HomeScreen.class);
        startActivity(HomeIntent);
    }

    public void OpenListHairActivity() {
        Intent ListHairIntent = new Intent(this, HairStyleRecyclerViewActivity.class);
        startActivity(ListHairIntent);
    }
}