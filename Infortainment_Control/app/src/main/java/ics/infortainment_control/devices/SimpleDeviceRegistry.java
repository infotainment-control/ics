package ics.infortainment_control.devices;

import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ics.infortainment_control.user_interface.settings_fragment;

// TODO ought to be a lazily instantiated singleton
public class SimpleDeviceRegistry implements DeviceRegistry {
    private static final String TAG = "[SD_REGISTRY]";

    private static void log(String message) { Log.d(TAG, message); }

    // associates a UserDevice's name to its Device interface
    // TODO how, if not at the Registry, will *anyone* know the UserDevice information needed to use the Devices?
    private static Map<String, AbstractDevice> userDevices;

    private static EnumMap<DeviceType, List<UserDevice>> registry;

    // TODO perhaps move to the loadRegisteredDevices() method to legitimize that impl & such
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
        boolean    Insignia_TV_Active  = false;
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

        String     Samsung_TV_Name    = "my_Samsung_TV";
        String     Samsung_TV_ID      = "1557";
        DeviceType Samsung_TV_Type    = DeviceType.TELEVISION;
        boolean    Samsung_TV_Active  = true;
        Date Samsung_TV_Creation_Date = new Date();

        try {
            Samsung_TV_Creation_Date= UserDevice.USER_DEVICE_DATE_FORMAT.parse("2017-04-25_05:21:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        UserDevice Samsung_TV = new UserDevice(Samsung_TV_Name, new Device(Samsung_TV_ID));
        Samsung_TV.setActive(Samsung_TV_Active);
        Samsung_TV.setType(Samsung_TV_Type);
        Samsung_TV.setDateAdded(Samsung_TV_Creation_Date);

        userDevices.put(LG_DVD_Name,      LG_DVD);
        userDevices.put(Insignia_TV_Name, Insignia_TV);
        userDevices.put(Samsung_TV_Name,  Samsung_TV);

        /**
         *   below is the work-in-progress for refactoring the class / serving as a guide for refactoring the interface
         */

        registry = new EnumMap<>(DeviceType.class);
        List<UserDevice> dvdPlayers = new ArrayList<>(1);
        dvdPlayers.add(LG_DVD);

        List<UserDevice> televisions = new ArrayList<>(2);
        televisions.add(Insignia_TV);
        televisions.add(Samsung_TV);

        Collections.sort(televisions);

        registry.put(DeviceType.DVD_PLAYER, dvdPlayers);
        registry.put(DeviceType.TELEVISION, televisions);

        // assign empty Lists for the remainder of DeviceTypes
        for( DeviceType type : UserDevice.REGISTERABLE_DEVICES ) {
            if( ! registry.containsKey(type)) {
                registry.put(type, new ArrayList<UserDevice>());
            }
        }
    }

    SimpleDeviceRegistry() {}

    @Override
    public AbstractDevice registerDevice(String deviceName, Device device) {
        // TODO validate name far prior to attempting to register device...
        if (isInRegistry(deviceName)) {
            throw new RuntimeException(TAG + " ERROR - device already exists in registry");
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
        //settings_fragment.devices = userDevices.values();
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
    AbstractDevice getActiveDevice(DeviceType deviceType) {
        for(AbstractDevice device : userDevices.values()) {
            if(((UserDevice) device).getType() == deviceType
                    && ((UserDevice) device).isActive()) {
                return device;
            }
        }
        return null;
    }
}
