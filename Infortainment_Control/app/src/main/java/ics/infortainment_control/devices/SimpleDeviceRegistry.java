package ics.infortainment_control.devices;

import java.util.HashMap;
import java.util.Map;

import ics.infortainment_control.commands.CodeProvider;
import ics.infortainment_control.commands.Command;
import ics.infortainment_control.commands.SimpleCodeProvider;

public class SimpleDeviceRegistry implements DeviceRegistry {

    // maps deviceID to Device object
    private Map<String, Device> userDevices;

    private CodeProvider codeProvider;

    public SimpleDeviceRegistry() {
        userDevices = new HashMap<>();
        codeProvider = new SimpleCodeProvider();
    }

    @Override
    public Device registerDevice(String deviceName) {
        if (userDevices.containsKey(deviceName)) {
            return userDevices.get(deviceName);
        }

        Device newDevice = createDevice(deviceName);

        userDevices.put(deviceName, newDevice);

        return newDevice;
    }

    @Override
    public Device removeDevice(String deviceName) {
        return userDevices.remove(deviceName);
    }

    @Override
    public boolean isInRegistry(String deviceName) {
        return userDevices.containsKey(deviceName);
    }

    // TODO there are messy interface considerations here: shouldn't that be willing to register it if it isn't in there?
    //      and why is that implementation *specific* to the SimpleDeviceRegistry? There is more similarity here that's being
    //      tossed aside yet...
    @Override
    public Device getDevice(String deviceName) {
        return userDevices.get(deviceName);
    }

    private Device createDevice(String deviceID) {
        Device device = new Device(deviceID);

        Map<Command, String> commands = codeProvider.getCodes(deviceID);

        device.setCommands(commands);

        return device;
    }
}
