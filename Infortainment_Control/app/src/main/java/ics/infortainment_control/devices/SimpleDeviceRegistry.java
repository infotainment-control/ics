package ics.infortainment_control.devices;

import java.util.HashMap;
import java.util.Map;

import ics.infortainment_control.commands.CodeProvider;
import ics.infortainment_control.commands.Command;
import ics.infortainment_control.commands.SimpleCodeProvider;

public class SimpleDeviceRegistry implements DeviceRegistry {

    // maps deviceID to Device object
    private Map<String, Device> registry;

    private CodeProvider codeProvider;

    public SimpleDeviceRegistry() {
        registry = new HashMap<>();
        codeProvider = new SimpleCodeProvider();
    }

    @Override
    public Device registerDevice(String deviceID) {
        if (registry.containsKey(deviceID)) {
            return registry.get(deviceID);
        }

        Device newDevice = createDevice(deviceID);

        registry.put(deviceID, newDevice);

        return newDevice;
    }

    @Override
    public Device removeDevice(String deviceID) {
        return registry.remove(deviceID);
    }

    @Override
    public boolean isInRegistry(String deviceID) {
        return registry.containsKey(deviceID);
    }

    @Override
    public Device getDevice(String deviceID) {
        return registry.get(deviceID);
    }

    private Device createDevice(String deviceID) {
        Device device = new Device(deviceID);

        Map<Command, String> commands = codeProvider.getCodes(deviceID);

        // TODO type issue :)
        device.setCommands(commands);

        return device;
    }
}
