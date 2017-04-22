package ics.infortainment_control.devices;

/**
 *  An interface outlining how Devices are handled.
 */
//TODO modify responsibilities to complement DeviceRegistry
public interface DeviceManager {

    DeviceRegistry getDeviceRegistry();

    AbstractDevice getActiveDevice();

    void setActiveDevice(String deviceID);

    void setActiveDevice(AbstractDevice abstractDevice);


}
