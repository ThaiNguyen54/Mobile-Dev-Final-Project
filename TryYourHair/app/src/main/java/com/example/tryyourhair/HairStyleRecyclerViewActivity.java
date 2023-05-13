package com.example.tryyourhair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import CustomAdapter.HairStyleAdapter;
import Models.HairStyle;
import Models.HairstyleDataCallFromAPI;
import RetrofitInstance.RetrofitClient;
import RetrofitInterface.Methods;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HairStyleRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rvHairStyle;
    private HairStyleAdapter hairStyleAdapter;
    private List<HairStyle> listHairStyle;
    private ImageView img_home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hairstyle_list_recycler_view);

        img_home = findViewById(R.id.img_home);

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeScreenIntent = new Intent(HairStyleRecyclerViewActivity.this, HomeScreen.class);
                startActivity(HomeScreenIntent);
            }
        });


        Thread GetAllHairStyleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                rvHairStyle = (RecyclerView) findViewById(R.id.rv_hairstyle);
                listHairStyle = new ArrayList<>();


                // Call API get Hairstyle
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<HairstyleDataCallFromAPI> call = methods.getAllData();
                call.enqueue(new Callback<HairstyleDataCallFromAPI>() {
                    @Override
                    public void onResponse(Call<HairstyleDataCallFromAPI> call, Response<HairstyleDataCallFromAPI> response) {
                        ArrayList<HairstyleDataCallFromAPI.data> Hairstyles = response.body().getHairstyles();
                        for (int i = 0; i < Hairstyles.size(); i++) {
                            Log.d("TEST",  Hairstyles.get(i).get_id());


                            listHairStyle.add(new HairStyle(
                                    Hairstyles.get(i).get_id(),
                                    Hairstyles.get(i).getUrl(),
                                    Hairstyles.get(i).getDes()));

                            hairStyleAdapter = new HairStyleAdapter(HairStyleRecyclerViewActivity.this, listHairStyle);

                            rvHairStyle.setAdapter(hairStyleAdapter);

//                    // Recycler view scroll vertical
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HairStyleRecyclerViewActivity.this, LinearLayoutManager.VERTICAL, false);

                            // Use GridLayout
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(HairStyleRecyclerViewActivity.this, 2);
                            rvHairStyle.setLayoutManager(gridLayoutManager);
                        }
                    }

                    @Override
                    public void onFailure(Call<HairstyleDataCallFromAPI> call, Throwable t) {

                    }
                });
            }
        });
        GetAllHairStyleThread.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent HomeScreenIntent = new Intent(this, HomeScreen.class);
        startActivity(HomeScreenIntent);
    }
}