package com.markeffects.samsungoutreach;

/**
 *
 */

public interface DeviceManager {
    CodeProvider getCodeProvider();

    void registerDevice(DeviceID deviceID);
    void removeDevice(DeviceID deviceID);

    // TODO figure out what should be issuing commands... and clean up this spike
    String getRawCommandCode(DeviceID deviceID, TelevisionCommand command);

}
