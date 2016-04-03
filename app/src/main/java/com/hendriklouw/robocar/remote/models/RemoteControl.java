package com.hendriklouw.robocar.remote.models;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteControl {
    public static final String BASE_URL = " http://192.168.2.25:3000/api/robots/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RemoteControlAPI remoteControlAPI = retrofit.create(RemoteControlAPI.class);

    public void forward() {
        remoteControlAPI
                .forward()
                .enqueue(apiCallbacks());
    }

    private Callback<RemoteControlApiResult> apiCallbacks() {
        return new Callback<RemoteControlApiResult>() {
            @Override
            public void onResponse(Call<RemoteControlApiResult> call, Response<RemoteControlApiResult> response) {
                RemoteControlApiResult remoteControlApiResult = response.body();
                Log.d("RemoteControl", remoteControlApiResult.result);
            }

            @Override
            public void onFailure(Call<RemoteControlApiResult> call, Throwable t) {
                Log.d("RemoteControl", call.toString(), t);
            }
        };
    }
}
