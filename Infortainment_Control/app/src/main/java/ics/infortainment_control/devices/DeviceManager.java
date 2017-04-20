package ics.infortainment_control.devices;

import ics.infortainment_control.commands.CodeProvider;

/**
 *  An interface outlining how Devices are handled.
 */
//TODO modify responsibilities to complement DeviceRegistry
public interface DeviceManager {

    DeviceRegistry getDeviceRegistry();

    Device getActiveDevice();

    void setActiveDevice(String deviceID);

    void setActiveDevice(Device device);


}
