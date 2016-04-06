package com.hendriklouw.robocar.remote.components;

import com.hendriklouw.robocar.modules.ApiModule;
import com.hendriklouw.robocar.remote.models.RemoteControl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface RemoteControlComponent {
    void inject(RemoteControl remoteControl);
}
