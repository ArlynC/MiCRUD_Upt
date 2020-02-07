package com.example.micrud_upt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CreateProd extends AppCompatActivity {

    Retrofit retrofit;
    servicesRetrofit miserviceretrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_prod);
        final String url = "http://productosuptarlyn.eu-4.evennode.com/";
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        miserviceretrofit = retrofit.create(servicesRetrofit.class);
    }

    public void nuevoproducto(View view) {
        EditText edtnombre;
        EditText edtprecio;
        edtnombre = findViewById(R.id.edtnomprod);
        edtprecio = findViewById(R.id.edtprecio);
        final Productos producto = new Productos(edtnombre.getText().toString(),
                Integer.parseInt(edtprecio.getText().toString()));
        Call<String> call = miserviceretrofit.newproducto(producto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("newproducto: ", "check:" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("newproducto", t.toString());
            }
        });
    }
}
