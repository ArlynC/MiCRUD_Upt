package com.example.micrud_upt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface servicesRetrofit {

    @GET("products/test")
    Call<String> getcheck();

    @POST("products/create")
    Call<String> newproducto(@Body Productos producto);

    @GET("products/list")
    Call<List<Productos>> getlist();
}
