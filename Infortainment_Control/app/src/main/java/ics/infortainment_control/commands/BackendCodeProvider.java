package ics.infortainment_control.commands;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ics.infortainment_control.InfortainmentControl;
import ics.infortainment_control.devices.Device;
import ics.infortainment_control.devices.DeviceType;
import ics.infortainment_control.devices.UserDevice;
import ics.infortainment_control.devices.database.DatabaseHelper;
import ics.infortainment_control.user_interface.settings_fragment;

import static ics.infortainment_control.devices.database.NamedDatabaseConstants.DEVICE_STANDARD_COMMANDS_CODE;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.DEVICE_STANDARD_COMMANDS_NAME;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_CREATION_DATE_COL;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_ID_COL;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_IS_ACTIVE_COL;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_NAME_COL;
import static ics.infortainment_control.devices.database.NamedDatabaseConstants.USER_DEVICE_TYPE_COL;

/**
 *  Provides static command codes, suitable for development, debugging, and testing.
 */
// TODO ought to be a lazily instantiated singleton
public class BackendCodeProvider implements CodeProvider {

    // TODO this will need an extra level to represent brand queries


    @Override
    public String getCode(String deviceID, Command command) {

        Map<Command, String> allCommandsForDevice = getStandardCommandCodesOfDevice(deviceID);

        return allCommandsForDevice.get(command);
    }

    @Override
    public Map<Command, String> getStandardCommandCodesOfDevice(String deviceID) {
        Map<Command, String> commandCodes = new HashMap<>();

        DatabaseHelper helper = new DatabaseHelper(InfortainmentControl.getAppContext());
        SQLiteDatabase db     = helper.getReadableDatabase();

        // TODO pure nasty hackery sorry - if I had the slightest time I'd at least use the query builder methods of SQLiteDatabase...
        String select = "SELECT raw_device_commands.device_id, commands_map.standard_name, raw_device_commands.pronto_hex_code";
        String from   = "FROM raw_device_commands JOIN commands_map";
        String on     = "ON raw_device_commands.device_id = commands_map.device_id AND raw_device_commands.unstandardized_name = commands_map.unstandardized_name";
        String where  = "WHERE raw_device_commands.device_id = ?";

        String query  = String.format("%s %s %s %s;", select, from, on, where);

        String[] queryArg = {deviceID};

        Cursor cursor = db.rawQuery(query, queryArg);

        cursor.moveToFirst();
        while (! cursor.isAfterLast()) {
            Command command = getCommandFromString(cursor.getString(DEVICE_STANDARD_COMMANDS_NAME));
            String  code    = cursor.getString(DEVICE_STANDARD_COMMANDS_CODE);

            commandCodes.put(command, code);

            cursor.moveToNext();
        }

        cursor.close();

        return commandCodes;
    }

    @Override
    public Set<String> getAllDeviceIDs() {
        return null;
    }

    @Override
    public Set<String> getAllDeviceIDsForBrand(String brand) {
        return null;
    }

    @Override
    public Map<String, String> getAllPowerCommandsForBrand(String brand) {
        return null;
    }

    private Command getCommandFromString(String command) {
        return Command.valueOf(command);
    }
}
