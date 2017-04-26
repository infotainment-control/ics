package ics.infortainment_control.devices;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Map;

import ics.infortainment_control.commands.Command;

public class UserDevice implements AbstractDevice, Comparable<UserDevice> {

    public static final SimpleDateFormat USER_DEVICE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", Locale.US);

    // indicates subset of DeviceTypes allowed for registration TODO - consider proper location for such constraining
    public static final EnumSet<DeviceType> REGISTERABLE_DEVICES = EnumSet.complementOf(EnumSet.of(DeviceType.UNKNOWN));

    // TODO consider modifying to be the AbstractDevice within? I don't know if it's necessary, but it doesn't seem harmful
    private Device device;
    private String name;

    private Date dateAdded;

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


    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
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

    // compares by active status; if neither active, then compares by name
    @Override
    public int compareTo(@NonNull UserDevice o) {

        // TODO this is an aggressive constraint that isn't statically enforced by any code - consider the refactor:
        //      ought Devices be generics on the DeviceType they implement? WELL??? Yes, they should...
        if(this.type != o.type) {
            throw new RuntimeException("error -- attempting to compare different device types");
        }

        if(this.active) return 1;
        else
            if(o.active) return -1;
        else
            return this.getName().compareTo(o.getName());
    }
}
