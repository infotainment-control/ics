package ics.infortainment_control.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ics.infortainment_control.devices.Device;

/**
 *  Provides hard-coded command codes, suitable for development, debugging, and testing.
 */
public class MockCodeProvider implements CodeProvider {

    //private Map<DeviceID, Map<TelevisionCommand, String>> deviceCodesMap;

    private static Map<String, Map<Command, String>> deviceCodesMap;

    static {
        deviceCodesMap = new HashMap<>();

        /**
         *  Samsung
         */
        String samsungID = Device.createDeviceID("Samsung", "TV_Functions");

        Map<Command, String> samsungTVCodes = new HashMap<>();

        samsungTVCodes.put( TelevisionCommand.POWER,        "0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0040 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e");
        samsungTVCodes.put( TelevisionCommand.VOLUME_UP,    "");
        samsungTVCodes.put( TelevisionCommand.VOLUME_DOWN,  "");
        samsungTVCodes.put( TelevisionCommand.CHANNEL_UP,   "");
        samsungTVCodes.put( TelevisionCommand.CHANNEL_DOWN, "");
        samsungTVCodes.put( TelevisionCommand.SOURCE,       "");
        samsungTVCodes.put( TelevisionCommand.MENU,         "");
        samsungTVCodes.put( TelevisionCommand.ENTER,        "");
        samsungTVCodes.put( TelevisionCommand.EXIT,         "");
        samsungTVCodes.put( TelevisionCommand.MUTE,         "");
        samsungTVCodes.put( TelevisionCommand.INFO,         "");
        samsungTVCodes.put( TelevisionCommand.ZERO,         "");
        samsungTVCodes.put( TelevisionCommand.ONE,          "");
        samsungTVCodes.put( TelevisionCommand.TWO,          "");
        samsungTVCodes.put( TelevisionCommand.THREE,        "");
        samsungTVCodes.put( TelevisionCommand.FOUR,         "");
        samsungTVCodes.put( TelevisionCommand.FIVE,         "");
        samsungTVCodes.put( TelevisionCommand.SIX,          "");
        samsungTVCodes.put( TelevisionCommand.SEVEN,        "");
        samsungTVCodes.put( TelevisionCommand.EIGHT,        "");
        samsungTVCodes.put( TelevisionCommand.NINE,         "");

        deviceCodesMap.put(samsungID, samsungTVCodes);

        /**
         *  Insignia
         */
        String insigniaID = Device.createDeviceID("Insignia", "NS-32D312NA15|CUSTOM_SEARCH");

        Map<TelevisionCommand, String> insigniaNS32D312NA15Codes = new HashMap<>();

        insigniaNS32D312NA15Codes.put( TelevisionCommand.POWER,        "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.VOLUME_UP,    "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.VOLUME_DOWN,  "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.CHANNEL_UP,   "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.CHANNEL_DOWN, "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.SOURCE,       "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.MENU,         "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.ENTER,        "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.EXIT,         "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.MUTE,         "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.INFO,         "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.ZERO,         "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.ONE,          "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.TWO,          "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.THREE,        "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.FOUR,         "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.FIVE,         "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.SIX,          "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.SEVEN,        "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.EIGHT,        "");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.NINE,         "");

        /**
         *  Motorola
         *  Arris
         */
        Map<TelevisionCommand, String> motorolaCodes = new HashMap<>();
        motorolaCodes.put(
                TelevisionCommand.POWER,
                "0000 006c 0012 0002 0157 00ac 0013 0056 0013 00ac 0013 0056 0013 00ac 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 00ac 0013 00ac 0013 0056 0013 04f8 0157 0056 0013 0d31"
        );

        /**
         *  LG
         */
        Map<TelevisionCommand, String> lgCodes = new HashMap<>();
        lgCodes.put(
                TelevisionCommand.POWER,
                "0000 006d 0026 0000 0155 00aa 0016 0015 0016 0015 0016 0040 0016 0015 0016 0015 0016 0015 0016 0014 0016 0015 0016 0040 0016 0040 0016 0015 0016 0040 0016 0040 0016 0040 0016 0040 0016 0040 0016 0015 0016 0015 0016 0040 0016 0015 0016 0015 0016 0015 0016 0040 0016 0040 0016 0040 0016 0040 0016 0015 0016 0040 0016 0040 0016 0040 0016 0014 0016 0015 0016 060b 0155 0055 0016 0e58 0155 0055 0016 00aa"
        );

//        deviceCodesMap.put(DeviceID.INSIGNIA, insigniaCodes);
//        deviceCodesMap.put(DeviceID.MOTOROLA, motorolaCodes);
//        deviceCodesMap.put(DeviceID.LG,       lgCodes      );
    }

    @Override
    public <C extends Command> String getCode(String deviceID, C command) {
        return null;
    }

    @Override
    public Map<TelevisionCommand, String> getCodes(String deviceID) {
        return null;
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
}

/*
copy dis

        samsungTVCodes.put( TelevisionCommand.POWER,        "");
        samsungTVCodes.put( TelevisionCommand.VOLUME_UP,    "");
        samsungTVCodes.put( TelevisionCommand.VOLUME_DOWN,  "");
        samsungTVCodes.put( TelevisionCommand.CHANNEL_UP,   "");
        samsungTVCodes.put( TelevisionCommand.CHANNEL_DOWN, "");
        samsungTVCodes.put( TelevisionCommand.SOURCE,       "");
        samsungTVCodes.put( TelevisionCommand.MENU,         "");
        samsungTVCodes.put( TelevisionCommand.ENTER,        "");
        samsungTVCodes.put( TelevisionCommand.EXIT,         "");
        samsungTVCodes.put( TelevisionCommand.MUTE,         "");
        samsungTVCodes.put( TelevisionCommand.INFO,         "");
        samsungTVCodes.put( TelevisionCommand.ZERO,         "");
        samsungTVCodes.put( TelevisionCommand.ONE,          "");
        samsungTVCodes.put( TelevisionCommand.TWO,          "");
        samsungTVCodes.put( TelevisionCommand.THREE,        "");
        samsungTVCodes.put( TelevisionCommand.FOUR,         "");
        samsungTVCodes.put( TelevisionCommand.FIVE,         "");
        samsungTVCodes.put( TelevisionCommand.SIX,          "");
        samsungTVCodes.put( TelevisionCommand.SEVEN,        "");
        samsungTVCodes.put( TelevisionCommand.EIGHT,        "");
        samsungTVCodes.put( TelevisionCommand.NINE,         "");


 */