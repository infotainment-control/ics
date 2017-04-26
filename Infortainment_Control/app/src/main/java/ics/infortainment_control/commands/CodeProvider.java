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

    String getCode(String deviceID, Command command);

    Map<Command, String> getStandardCommandCodesOfDevice(String deviceID);

    // TODO what exactly is this supposed to do? .......
    Set<String> getAllDeviceIDs();

    Set<String> getAllDeviceIDsForBrand(String brand);

    /**
     * Allows for setup of an unknown device of a known brand. The power command for each device manufactured
     * by the brand will be offered to the user, and if the user discovers it works, it will be a candidate
     * deviceID (a possibly accurate mapping of their physical device to an entry in the device database)
     *
     * Further interfacing with the deviceID's related commands will test the mapping's accuracy. Some brands
     * share power codes between devices, but do not share all other commands' codes.
     *
     * @param brand The company responsible for manufacturing the device
     * @return a map of deviceID -> Power type of command TODO note the difficulty in type hierarchy here...
     */
    Map<String, String> getAllPowerCommandsForBrand(String brand);
}
