package ics.infortainment_control.commands;

/**
 *  Enumeration for dynamic instantiation of device command implementations.
 *
 *  TODO consider items 32, 33 of effective java for more robust design
 */
public enum TelevisionCommand implements Command {
    POWER      ("power",       "turns device on and off"            ),
    VOLUME_UP  ("volume up",   "raises the device's level of volume"),
    VOLUME_DOWN("volume down", "lowers the device's level of volume");

    private final String simpleName;
    private final String description;

    TelevisionCommand(String simpleName, String description) {
        this.simpleName  = simpleName;
        this.description = description;
    }


    @Override
    public String getSimpleName() {
        return simpleName;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
