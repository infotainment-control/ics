package ics.infortainment_control.commands;

import java.util.HashMap;
import java.util.Map;

public class Device <C extends Command> {

    private String id;

    private Map<C, String> commands;


    public Device(String brand, String model) {
        id = createDeviceID(brand, model);

        commands = new HashMap<>();
//        commands = CommandProvider.get(id).............
    }

    public Device(String id) {
        this.id = id;
    }

    public String getID() { return id; }

    private static final char SEPARATOR = ':';

    public static String createDeviceID(String brand, String model) {
        return String.format("%s%c%s", brand, SEPARATOR, model);
    }


    boolean handleCommand(C command) {
        if (commands.containsKey(command)) {
            String prontoHex = commands.get(command);
            return true;// TODO pass the prontoHex to the singleton IRBlasterManager...
        } else {
            System.err.printf("command '%s' not found for device %s%n", command, id);
            return false;
        }

    }
}
