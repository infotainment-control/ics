package ics.infortainment_control.devices;

import java.util.EnumMap;
import java.util.List;

import ics.infortainment_control.commands.CodeProvider;
import ics.infortainment_control.commands.SimpleCodeProvider;

/**
 *  A class to control use of Devices.
 */
public class SimpleDeviceManager implements DeviceManager {

    private static SimpleDeviceManager INSTANCE;

    private SimpleDeviceManager() {}

    public static DeviceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleDeviceManager();
        }
        return INSTANCE;
    }

    private static final DeviceRegistry registry     = new SimpleDeviceRegistry();
    private static final CodeProvider   codeProvider = new SimpleCodeProvider();


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
    public void createDevice(String deviceName, DeviceType deviceType) {

    }

    @Override
    public List<UserDevice> getRegisteredDevices(DeviceType deviceType) {
        return null;
    }

    @Override
    public void loadDeviceRegistry() {
        registry.loadRegisteredDevices();

    }


}
