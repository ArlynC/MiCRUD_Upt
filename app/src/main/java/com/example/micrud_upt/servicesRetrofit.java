package com.example.micrud_upt;

import retrofit2.Call;
import retrofit2.http.GET;

public interface servicesRetrofit {

    @GET("products/test")
    Call<String> getcheck();

}
