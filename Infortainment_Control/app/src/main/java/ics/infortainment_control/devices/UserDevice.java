package ics.infortainment_control.devices;

import java.util.Date;

public class UserDevice {
    private String name;
    private Date   dateAdded;

    public UserDevice(String name) {
        this.name = name;
        dateAdded = new Date();
    }

    public String getName() { return name; }

    public Date getDateCreated() { return dateAdded; }
}
