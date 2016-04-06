package com.hendriklouw.robocar.remote.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.hendriklouw.robocar.R;
import com.hendriklouw.robocar.remote.models.RemoteControl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicRemoteActivity extends AppCompatActivity {
    @Bind(R.id.forward_button)
    Button forwardButton;

    @OnClick(R.id.forward_button)
    public void goForward() {
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.forward();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_remote);
        ButterKnife.bind(this);
    }
}
