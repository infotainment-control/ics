package ics.infortainment_control.devices;

import ics.infortainment_control.commands.CodeProvider;

/**
 *  An interface outlining how Devices are handled.
 */
//TODO modify responsibilities to complement DeviceRegistry
public interface DeviceManager {

    // TODO consider if this ought to, or if registry ought to, or if a device factory ought to provision codes
    CodeProvider getCodeProvider();


    DeviceRegistry getDeviceRegistry();

    String getActiveDevice();
    String setActiveDevice(String deviceID);

}
