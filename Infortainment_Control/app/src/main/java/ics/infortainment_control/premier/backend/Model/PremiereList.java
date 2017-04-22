package ics.infortainment_control.premier.backend.Model;


public class PremiereList {
    private String premiereId;
    private String premiereTitle;
    private String premiereGenre;
    private String premiereCategory;
    private String premiereType;
    private String premiereInfo;
    private String channelName;
    private String channelNumber;
    private String airTime;
    private String channelId;
    private String timeZoneId;

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getPremiereType() {return premiereType; }

    public void setPremiereType(String premiereType) {this.premiereType = premiereType;}

    public String getPremiereId() {
        return premiereId;
    }

    public void setPremiereId(String premiereId) {
        this.premiereId = premiereId;
    }

    public String getPremiereTitle() {
        return premiereTitle;
    }

    public void setPremiereTitle(String premiereTitle) {
        this.premiereTitle = premiereTitle;
    }

    public String getPremiereGenre() {
        return premiereGenre;
    }

    public void setPremiereGenre(String premiereGenre) {
        this.premiereGenre = premiereGenre;
    }

    public String getPremiereCategory() {
        return premiereCategory;
    }

    public void setPremiereCategory(String premiereCategory) {this.premiereCategory = premiereCategory;}

    public String getPremiereInfo() {
        return premiereInfo;
    }

    public void setPremiereInfo(String premiereInfo) {
        this.premiereInfo = premiereInfo;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {this.channelId = channelId;}

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {this.channelName = channelName;}

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }
}