package ics.infortainment_control.devices;

import java.util.Map;

import ics.infortainment_control.commands.Command;

public interface AbstractDevice {
    boolean handleCommand(Command command);

    boolean knowsCommand(Command command);

    String getID();

    void setCommands(Map<Command, String> commands);
}
