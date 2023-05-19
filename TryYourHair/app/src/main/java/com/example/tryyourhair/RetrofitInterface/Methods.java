package com.example.tryyourhair.RetrofitInterface;

import com.example.tryyourhair.Models.HairstyleDataCallFromAPI;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
//    @GET("ver1/hairstyle")
//    Call <TestAPIHairstyle> getAllData();

    @GET("ver1/hairstyle")
    Call <HairstyleDataCallFromAPI> getAllData();
}
