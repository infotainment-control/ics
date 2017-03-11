package com.markeffects.samsungoutreach;

/**
 *
 */

public class MockDeviceManager implements DeviceManager {

    private CodeProvider mockCodeProvider = new MockCodeProvider();

    // TODO probably doesn't need to be part of the interface, eh?
    @Override
    public CodeProvider getCodeProvider() {
        return mockCodeProvider;
    }

    @Override
    public void registerDevice(DeviceID deviceID) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void removeDevice(DeviceID deviceID) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public String getRawCommandCode(DeviceID deviceID, TelevisionCommand command) {
        return mockCodeProvider.getCode(deviceID, command);
    }
}
