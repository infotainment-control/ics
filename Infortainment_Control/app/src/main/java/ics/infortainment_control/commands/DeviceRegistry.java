package ics.infortainment_control.commands;


public interface DeviceRegistry {

    /**
     * Creates a device, stores it in the registry, and makes the device active. TODO a bit much, eh?
     *
     * @param deviceID a manufacturer+model pair that uniquely identifies a device
     * @return the device created during the registration process
     */
    Device registerDevice(String deviceID);

    /**
     * Removes a device from the registry
     * @param deviceID
     * @return
     */
    boolean removeDevice(String deviceID);

    boolean removeDevice(Device device);

    boolean isInRegistry(Device device);

    boolean isInRegistry(String deviceID);

    /**
     * Retrieves a device from the registry (typically to reference it as the new active device for its DeviceType)
     * @param deviceID
     * @return the device referenced by the deviceID, or null if it does not exist TODO invalid DeviceIDs?
     */
    Device getDevice(String deviceID);
}
