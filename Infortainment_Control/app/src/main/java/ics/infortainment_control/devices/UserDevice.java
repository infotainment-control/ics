package ics.infortainment_control.devices;

import java.util.Date;
import java.util.Map;

import ics.infortainment_control.commands.Command;

public class UserDevice implements AbstractDevice {

    // TODO consider modifying to be the AbstractDevice within? I don't know if it's necessary, but it doesn't seem harmful
    private Device  device;
    private String  name;
    private Date    dateAdded;

    // TODO fields that are a bit more challenging to deal with, so assess and integrate
    private boolean    active;
    private DeviceType type;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
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
