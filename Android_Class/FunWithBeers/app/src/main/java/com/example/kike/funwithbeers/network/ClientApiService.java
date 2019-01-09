package com.example.kike.funwithbeers.network;

import com.example.kike.funwithbeers.models.Flag;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientApiService {
    @GET("all")
    Call<ArrayList<Flag>> getFlags();
}
