package remote.models;


import com.hendriklouw.robocar.remote.models.RemoteControl;

import org.junit.Test;

public class RemoteControlTest {
    @Test
    public void shouldCallTheRemoteControlForwardMethodWhenGoingForward() {
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.forward();
    }
}
