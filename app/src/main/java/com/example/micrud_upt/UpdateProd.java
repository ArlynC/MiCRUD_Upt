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

public class UpdateProd extends AppCompatActivity {

    Retrofit retrofit;
    servicesRetrofit miserviceretrofit;
    EditText edtnombre;
    EditText edtprecio;
    Productos miproducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prod);
        final String url = "http://productosuptarlyn.eu-4.evennode.com/";
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        miserviceretrofit = retrofit.create(servicesRetrofit.class);
        edtnombre = findViewById(R.id.edtnomprod);
        edtprecio = findViewById(R.id.edtprecio);
    }

    public void updateproducto(View view) {
        final Productos producto = new Productos(edtnombre.getText().toString(),
                Integer.parseInt(edtprecio.getText().toString()));
        Call<String> call = miserviceretrofit.updateproducto(miproducto.getId(), producto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("updateproducto: ", "check:" + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("updateproducto", t.toString());
            }
        });
    }

    public void buscar(View view) {
        Call<Productos> call = miserviceretrofit.getproducto(edtnombre.getText().toString());
        call.enqueue(new Callback<Productos>() {
            @Override
            public void onResponse(Call<Productos> call, Response<Productos> response) {
                Log.e("cargarproducto: ","check:"+response.body().getName()+", "+
                        response.body().getPrice()+", "+response.body().getId());
                miproducto=new Productos(response.body().getName(),response.body().getPrice(),response.body().getId());
                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      edtprecio.setText(String.valueOf(miproducto.getPrice()));
                                  }
                              }
                );
            }
            @Override
            public void onFailure(Call<Productos> call, Throwable t) {
                Log.e("cargarproducto",t.toString());
            }
        });
    }

}
