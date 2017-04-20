package ics.infortainment_control.devices;

import ics.infortainment_control.commands.CodeProvider;
import ics.infortainment_control.commands.SimpleCodeProvider;
import ics.infortainment_control.commands.TelevisionCommand;

/**
 *  A class to control use of Devices.
 */
public class SimpleDeviceManager implements DeviceManager {

    private static final DeviceRegistry registry = new SimpleDeviceRegistry();

    @Override
    public CodeProvider getCodeProvider() {
        return null;
    }

    @Override
    public DeviceRegistry getDeviceRegistry() {
        return null;
    }

    @Override
    public String getActiveDevice() {
        return null;
    }

    @Override
    public String setActiveDevice(String deviceID) {
        return null;
    }
}
