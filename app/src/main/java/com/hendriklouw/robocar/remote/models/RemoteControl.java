package com.hendriklouw.robocar.remote.models;


import android.util.Log;

import com.hendriklouw.robocar.remote.api.RemoteControlAPI;
import com.hendriklouw.robocar.remote.api.RemoteControlApiResult;
import com.hendriklouw.robocar.remote.modules.DaggerRemoteControlComponent;
import com.hendriklouw.robocar.remote.modules.RemoteControlApiModule;
import com.hendriklouw.robocar.remote.modules.RemoteControlComponent;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteControl {
    private final RemoteControlComponent mRemoteControlComponent;
    @Inject
    RemoteControlAPI remoteControlAPI;

    public RemoteControl() {
        mRemoteControlComponent = DaggerRemoteControlComponent.builder()
                // list of modules that are part of this component need to be created here too
                .remoteControlApiModule(new RemoteControlApiModule())
                .build();
        mRemoteControlComponent.inject(this);
    }

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
