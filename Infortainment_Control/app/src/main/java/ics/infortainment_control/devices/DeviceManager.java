package ics.infortainment_control.devices;

import ics.infortainment_control.commands.CodeProvider;
import ics.infortainment_control.commands.TelevisionCommand;

/**
 *  An interface outlining how Devices are handled.
 */
@Deprecated //TODO modify it to complement DeviceRegistry
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
