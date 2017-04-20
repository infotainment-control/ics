package ics.infortainment_control.devices.Data.Model;

public class TimeZone {

    public static final String TAG = TimeZone.class.getSimpleName();
    public static final String TABLE = "TimeZone";
    // Labels Table Columns names
    public static final String KEY_TimeZoneId = "TimeZoneId";
    public static final String KEY_TimeZone = "TimeZone";
    public static final String KEY_AirTime = "AirTime";
    public static final String KEY_TimeStamp = "TimeStamp";

    private String timeZoneId;
    private String timeZone;
    private String airTime;
    private String timeStamp;

    public String getTimeZoneId() {return timeZoneId;}

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getTimeZone() {return timeZone;}

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeStamp() {return timeStamp;}

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAirTime() {return airTime;}

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }
}