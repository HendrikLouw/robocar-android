package remote.models;


import com.hendriklouw.robocar.remote.models.RemoteControl;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RemoteControlTest {
    @Test
    public void shouldCallTheRemoteControlForwardMethodWhenGoingForward() {
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.forward();

    }
}
