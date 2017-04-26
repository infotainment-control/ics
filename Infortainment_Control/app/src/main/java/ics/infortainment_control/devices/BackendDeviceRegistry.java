package ics.infortainment_control.devices;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.util.Pair;

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

import ics.infortainment_control.InfortainmentControl;
import ics.infortainment_control.devices.database.DatabaseHelper;
import ics.infortainment_control.user_interface.settings_fragment;

import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_CREATION_DATE_COL;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_ID_COL;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_IS_ACTIVE_COL;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_NAME_COL;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_TYPE_COL;

// TODO ought to be a lazily instantiated singleton
public class BackendDeviceRegistry implements DeviceRegistry {
    private static final String TAG = "[SD_REGISTRY]";

    private static void log(String message) { Log.d(TAG, message); }

    // associates a UserDevice's name to its Device interface
    // TODO how, if not at the Registry, will *anyone* know the UserDevice information needed to use the Devices?
    private static Map<String, AbstractDevice> userDevices;

    BackendDeviceRegistry() {}

    @Override
    public AbstractDevice registerDevice(String deviceName, Device device) {
        // TODO validate name far prior to attempting to register device...
        if (isInRegistry(deviceName)) {
            throw new RuntimeException(TAG + " ERROR - device already exists in registry");
        }

        AbstractDevice abstractDevice = new UserDevice(deviceName, device);

        userDevices.put(deviceName, abstractDevice);

        // TODO !!! when registration implemented, issue INSERT statement

        return abstractDevice;
    }

    @Override
    public AbstractDevice removeDevice(String deviceName) {
        // TODO !!! when de-registration implemented, issue DELETE statement
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

        userDevices = new HashMap<>();

        DatabaseHelper helper    = new DatabaseHelper(InfortainmentControl.getAppContext());
        SQLiteDatabase db        = helper.getReadableDatabase();

        // TODO of course this all should be retooled...
        Cursor cursor = db.rawQuery("SELECT * FROM user_devices;", new String[0]);

        cursor.moveToFirst();
        while (! cursor.isAfterLast()) {

            String name = cursor.getString(USER_DEVICE_NAME_COL);
            int id = cursor.getInt(USER_DEVICE_ID_COL);
            DeviceType type = getDeviceTypeFromString(cursor.getString(USER_DEVICE_TYPE_COL));
            boolean active = ( 1 == cursor.getInt(USER_DEVICE_IS_ACTIVE_COL) );

            Date creationDate = new Date();
            String creationDateString = "_NOT_RETRIEVED_FROM_CURSOR_";
            try {
                creationDateString = cursor.getString(USER_DEVICE_CREATION_DATE_COL);
                creationDate = UserDevice.USER_DEVICE_DATE_FORMAT.parse(creationDateString);
            } catch (ParseException e) {
                Log.e(TAG, "ERROR - user device's creation date malformed: " + creationDateString);
            }

            UserDevice device = new UserDevice(name, new Device(String.valueOf(id)));
            device.setActive(active);
            device.setType(type);
            device.setDateAdded(creationDate);

            userDevices.put(name, device);

            cursor.moveToNext();
        }

        cursor.close();

        settings_fragment.devices = userDevices.values();
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

    private DeviceType getDeviceTypeFromString(String type) {
        return DeviceType.valueOf(type);
    }
}
