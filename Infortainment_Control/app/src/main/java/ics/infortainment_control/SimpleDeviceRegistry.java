package ics.infortainment_control;

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
    public Device registerDevice(DeviceID deviceID) {
        Device registeredDevice = roster.get(D)
    }

    @Override
    public boolean removeDevice(DeviceID deviceID) {
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
    public boolean isInRegistry(DeviceID deviceID) {
        return false;
    }

    @Override
    public Device getDevice(DeviceID deviceID) {
        return null;
    }
}
