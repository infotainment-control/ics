package com.markeffects.core;

import java.util.HashMap;
import java.util.Map;

/**
 *  Provides hard-coded command codes, suitable for development, debugging, and testing.
 */
class MockCodeProvider implements CodeProvider {

    private Map<DeviceID, Map<TelevisionCommand, String>> deviceCodesMap;

    MockCodeProvider() {
        deviceCodesMap = new HashMap<>();

        // SAMSUNG
        Map<TelevisionCommand, String> samsungCodes  = new HashMap<>();
        samsungCodes.put(
                TelevisionCommand.POWER,
                "0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0040 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e"
        );

        // INSIGNIA
        Map<TelevisionCommand, String> insigniaCodes = new HashMap<>();
        insigniaCodes.put(
                TelevisionCommand.POWER,
                "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C"
        );
        insigniaCodes.put(
                TelevisionCommand.VOLUME_DOWN,
                "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C"
        );
        insigniaCodes.put(
                TelevisionCommand.VOLUME_UP,
                "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C"
        );

        // MOTOROLA (also ARRIS)
        Map<TelevisionCommand, String> motorolaCodes = new HashMap<>();
        motorolaCodes.put(
                TelevisionCommand.POWER,
                "0000 006c 0012 0002 0157 00ac 0013 0056 0013 00ac 0013 0056 0013 00ac 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 0056 0013 00ac 0013 00ac 0013 0056 0013 04f8 0157 0056 0013 0d31"
        );

        deviceCodesMap.put(DeviceID.SAMSUNG,  samsungCodes );
        deviceCodesMap.put(DeviceID.INSIGNIA, insigniaCodes);
        deviceCodesMap.put(DeviceID.MOTOROLA, motorolaCodes);
    }

    @Override
    public String getCode(DeviceID deviceID, TelevisionCommand command) {
        return deviceCodesMap.get(deviceID).get(command);
    }

    @Override
    public Map<TelevisionCommand, String> getCodes(DeviceID deviceID) {
        return deviceCodesMap.get(deviceID);
    }
}
