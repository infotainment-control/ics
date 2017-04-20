package ics.infortainment_control.devices.Data.Model;

public class Channel {

    public static final String TAG = Channel.class.getSimpleName();
    public static final String TABLE = "Channel";
    // Labels Table Columns names
    public static final String KEY_ChannelId = "ChannelId";
    public static final String KEY_ChannelName = "Name";


    private String channelId;
    private String channelName;



    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {this.channelName = channelName;}

}