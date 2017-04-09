package ics.infortainment_control.commands;

public class UserDevice<C extends Command> {

    private String name;
    private Device<C> device;
}
