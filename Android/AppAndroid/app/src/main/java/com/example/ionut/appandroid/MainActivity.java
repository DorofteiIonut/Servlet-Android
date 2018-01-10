package com.example.ionut.appandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ionut.appandroid.Retrofit.API;
import com.example.ionut.appandroid.Retrofit.ApiUtils;
import com.example.ionut.appandroid.Retrofit.Object;
import com.example.ionut.appandroid.Retrofit.PostObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private API api;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = ApiUtils.getAPI();
        String username = "Eusebiu";
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SingleMenuItemActivity.class));
            }
        });
    }

    public void sendPost() {
        api.sendPost().enqueue(new Callback<PostObject>() {
            @Override
            public void onResponse(Call<PostObject> call, Response<PostObject> response) {

                if (response.isSuccessful()) {
                    Log.d("ListaObiective", "Success!");
                } else {
                    Log.d("ListaObiective", "Cod de erroare !" + response.code());
                }
            }

            @Override
            public void onFailure(Call<PostObject> call, Throwable t) {
                Log.d("ListaObiective", "Nu s-a putut conecta la server!");
            }
        });

    }
    public void getObjects() {

    }
}


