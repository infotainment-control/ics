package ics.infortainment_control.devices;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ics.infortainment_control.commands.CodeProvider;
import ics.infortainment_control.commands.Command;
import ics.infortainment_control.commands.SimpleCodeProvider;

// TODO ought to be a lazily instantiated singleton
public class SimpleDeviceRegistry implements DeviceRegistry {

    // maps deviceID to Device object
    private Map<String, AbstractDevice> userDevices;

    private CodeProvider codeProvider;

    public SimpleDeviceRegistry() {
        userDevices = new HashMap<>();
        codeProvider = new SimpleCodeProvider();
    }

    @Override
    public AbstractDevice registerDevice(String deviceName, Device device) {
        if (userDevices.containsKey(deviceName)) {
            return userDevices.get(deviceName);
        }

        AbstractDevice newAbstractDevice = createDevice(deviceName);

        userDevices.put(deviceName, newAbstractDevice);

        return newAbstractDevice;
    }

    @Override
    public AbstractDevice removeDevice(String deviceName) {
        return userDevices.remove(deviceName);
    }

    @Override
    public boolean isInRegistry(String deviceName) {
        return userDevices.containsKey(deviceName);
    }

    @Override
    public AbstractDevice getDevice(String deviceName) {
        return userDevices.get(deviceName);
    }

    @Override
    public Set<AbstractDevice> loadRegisteredDevices() {
        // TODO load them! Statically, I mean! Like, the devices SimpleCodeProvider will accomodate
        return new HashSet<>(userDevices.values());
    }

    // TODO move to DeviceManager
    private AbstractDevice createDevice(String deviceID) {
        AbstractDevice abstractDevice = new Device(deviceID);

        Map<Command, String> commands = codeProvider.getCodes(deviceID);

        abstractDevice.setCommands(commands);

        return abstractDevice;
    }
}
