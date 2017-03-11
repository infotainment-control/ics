package com.markeffects.core;

import java.util.Map;

/**
 *  An interface that furnishes Devices with the codes for their commands.
 *
 *  An intermediary class will translate; this interface will be backed by
 *  two main classes:
 *      one for debugging and testing (a mock class) and
 *      one for querying the app's local database and passing along the results
 *
 *  TODO consider annotations & item 37 of effective java
 *  TODO comment, brochacho
 */
interface CodeProvider {
    //String getCode(int deviceID, @Command String command); TODO this be that annotated approach...

    // explain yourself
    String getCode(DeviceID deviceID, TelevisionCommand command);

    // and again, explain
    Map<TelevisionCommand, String> getCodes(DeviceID deviceID);
}
