package ics.infortainment_control.devices;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// TODO ought to be a lazily instantiated singleton
public class SimpleDeviceRegistry implements DeviceRegistry {

    // associates a UserDevice's name to its Device interface
    // TODO how, if not at the Registry, will *anyone* know the UserDevice information needed to use the Devices?
    private static Map<String, AbstractDevice> userDevices;

    static {
        userDevices = new HashMap<>(3);

        String     LG_DVD_Name    = "my_LG_dvdplayer";
        String     LG_DVD_ID      = "635";
        DeviceType LG_DVD_Type    = DeviceType.DVD_PLAYER;
        boolean    LG_DVD_Active  = true;
        Date LG_DVD_Creation_Date = new Date();

        try {
            LG_DVD_Creation_Date = UserDevice.USER_DEVICE_DATE_FORMAT.parse("2017-04-22_15:21:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserDevice LG_DVD = new UserDevice(LG_DVD_Name, new Device(LG_DVD_ID));
        LG_DVD.setActive(LG_DVD_Active);
        LG_DVD.setType(LG_DVD_Type);
        LG_DVD.setDateAdded(LG_DVD_Creation_Date);

        String     Insignia_TV_Name    = "my_Insignia_TV";
        String     Insignia_TV_ID      = "2114";
        DeviceType Insignia_TV_Type    = DeviceType.TELEVISION;
        boolean    Insignia_TV_Active  = true;
        Date Insignia_TV_Creation_Date = new Date();

        try {
            Insignia_TV_Creation_Date= UserDevice.USER_DEVICE_DATE_FORMAT.parse("2017-04-22_15:21:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserDevice Insignia_TV = new UserDevice(Insignia_TV_Name, new Device(Insignia_TV_ID));
        Insignia_TV.setActive(Insignia_TV_Active);
        Insignia_TV.setType(Insignia_TV_Type);
        Insignia_TV.setDateAdded(Insignia_TV_Creation_Date);

        // TODO save Samsung_TV for a hard-coded triggering of the device registration process, eh?

        userDevices.put(LG_DVD_Name,      LG_DVD);
        userDevices.put(Insignia_TV_Name, Insignia_TV);
    }

    SimpleDeviceRegistry() {}

    @Override
    public AbstractDevice registerDevice(String deviceName, Device device) {
        // TODO this may be an error condition
        if (userDevices.containsKey(deviceName)) {
            return userDevices.get(deviceName);
        }

        AbstractDevice abstractDevice = new UserDevice(deviceName, device);

        userDevices.put(deviceName, abstractDevice);

        return abstractDevice;
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

    // TODO a bit disingenuous, this implementation...
    @Override
    public Set<AbstractDevice> loadRegisteredDevices() {
        return new HashSet<>(userDevices.values());
    }


    @Override
    public List<UserDevice> getOrderedListOfUserDevices(DeviceType deviceType) {
        List<UserDevice> userDeviceList = new ArrayList<>(userDevices.size());

        // TODO process the registry >:)
        for(Map.Entry<String, AbstractDevice> entry : userDevices.entrySet()) {
            // order them by DeviceType and active and date created
        }
        return null;
    }

    // TODO consider building into the interface and exposing via that
    // TODO also --- make it less of a hack, in that case!
    public AbstractDevice getActiveDevice(DeviceType deviceType) {
        switch(deviceType) {
            case DVD_PLAYER:
                return userDevices.get("my_LG_dvdplayer");
            case TELEVISION:
                return userDevices.get("my_Insignia_TV");
            default:
                return null;
        }
    }
}
