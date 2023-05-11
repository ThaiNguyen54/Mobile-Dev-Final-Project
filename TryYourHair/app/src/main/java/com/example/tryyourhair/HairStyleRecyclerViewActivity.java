package com.example.tryyourhair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import CustomAdapter.HairStyleAdapter;
import Models.HairStyle;

public class HairStyleRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rvHairStyle;
    private HairStyleAdapter hairStyleAdapter;
    private List<HairStyle> listHairStyle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rvHairStyle = (RecyclerView) findViewById(R.id.rv_hairstyle);

        // create hairstyle data
        listHairStyle = new ArrayList<>();
        listHairStyle.add(new HairStyle("abc", "son tung's hair"));
        listHairStyle.add(new HairStyle("sdfa", "dan truong's hair"));
        listHairStyle.add(new HairStyle("xcvv", "Dam Vinh Hung's hair"));
        listHairStyle.add(new HairStyle("gbx", "Jack's hair"));

        hairStyleAdapter = new HairStyleAdapter(this, listHairStyle);

        rvHairStyle.setAdapter(hairStyleAdapter);

        // Recycler view scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // Use GridLayout
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvHairStyle.setLayoutManager(gridLayoutManager);
    }
}