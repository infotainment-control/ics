package ics.infortainment_control.devices.database;

// TODO This is very much an anti-pattern
public class NamedDatabaseConstants {

    // table: user_devices
    public static int USER_DEVICE_NAME_COL          = 1;
    public static int USER_DEVICE_ID_COL            = 2;
    public static int USER_DEVICE_CREATION_DATE_COL = 3;
    public static int USER_DEVICE_TYPE_COL          = 4;
    public static int USER_DEVICE_IS_ACTIVE_COL     = 5;

    // table: commands_map
    public static int COMMANDS_MAP_STANDARD_NAME_COL = 2;

    // join:
    public static int DEVICE_STANDARD_COMMANDS_NAME = 1;
    public static int DEVICE_STANDARD_COMMANDS_CODE = 2;
}
