package com.hendriklouw.robocar.remote.models;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RemoteControlAPI {
    @POST("dc_motor_bot/commands/forward")
    Call<RemoteControlApiResult> forward();
}