package ics.infortainment_control.devices;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import ics.infortainment_control.commands.CodeProvider;

/**
 *  A class to control use of Devices.
 */
public class SimpleDeviceManager implements DeviceManager {

    // TODO should be a singleton
    // TODO should delegate retrieval to the DeviceRegistryProvider<<interface>> backed by a SimpleDeviceRegistryProvider
    // TODO enough indirection for you??? WELL????
    private static final DeviceRegistry registry = new SimpleDeviceRegistry();

    private static EnumMap<DeviceType, Device> activeDevices = new EnumMap<>(DeviceType.class);

    // static block that
    // TODO this calls into question all sorts of issues: ought Devices know what type they are all over?
    //      Or ought there be a really damn big, fundamental distinction between Device and UserDevice,
    //      where a user's assertions truly make a clarification...
    static {

        for (DeviceType deviceType :
             DeviceType.values() ) {


        }

    }

    @Override
    public DeviceRegistry getDeviceRegistry() {
        return registry;
    }

    @Override
    public Device getActiveDevice() {
        return null;
    }

    @Override
    public void setActiveDevice(String deviceID) {
    }

    @Override
    public void setActiveDevice(Device device) {

    }


}
