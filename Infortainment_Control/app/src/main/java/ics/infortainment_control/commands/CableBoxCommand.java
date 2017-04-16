package ics.infortainment_control.commands;

/**
 *  Enumeration for dynamic instantiation of CableBox device command implementations.
 */
public enum CableBoxCommand implements Command {

    POWER       ("power",        "turns the CB on and off"                          ),
    CHANNEL_UP  ("channel up",   "increments the CB channel tuned in to"            ),
    CHANNEL_DOWN("channel down", "decrements the CB channel tuned in to"            ),
    MENU        ("menu",         "opens the CB's settings menu"                     ),
    UP          ("up",           "selects an option above the current selection"    ),
    DOWN        ("down",         "selects an option below the current selection"    ),
    LEFT        ("left",         "selects an option left of the current selection"  ),
    RIGHT       ("right",        "selects an option right of the current selection" ),
    ENTER       ("enter",        "affirms a selected option"                        ),
    EXIT        ("exit",         "cancels a selection or returns to a previous menu"),
    INFO        ("info",         "displays additional information"                  ),
    ZERO        ("0",            "the digit 0"                                      ),
    ONE         ("1",            "the digit 1"                                      ),
    TWO         ("2",            "the digit 2"                                      ),
    THREE       ("3",            "the digit 3"                                      ),
    FOUR        ("4",            "the digit 4"                                      ),
    FIVE        ("5",            "the digit 5"                                      ),
    SIX         ("6",            "the digit 6"                                      ),
    SEVEN       ("7",            "the digit 7"                                      ),
    EIGHT       ("8",            "the digit 8"                                      ),
    NINE        ("9",            "the digit 9"                                      );

    private final String simpleName;
    private final String description;

    // TODO these are all the same, so perhaps there ought to be 1 abstract parent class/enum?
    CableBoxCommand(String simpleName, String description) {
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
