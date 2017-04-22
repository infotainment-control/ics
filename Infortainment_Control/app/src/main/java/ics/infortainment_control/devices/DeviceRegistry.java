package ics.infortainment_control.devices;


public interface DeviceRegistry {

    /**
     * TODO this description is no longer valid
     * Creates a device, stores it in the registry, and makes the device active. TODO a bit much, eh?
     *
     * @param deviceName a manufacturer+model pair that uniquely identifies a device
     * @return the device created during the registration process
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
     * @return the device referenced by the deviceID, or null if it does not exist TODO invalid DeviceIDs?
     */
    AbstractDevice getDevice(String deviceName);

    /**
     * Pulls all registered devices into the registry.
     */
    void loadRegisteredDevices();
}
