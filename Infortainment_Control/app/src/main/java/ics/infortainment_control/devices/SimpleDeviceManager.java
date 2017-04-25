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

    private SimpleDeviceManager() {
        registry     = new SimpleDeviceRegistry();
        codeProvider = new SimpleCodeProvider();

        Set<AbstractDevice> devices = registry.loadRegisteredDevices();
        for(AbstractDevice device : devices) {
            Map<Command, String> commands = codeProvider.getCodes(device.getID());
            device.setCommands(commands);
        }
        // TODO any information on
    }

    public static DeviceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleDeviceManager();
        }
        return INSTANCE;
    }


    // TODO this represents the roster class, so that should get pulled out eventually
    private static EnumMap<DeviceType, AbstractDevice> activeDevices = new EnumMap<>(DeviceType.class);

    // static block that
    // TODO this calls into question all sorts of issues: ought Devices know what type they are all over?
    //      Or ought there be a really damn big, fundamental distinction between Device and UserDevice,
    //      where a user's assertions truly make a clarification...
    static {
        for (DeviceType deviceType :
             DeviceType.values() ) {
        }
    }

    @Override
    public AbstractDevice getActiveDevice(DeviceType deviceType) {
        return null;
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
