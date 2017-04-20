package ics.infortainment_control.devices.Data.Model;

public class ServiceProvider {
    public static final String TAG = ServiceProvider.class.getSimpleName();
    public static final String TABLE = "ServiceProvider";

    // Labels Table Columns names
    public static final String KEY_ServiceProviderID = "ServiceProviderID";
    public static final String KEY_PremID = "PremiereId";
    public static final String KEY_ChannelId = "ChannelId";
    public static final String KEY_ChannelNumber = "ChannelNumber";


    public String premiereId;
    public String channelId;
    public String channelNumber;


    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;    }

    public String getPremiereId() {
        return premiereId;
    }

    public void setPremiereId(String premiereId) {
        this.premiereId = premiereId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

}