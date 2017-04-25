package ics.infortainment_control.devices;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ics.infortainment_control.commands.CodeProvider;
import ics.infortainment_control.commands.Command;
import ics.infortainment_control.commands.SimpleCodeProvider;

/**
 *  A class to control use of Devices.
 */
public class SimpleDeviceManager implements DeviceManager {

    private static DeviceRegistry registry;
    private static CodeProvider   codeProvider;

    private static SimpleDeviceManager INSTANCE;

    private static EnumMap<DeviceType, AbstractDevice> activeDevices;

    private SimpleDeviceManager() {
        registry      = new SimpleDeviceRegistry();
        codeProvider  = new SimpleCodeProvider();
        activeDevices = new EnumMap<>(DeviceType.class);

        // fetch all devices from the registry
        Set<AbstractDevice> devices = registry.loadRegisteredDevices();

        // for each device, instantiate its Command codes using the codeProvider
        for(AbstractDevice device : devices) {
            Map<Command, String> commands = codeProvider.getCodes(device.getID());
            device.setCommands(commands);
        }

        DeviceType dvd = DeviceType.DVD_PLAYER;
        DeviceType tv  = DeviceType.TELEVISION;

        // surely this is awful, and you know it
        activeDevices.put(dvd, null);
        activeDevices.put(tv,  null);

        for(DeviceType typeOfDevice : activeDevices.keySet()) {
            // TODO see the todo of the SimpleDeviceRegistry for thoughts about this responsibility
            AbstractDevice activeDevice = ((SimpleDeviceRegistry) registry).getActiveDevice(typeOfDevice);
            activeDevices.put(typeOfDevice, activeDevice);
        }
    }

    public static DeviceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleDeviceManager();
        }
        return INSTANCE;
    }

    @Override
    public AbstractDevice getActiveDevice(DeviceType deviceType) {
        return activeDevices.get(deviceType);
    }

    @Override
    public void setActiveDevice(AbstractDevice abstractDevice) {

    }


    @Override
    public void createDevice(String deviceName, String deviceID, DeviceType deviceType) {
        // TODO does the below commmented-out abstraction make sense? Or is this a jumbled, awful mess? :)
        //AbstractDevice abstractDevice = new Device(deviceID);
        Device device = new Device(deviceID);

        Map<Command, String> deviceCodes = codeProvider.getCodes(deviceID);

        device.setCommands(deviceCodes);

        registry.registerDevice(deviceName, device);
    }

    @Override
    public List<UserDevice> getRegisteredDevices(DeviceType deviceType) {
        return null;
    }

    // TODO this is rough stuff: these are devices! They should be abstracted......
    @Override
    public void loadDeviceRegistry() {
        Set<AbstractDevice> allDevices = registry.loadRegisteredDevices();

        // with all devices created, the next step is provisioning them with command codes
        for (AbstractDevice device : allDevices) {
            String id = device.getID();

            Map<Command, String> deviceCodes = codeProvider.getCodes(id);

            device.setCommands(deviceCodes);
        }
    }


}
