package ics.infortainment_control.commands;

/**
 *  An interface outlining how Devices are handled.
 */
public interface DeviceManager {
    CodeProvider getCodeProvider();

    void registerDevice(DeviceID deviceID);
    void removeDevice(DeviceID deviceID);

    DeviceID getActiveDevice();
    DeviceID setActiveDevice(DeviceID deviceID);

    // TODO figure out what should be issuing commands... and clean up this spike
    // TODO also, this dude should definitely throw exceptions for unsupported commands, at least given the looooose, dynamic architecture right now...
    String getRawCommandCode(DeviceID deviceID, TelevisionCommand command);

}
