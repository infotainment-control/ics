package ics.infortainment_control.devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ics.infortainment_control.commands.CodeProvider;
import ics.infortainment_control.commands.Command;
import ics.infortainment_control.commands.SimpleCodeProvider;

// TODO ought to be a lazily instantiated singleton
public class SimpleDeviceRegistry implements DeviceRegistry {

    // maps deviceID to Device object
    private Map<String, AbstractDevice> userDevices;

    private CodeProvider codeProvider;

    public SimpleDeviceRegistry() {
        userDevices = new HashMap<>();
        codeProvider = new SimpleCodeProvider();
    }

    @Override
    public AbstractDevice registerDevice(String deviceName, Device device) {
        if (userDevices.containsKey(deviceName)) {
            return userDevices.get(deviceName);
        }

        AbstractDevice abstractDevice = new UserDevice(deviceName, device);

        userDevices.put(deviceName, abstractDevice);

        return abstractDevice;
    }

    @Override
    public AbstractDevice removeDevice(String deviceName) {
        return userDevices.remove(deviceName);
    }

    @Override
    public boolean isInRegistry(String deviceName) {
        return userDevices.containsKey(deviceName);
    }

    @Override
    public AbstractDevice getDevice(String deviceName) {
        return userDevices.get(deviceName);
    }

    @Override
    public Set<AbstractDevice> loadRegisteredDevices() {
        // TODO load them! Statically, I mean! Like, the devices SimpleCodeProvider will accommodate
        return new HashSet<>(userDevices.values());
    }


    @Override
    public List<UserDevice> getOrderedListOfUserDevices(DeviceType deviceType) {
        List<UserDevice> userDeviceList = new ArrayList<>(userDevices.size());

        // TODO process the registry >:)
        for(Map.Entry<String, AbstractDevice> entry : userDevices.entrySet()) {
            // order them by DeviceType and active and date created
        }
        return null;
    }

}
