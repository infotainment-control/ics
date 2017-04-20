package ics.infortainment_control.devices;

public class SimpleDeviceRegistryProvider implements DeviceRegistryProvider {
    // TODO is this all good to go, then? Not really...
    private static final DeviceRegistry simpleDeviceRegistry = new SimpleDeviceRegistry();

    @Override
    public DeviceRegistry getDeviceRegistry() {

        return simpleDeviceRegistry;
    }
}
