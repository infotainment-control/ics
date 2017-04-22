package ics.infortainment_control.devices;

import java.util.Date;
import java.util.Map;

import ics.infortainment_control.commands.Command;

public class UserDevice implements AbstractDevice {

    private Device device;
    private String name;
    private Date   dateAdded;

    public UserDevice(String name, Device device) {
        this.device = device;
        this.name   = name;
        dateAdded   = new Date();
    }

    public String getName() {
        return name;
    }

    public Date getDateCreated() {
        return dateAdded;
    }

    @Override
    public boolean handleCommand(Command command) {
        return device.handleCommand(command);
    }

    @Override
    public boolean knowsCommand(Command command) {
        return device.knowsCommand(command);
    }

    @Override
    public String getID() {
        return device.getID();
    }

    @Override
    public void setCommands(Map<Command, String> commands) {
        device.setCommands(commands);
    }
}
