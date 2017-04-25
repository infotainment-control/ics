package ics.infortainment_control.devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// TODO ought to be a lazily instantiated singleton
public class SimpleDeviceRegistry implements DeviceRegistry {

    // associates a UserDevice's name to its Device interface
    private Map<String, AbstractDevice> userDevices;

    SimpleDeviceRegistry() {
        userDevices  = new HashMap<>();
    }

    @Override
    public AbstractDevice registerDevice(String deviceName, Device device) {
        // TODO this may be an error condition
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
