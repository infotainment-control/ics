package ics.infortainment_control.commands;

/**
 *  A class to control use of Devices.
 */
public class MockDeviceManager implements DeviceManager {

    private CodeProvider mockCodeProvider = new MockCodeProvider();

    private DeviceID currentDevice;

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
    public DeviceID getActiveDevice() {
        return currentDevice;
    }

    @Override
    public DeviceID setActiveDevice(DeviceID deviceID) {
        DeviceID previousDevice = currentDevice;
        currentDevice = deviceID;
        return previousDevice;
    }

    @Override
    public String getRawCommandCode(DeviceID deviceID, TelevisionCommand command) {
        return mockCodeProvider.getCode(deviceID, command);
    }
}
