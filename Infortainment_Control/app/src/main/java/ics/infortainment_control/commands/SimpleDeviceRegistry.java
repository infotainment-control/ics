package ics.infortainment_control.commands;

import java.util.HashMap;
import java.util.Map;

import ics.infortainment_control.commands.Device;
import ics.infortainment_control.commands.DeviceID;
import ics.infortainment_control.commands.DeviceRegistry;

public class SimpleDeviceRegistry implements DeviceRegistry {

    private Map<String, Device> roster;

    public SimpleDeviceRegistry() {
        roster = new HashMap<>();
    }

    @Override
    public Device registerDevice(String deviceID) {
        if (roster.containsKey(deviceID)) {
            return roster.get(deviceID);
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

    @Override
    public boolean isInRegistry(Device device) {
        return false;
    }

    @Override
    public boolean isInRegistry(String deviceID) {
        return false;
    }

    @Override
    public Device getDevice(String deviceID) {
        return null;
    }
}
