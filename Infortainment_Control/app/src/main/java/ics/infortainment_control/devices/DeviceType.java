package ics.infortainment_control.devices;

import java.util.EnumSet;

import ics.infortainment_control.commands.Command;

/**
 *  Enum that encapsulates the set of Commands that Devices of a given DeviceType ought to provide.
 *
 *  This is used by CodeProvider implementations to understand what a Device should be provisioned with.
 *  E.g. a database-based CodeProvider asked to provide codes for a TELEVISION Device
 *  would call TELEVISION.getCommandSet() in order to seek for the standardized Command terms
 *  in the database (using an argument-provided Device ID to see what's *actually* in the database)
 *
 */
public enum DeviceType {
    TELEVISION,
    CABLE_BOX,
    DVD_PLAYER,
    // note: UNKNOWN devices are special cases. they are only a state a Device maps to during
    //       the user's device setup process while they try to identify the device they own
    UNKNOWN;

    private EnumSet<Command> commandSet;

    public EnumSet<Command> getCommandSet() {
        return commandSet;
    }

    static {
        TELEVISION.commandSet = EnumSet.of(
                Command.CHANNEL_DOWN,
                Command.CHANNEL_UP,
                Command.DISPLAY,
                Command.DOWN,
                Command.EIGHT,
                Command.ENTER,
                Command.EXIT,
                Command.FIVE,
                Command.FOUR,
                Command.GUIDE,
                Command.INFO,
                Command.LEFT,
                Command.MENU,
                Command.MUTE,
                Command.NINE,
                Command.ONE,
                Command.POWER,
                Command.RIGHT,
                Command.SEVEN,
                Command.SIX,
                Command.SOURCE,
                Command.THREE,
                Command.TWO,
                Command.UP,
                Command.VOLUME_DOWN,
                Command.VOLUME_UP,
                Command.ZERO
        );

        CABLE_BOX.commandSet = EnumSet.of(
                Command.CHANNEL_DOWN,
                Command.CHANNEL_UP,
                Command.DISPLAY,
                Command.DOWN,
                Command.EIGHT,
                Command.ENTER,
                Command.EXIT,
                Command.FASTFORWARD,
                Command.FIVE,
                Command.FORWARD_STEP,
                Command.FOUR,
                Command.GUIDE,
                Command.INFO,
                Command.LEFT,
                Command.MENU,
                Command.MODE,
                Command.MUTE,
                Command.NINE,
                Command.ONE,
                Command.PAUSE,
                Command.PLAY,
                Command.POWER,
                Command.RECALL,
                Command.RECORD,
                Command.REVERSE_STEP,
                Command.REWIND,
                Command.RIGHT,
                Command.SEVEN,
                Command.SIX,
                Command.SLOW,
                Command.SOURCE,
                Command.STOP,
                Command.SUBTITLE,
                Command.THREE,
                Command.TITLE,
                Command.TWO,
                Command.UP,
                Command.VOLUME_DOWN,
                Command.VOLUME_UP,
                Command.ZERO
        );

        DVD_PLAYER.commandSet = EnumSet.of(
                Command.AUDIO,
                Command.DISPLAY,
                Command.DOWN,
                Command.EIGHT,
                Command.EJECT,
                Command.ENTER,
                Command.EXIT,
                Command.FASTFORWARD,
                Command.FIVE,
                Command.FORWARD_STEP,
                Command.FOUR,
                Command.INFO,
                Command.LEFT,
                Command.MENU,
                Command.NINE,
                Command.ONE,
                Command.PAUSE,
                Command.PLAY,
                Command.POWER,
                Command.RECORD,
                Command.REVERSE_STEP,
                Command.REWIND,
                Command.RIGHT,
                Command.SEVEN,
                Command.SIX,
                Command.SLOW,
                Command.STOP,
                Command.SUBTITLE,
                Command.THREE,
                Command.TITLE,
                Command.TWO,
                Command.UP,
                Command.VOLUME_DOWN,
                Command.VOLUME_UP,
                Command.ZERO
        );

        UNKNOWN.commandSet = EnumSet.of(
                Command.POWER
        );
    }
}
