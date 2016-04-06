package com.hendriklouw.robocar.modules;

import com.hendriklouw.robocar.remote.api.RemoteControlAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    public static final String BASE_URL = " http://192.168.2.25:3000/api/robots/";

    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    RemoteControlAPI providesRemoteControlAPI(Retrofit retrofit) {
        RemoteControlAPI remoteControlAPI = retrofit.create(RemoteControlAPI.class);
        return remoteControlAPI;
    }
}