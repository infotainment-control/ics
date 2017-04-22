package ics.infortainment_control.devices;

import java.util.List;
import java.util.Set;

interface DeviceRegistry {

    // TODO this is rough stuff: Devices? shouldn't these be abstracted?
    /**
     * Uses the device to create a UserDevice and put that UserDevice, mapped to its name, into the registry
     *
     * @param deviceName
     * @return the UserDevice(????) TODO check if it ought to be created during the registration process
     */
    AbstractDevice registerDevice(String deviceName, Device device);

    /**
     * Removes a device from the registry
     * @param deviceName
     * @return the removed Device (for checking if it was the primary device of that type)
     */
    AbstractDevice removeDevice(String deviceName);

    // useful for when a DeviceManager wishes to create a device -- ensures avoidance of name collision
    boolean isInRegistry(String deviceName);

    /**
     * Retrieves a device from the registry (typically to reference it as the new active device for its DeviceType)
     * @param deviceName
     * @return the device referenced by the deviceID, or null if it does not exist
     */
    AbstractDevice getDevice(String deviceName);




    // TODO - concerning device retrieval
    //        the retrieved should be at times UserDevice, at times Device
    //        and at times grouped by DeviceType,
    //                     sorted  by Active > not Active,
    //                     sorted again by newer creation date > older creation date

    /**
     * Pulls all registered devices into the registry and offers them.
     *
     * This will be used by DeviceManagers who ought to delegate code provision for each device
     * with whatever CodeProvider implementation they deem fit.
     */
    // TODO note that this should be a List, sorted by 2 things: active > inactive
    Set<AbstractDevice> loadRegisteredDevices();

    List<UserDevice> getOrderedListOfUserDevices(DeviceType deviceType);
}
