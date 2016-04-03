package com.hendriklouw.robocar.remote.modules;

import com.hendriklouw.robocar.remote.models.RemoteControl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RemoteControlApiModule.class})
public interface RemoteControlComponent {
    void inject(RemoteControl remoteControl);
}
