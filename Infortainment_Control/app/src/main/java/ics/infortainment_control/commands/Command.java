package ics.infortainment_control.commands;

/**
 *  Enumeration for dynamic instantiation of device command implementations.
 */
public enum Command {

    CHANNEL_DOWN ("channel down", "decrements the device channel tuned in to"         ),
    CHANNEL_UP   ("channel up",   "increments the device channel tuned in to"         ),
    DISPLAY      ("",             ""                                                  ),
    DOWN         ("down",         "selects an option below the current selection"     ),
    EIGHT        ("8",            "the digit 8"                                       ),
    EJECT        ("",             ""                                                  ),
    ENTER        ("enter",        "affirms a selected option"                         ),
    EXIT         ("exit",         "cancels a selection or returns to a previous menu" ),
    FASTFORWARD  ("",             ""                                                  ),
    FIVE         ("5",            "the digit 5"                                       ),
    FORWARD_STEP ("",             ""                                                  ),
    FOUR         ("4",            "the digit 4"                                       ),
    GUIDE        ("",             ""                                                  ),
    INFO         ("info",         "displays additional information"                   ),
    LEFT         ("left",         "selects an option left of the current selection"   ),
    MENU         ("menu",         "opens the device's settings menu"                  ),
    MODE         ("",             ""                                                  ),
    MUTE         ("mute",         "silences and unsilences the device"                ),
    NINE         ("9",            "the digit 9"                                       ),
    ONE          ("1",            "the digit 1"                                       ),
    PAUSE        ("",             ""                                                  ),
    PLAY         ("",             ""                                                  ),
    POWER        ("power",        "turns the device on and off"                       ),
    RECALL       ("",             ""                                                  ),
    RECORD       ("",             ""                                                  ),
    REVERSE_STEP ("",             ""                                                  ),
    REWIND       ("",             ""                                                  ),
    RIGHT        ("right",        "selects an option right of the current selection"  ),
    SEVEN        ("7",            "the digit 7"                                       ),
    SIX          ("6",            "the digit 6"                                       ),
    SLOW         ("",             ""                                                  ),
    SOURCE       ("source",       "changes the input source of the device"            ),
    STOP         ("",             ""                                                  ),
    SUBTITLE     ("",             ""                                                  ),
    THREE        ("3",            "the digit 3"                                       ),
    TITLE        ("",             ""                                                  ),
    TWO          ("2",            "the digit 2"                                       ),
    UP           ("up",           "selects an option above the current selection"     ),
    VOLUME_DOWN  ("volume down",  "lowers the device's level of volume"               ),
    VOLUME_UP    ("volume up",    "raises the device's level of volume"               ),
    ZERO         ("0",            "the digit 0"                                       );


    private final String simpleName;
    private final String description;

    Command(String simpleName, String description) {
        this.simpleName  = simpleName;
        this.description = description;
    }


    public String getSimpleName() {
        return simpleName;
    }

    public String getDescription() {
        return description;
    }
}
