package ics.infortainment_control.devices;

import java.util.List;

/**
 *  An interface outlining how Devices are handled.
 *
 *  Implementations ought to expose themselves as lazily-instantiated singletons.
 */
//TODO modify responsibilities to complement DeviceRegistry
public interface DeviceManager {

    // to be used by UI views in order to delegate properly
    AbstractDevice getActiveDevice(DeviceType deviceType);

    // to be used in the user devices view, AND when a device is created
    void setActiveDevice(AbstractDevice abstractDevice);

    // TODO ought not to crash, or whatnot, if the deviceName is invalid:
    //      should be part of a UI-located, delegated validation to two components:
    //             1) a class that understands what characters etc. are acceptable to be in the String
    //             2) the Registry, which due to the mandate of uniqueness among device names, will need
    //                to cross-reference the name arg against existing registered devices (check for equality)

    // adds the device to the registry
    void createDevice(String deviceName, String deviceID, DeviceType deviceType);

    // TODO delegate to the registry to retrieve all information a user needs
    //      about its registered UserDevices in order to interact with them
    List<UserDevice> getRegisteredDevices(DeviceType deviceType);

    // TODO delegate to the registry to leverage its loading pattern
    void loadDeviceRegistry();
}
