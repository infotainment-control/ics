package ics.infortainment_control.commands;

public interface Command {

    String getSimpleName();

    String getDescription();

    // TODO design the logic here to sync this up.
    //void registerToButton();

    // TODO should this be generic? IE a method that returns the device type?

}
