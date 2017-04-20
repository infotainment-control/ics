package ics.infortainment_control.devices;

import java.util.HashMap;
import java.util.Map;

import ics.infortainment_control.commands.Command;
import ics.infortainment_control.commands.IRBlasterManager;

public class Device {

    private String id;
    private DeviceType type;

    private Map<Command, String> commands;

    private static IRBlasterManager irBlasterManager = null;

    // TODO there really ought to be a device factory to control...
    //      - instantiation of the static irBlasterManager
    //      - uniqueness on device ID (and other such control over the ID, e.g. its composition)
    //      - (therefore, pooling of instantiated devices)
    //      - population of devices' commands map TODO currently will reside in registry

    public Device(String brand, String model) {
        id = createDeviceID(brand, model);

        commands = new HashMap<>();
//        commands = CommandProvider.get(id).............
    }

    public Device(String id) {
        this.id = id;

        // TODO wouldn't be necessary if the InfortainmentControl main application published
        // the instantiation of the IRBlasterManager singleton to all interested parties. Static blocks are tough to coordinate.
        if (irBlasterManager == null) {
            irBlasterManager = IRBlasterManager.getInstance();
        }

        commands = new HashMap<>();
    }

    public boolean handleCommand(Command command) {
        if (! knowsCommand(command)) {
            System.err.printf("command '%s' not found for device %s%n", command, id);
            // TODO check your aggression? Maybe? This is like, shoot yourself in the foot for the demo
            //        (disabling it for now... make this return void and re-enable it post-school)
            //throw new RuntimeException(String.format("command '%s' not found for device %s%n", command, id));
        }

        String prontoHex = commands.get(command);
        irBlasterManager.issueCommand(prontoHex);
        return true;

    }

    public boolean knowsCommand(Command command) {
        return commands.containsKey(command);
    }

    public String getID() {
        return id;
    }

    public void setCommands(Map<Command, String> commands) {
        this.commands = commands;
    }

    // These several methods would be the domain of a factory
    private static final String SEPARATOR = ":";

    public static String createDeviceID(String brand, String model) {
        return String.format("%s%s%s", brand, SEPARATOR, model);
    }

    public static String getBrandFromID(String id) {
        return id.split(SEPARATOR)[0];
    }

    public static String getModelFromID(String id) {
        return id.split(SEPARATOR)[1];
    }
}
