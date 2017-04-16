package ics.infortainment_control.commands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ics.infortainment_control.devices.Device;

/**
 *  Provides hard-coded command codes, suitable for development, debugging, and testing.
 */
public class SimpleCodeProvider implements CodeProvider {

    private static Map<String, Map<Command, String>> deviceCodesMap;

    // instantiation of all command codes
    static {
        deviceCodesMap = new HashMap<>();

        /**
         *  Samsung
         *  "all televisions"
         *  TV
         */
        String samsungID = Device.createDeviceID("Samsung", "TV_Functions");

        Map<Command, String> samsungTVCodes = new HashMap<>();

        samsungTVCodes.put( TelevisionCommand.POWER,        "0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0040 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e");
//        samsungTVCodes.put( TelevisionCommand.VOLUME_UP,    null);
//        samsungTVCodes.put( TelevisionCommand.VOLUME_DOWN,  null);
//        samsungTVCodes.put( TelevisionCommand.CHANNEL_UP,   null);
//        samsungTVCodes.put( TelevisionCommand.CHANNEL_DOWN, null);
//        samsungTVCodes.put( TelevisionCommand.SOURCE,       null);
//        samsungTVCodes.put( TelevisionCommand.MENU,         null);
//        samsungTVCodes.put( TelevisionCommand.UP,           null);
//        samsungTVCodes.put( TelevisionCommand.DOWN,         null);
//        samsungTVCodes.put( TelevisionCommand.LEFT,         null);
//        samsungTVCodes.put( TelevisionCommand.RIGHT,        null);
//        samsungTVCodes.put( TelevisionCommand.ENTER,        null);
//        samsungTVCodes.put( TelevisionCommand.EXIT,         null);
//        samsungTVCodes.put( TelevisionCommand.MUTE,         null);
//        samsungTVCodes.put( TelevisionCommand.INFO,         null);
//        samsungTVCodes.put( TelevisionCommand.ZERO,         null);
//        samsungTVCodes.put( TelevisionCommand.ONE,          null);
//        samsungTVCodes.put( TelevisionCommand.TWO,          null);
//        samsungTVCodes.put( TelevisionCommand.THREE,        null);
//        samsungTVCodes.put( TelevisionCommand.FOUR,         null);
//        samsungTVCodes.put( TelevisionCommand.FIVE,         null);
//        samsungTVCodes.put( TelevisionCommand.SIX,          null);
//        samsungTVCodes.put( TelevisionCommand.SEVEN,        null);
//        samsungTVCodes.put( TelevisionCommand.EIGHT,        null);
//        samsungTVCodes.put( TelevisionCommand.NINE,         null);

        deviceCodesMap.put(samsungID, samsungTVCodes);

        /**
         *  Insignia
         *  NS-32D312NA15
         *  TV
         */
        String insigniaID = Device.createDeviceID("Insignia", "NS-32D312NA15|CUSTOM_SEARCH");

        Map<Command, String> insigniaNS32D312NA15Codes = new HashMap<>();

        insigniaNS32D312NA15Codes.put( TelevisionCommand.POWER,        "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.VOLUME_UP,    "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C");
        insigniaNS32D312NA15Codes.put( TelevisionCommand.VOLUME_DOWN,  "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C");
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.CHANNEL_UP,   null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.CHANNEL_DOWN, null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.SOURCE,       null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.MENU,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.UP,           null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.DOWN,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.LEFT,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.RIGHT,        null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.ENTER,        null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.EXIT,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.MUTE,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.INFO,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.ZERO,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.ONE,          null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.TWO,          null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.THREE,        null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.FOUR,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.FIVE,         null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.SIX,          null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.SEVEN,        null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.EIGHT,        null);
//        insigniaNS32D312NA15Codes.put( TelevisionCommand.NINE,         null);

        deviceCodesMap.put(insigniaID, insigniaNS32D312NA15Codes);

        /**
         *  Motorola
         *  Arris CableBox
         *  CB
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
    }

    @Override
    public <C extends Command> String getCode(String deviceID, C command) {

        String commandCode = null;
        Map<C, String> allCommandsForDevice = getCodes(deviceID);

        if (allCommandsForDevice != null) {

            // TODO herein lines all the trouble. gotta marshal based on C's underlying type, I guess. But that seems screwy
            if (command instanceof TelevisionCommand) {
                commandCode = allCommandsForDevice.get(TelevisionCommand.POWER);
            } else if (command instanceof CableBoxCommand) {
                commandCode = allCommandsForDevice.get(CableBoxCommand.POWER);
            }
        }

        return commandCode;
    }

    // TODO this is where I've screwed up generics, yeppers
    @Override
    public <C extends Command> Map<C, String> getCodes(String deviceID) {
        // TODO or have I??
        return (Map<C, String>) deviceCodesMap.get(deviceID);
    }

    @Override
    public Set<String> getAllDeviceIDs() {
        return deviceCodesMap.keySet();
    }

    @Override
    public Set<String> getAllDeviceIDsForBrand(String brand) {

        Set<String> allDeviceIDs = deviceCodesMap.keySet();
        Set<String> brandDeviceIDs = new HashSet<>();

        for(String deviceID : allDeviceIDs) {
            if (brand.equals(Device.getBrandFromID(deviceID))) {
                brandDeviceIDs.add(deviceID);
            }
        }
        return brandDeviceIDs;
    }

    @Override
    public Map<String, String> getAllPowerCommandsForBrand(String brand) {
        return null;
    }
}

/*
television commands

        samsungTVCodes.put( TelevisionCommand.POWER,        null);
        samsungTVCodes.put( TelevisionCommand.VOLUME_UP,    null);
        samsungTVCodes.put( TelevisionCommand.VOLUME_DOWN,  null);
        samsungTVCodes.put( TelevisionCommand.CHANNEL_UP,   null);
        samsungTVCodes.put( TelevisionCommand.CHANNEL_DOWN, null);
        samsungTVCodes.put( TelevisionCommand.SOURCE,       null);
        samsungTVCodes.put( TelevisionCommand.MENU,         null);
        samsungTVCodes.put( TelevisionCommand.UP,           null);
        samsungTVCodes.put( TelevisionCommand.DOWN,         null);
        samsungTVCodes.put( TelevisionCommand.LEFT,         null);
        samsungTVCodes.put( TelevisionCommand.RIGHT,        null);
        samsungTVCodes.put( TelevisionCommand.ENTER,        null);
        samsungTVCodes.put( TelevisionCommand.EXIT,         null);
        samsungTVCodes.put( TelevisionCommand.MUTE,         null);
        samsungTVCodes.put( TelevisionCommand.INFO,         null);
        samsungTVCodes.put( TelevisionCommand.ZERO,         null);
        samsungTVCodes.put( TelevisionCommand.ONE,          null);
        samsungTVCodes.put( TelevisionCommand.TWO,          null);
        samsungTVCodes.put( TelevisionCommand.THREE,        null);
        samsungTVCodes.put( TelevisionCommand.FOUR,         null);
        samsungTVCodes.put( TelevisionCommand.FIVE,         null);
        samsungTVCodes.put( TelevisionCommand.SIX,          null);
        samsungTVCodes.put( TelevisionCommand.SEVEN,        null);
        samsungTVCodes.put( TelevisionCommand.EIGHT,        null);
        samsungTVCodes.put( TelevisionCommand.NINE,         null);


 */