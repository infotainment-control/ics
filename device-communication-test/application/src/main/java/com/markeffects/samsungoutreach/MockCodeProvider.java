package com.markeffects.samsungoutreach;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class MockCodeProvider implements CodeProvider {

    private Map<DeviceID, Map<TelevisionCommand, String>> deviceCodesMap;

    public MockCodeProvider() {
        deviceCodesMap = new HashMap<>();

        Map<TelevisionCommand, String> samsungCodes  = new HashMap<>();
        samsungCodes.put(
                TelevisionCommand.POWER,
                "0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0040 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e"
        );

        Map<TelevisionCommand, String> insigniaCodes = new HashMap<>();
        insigniaCodes.put(
                TelevisionCommand.POWER,
                "0000 006C 0022 0002 015B 00AD 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0679 015B 0057 0016 0E6C"
        );

        deviceCodesMap.put(DeviceID.SAMSUNG,  samsungCodes );
        deviceCodesMap.put(DeviceID.INSIGNIA, insigniaCodes);
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
