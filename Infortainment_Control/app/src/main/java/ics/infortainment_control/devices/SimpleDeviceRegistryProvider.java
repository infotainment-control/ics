package ics.infortainment_control.devices;

public class SimpleDeviceRegistryProvider implements DeviceRegistryProvider {
    private static final DeviceRegistry simpleDeviceRegistry = new SimpleDeviceRegistry();

    @Override
    public DeviceRegistry getDeviceRegistry() {
        return simpleDeviceRegistry;
    }
}
