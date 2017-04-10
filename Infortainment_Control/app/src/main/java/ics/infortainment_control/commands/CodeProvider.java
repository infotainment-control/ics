package ics.infortainment_control.commands;

import java.util.Map;
import java.util.Set;

/**
 *  An interface that furnishes Devices with the codes for their commands.
 *
 *  An intermediary class will translate; this interface will be backed by
 *  two main classes:
 *      one for debugging and testing (a mock class) and
 *      one for querying the app's local database and passing along the results
 *
 *  TODO consider annotations & item 37 of effective java
 */
public interface CodeProvider {

    <C extends Command> String getCode(String deviceID, C command);

    Map<TelevisionCommand, String> getCodes(String deviceID);

    Set<String> getAllDeviceIDs();

    Set<String> getAllDeviceIDsForBrand(String brand);

    Map<String, String> getAllPowerCommandsForBrand(String brand);
    // TODO the above, getAllPowerCommandsForBrand(), will look something like...
    // for each deviceID in getAllDeviceIDs(brandID),
    //   map.put(deviceID, getCode(deviceID, TelevisionCommand.POWER)
    // return map
}
