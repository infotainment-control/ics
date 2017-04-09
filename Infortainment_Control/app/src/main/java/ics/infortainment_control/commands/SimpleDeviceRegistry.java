package ics.infortainment_control.commands;

import java.util.HashMap;
import java.util.Map;

import ics.infortainment_control.commands.Device;
import ics.infortainment_control.commands.DeviceID;
import ics.infortainment_control.commands.DeviceRegistry;

public class SimpleDeviceRegistry implements DeviceRegistry {

    private Map<String, Device> registry;

    public SimpleDeviceRegistry() {
        registry = new HashMap<>();
    }

    @Override
    public Device registerDevice(String deviceID) {
        if (registry.containsKey(deviceID)) {
            return registry.get(deviceID);
        }

        // TODO device creation requires code provision
        Device registeredDevice = new Device(deviceID);

        return registeredDevice;
    }

    @Override
    public boolean removeDevice(String deviceID) {
        return false;
    }

    @Override
    public boolean removeDevice(Device device) {
        return false;
    }

    // TODO iffy that this might exist, hmm?
//    @Override
//    public boolean isInRegistry(Device device) {
//        return registry.containsValue(device);
//    }

    @Override
    public boolean isInRegistry(String deviceID) {
        return registry.containsKey(deviceID);
    }

    @Override
    public Device getDevice(String deviceID) {
        return registry.get(deviceID);
    }
}
