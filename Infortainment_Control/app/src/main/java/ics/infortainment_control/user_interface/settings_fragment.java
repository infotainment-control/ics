package ics.infortainment_control.user_interface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ics.infortainment_control.R;
import ics.infortainment_control.devices.AbstractDevice;
import ics.infortainment_control.devices.DeviceManager;
import ics.infortainment_control.devices.DeviceType;
import ics.infortainment_control.devices.SimpleDeviceManager;
import ics.infortainment_control.devices.UserDevice;

public class settings_fragment extends Fragment {

    // TODO should privatize and define setter for DeviceManager (OR!!! AppManager??) to work through
    public static Collection<AbstractDevice> devices;

    private ArrayAdapter<String> adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.settings_layout,containter,false);
        View mine = v.findViewById(R.id.manufacturer_input);
        Log.d("mark-test", v.findViewById(R.id.manufacturer_input).getClass().getCanonicalName());

        // for demonstration purposes, this indicates the registry service has partial functionality
        toggleTelevisions();


        /*

        View v = inflater.inflate(R.layout.tv_layout,container,false);

        _power = (Button) v.findViewById(R.id.power_btn);

         */

        List<String> testStrings = new ArrayList<>(5);
        testStrings.add("a");
        testStrings.add("b");
        testStrings.add("c");
        testStrings.add("d");
        testStrings.add("e");

        Spinner spinner = (Spinner) v.findViewById(R.id.manufacturer_input);


//        adapter = new ArrayAdapter<String>(this, R.layout.settings_layout, testStrings.toArray());
        adapter = new ArrayAdapter<String>(this.getContext(), R.layout.settings_layout, R.id.manufacturer_input_label, testStrings);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

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
