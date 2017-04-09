package ics.infortainment_control.commands;

public class Device {

    private String id;

    public Device(String brand, String model) {
        id = createDeviceID(brand, model);
    }

    public Device(String id) {
        this.id = id;
    }

    public String getID() { return id; }

    private static final char SEPARATOR = ':';

    public static String createDeviceID(String brand, String model) {
        return String.format("%s%c%s", brand, SEPARATOR, model);
    }

    
}
