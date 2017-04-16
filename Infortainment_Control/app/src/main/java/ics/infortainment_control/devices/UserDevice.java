package ics.infortainment_control.devices;

import ics.infortainment_control.commands.Command;
import ics.infortainment_control.devices.Device;

public class UserDevice<C extends Command> {

    private String name;
    private Device<C> device;
}
