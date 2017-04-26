package ics.infortainment_control.user_interface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import ics.infortainment_control.R;
import ics.infortainment_control.devices.AbstractDevice;
import ics.infortainment_control.devices.DeviceManager;
import ics.infortainment_control.devices.DeviceType;
import ics.infortainment_control.devices.SimpleDeviceManager;
import ics.infortainment_control.devices.UserDevice;

public class settings_fragment extends Fragment {

    public static Collection<AbstractDevice> devices;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.settings_layout,containter,false);
        View mine = v.findViewById(R.id.dvd_manufacturer_input);
        Log.d("mark-test", v.findViewById(R.id.dvd_manufacturer_input).getClass().getCanonicalName());

        // for demonstration purposes, this indicates the registry service has partial functionality
        toggleTelevisions();

        return v;
    }

    private void toggleTelevisions() {
        DeviceManager manager = SimpleDeviceManager.getInstance();

        AbstractDevice activeTelevision = manager.getActiveDevice(DeviceType.TELEVISION);

        for(AbstractDevice device : devices) {
            if(((UserDevice)device).getType() == DeviceType.TELEVISION
                &&  device != activeTelevision ) {
                manager.setActiveDevice(device);
            }
        }

    }
}
