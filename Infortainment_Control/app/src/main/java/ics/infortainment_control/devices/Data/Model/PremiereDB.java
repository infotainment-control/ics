package ics.infortainment_control.devices.Data.Model;

public class PremiereDB {
    public static final String TAG = PremiereDB.class.getSimpleName();
    public static final String TABLE = "PremiereDB";

    // Labels Table Columns names
    public static final String KEY_PremID = "PremiereId";
    public static final String KEY_PremTitle = "Title";
    public static final String KEY_PremGenre = "Genre";
    public static final String KEY_PremCategory = "Category";
    public static final String KEY_Type = "Type";
    public static final String KEY_PremInfo = "Information";

    private String premiereId ;
    private String premiereTitle;
    private String premiereGenre;
    private String premiereCategory;
    private String premiereType;
    private String premiereInfo;

    public String getPremiereType() { return premiereType;}

    public void setPremiereType(String premiereType) { this.premiereType = premiereType;}

    public String getPremiereId() { return premiereId;}

    public void setPremiereId(String premiereId) { this.premiereId = premiereId;}

    public String getPremiereTitle() { return premiereTitle;}

    public void setPremiereTitle(String premiereTitle) { this.premiereTitle = premiereTitle;}

    public String getPremiereGenre() { return premiereGenre;}

    public void setPremiereGenre(String premiereGenre) { this.premiereGenre = premiereGenre;}

    public String getPremiereCategory() { return premiereCategory;}

    public void setPremiereCategory(String premiereCategory) { this.premiereCategory = premiereCategory;}

    public String getPremiereInfo() { return premiereInfo;}

    public void setPremiereInfo(String premiereInfo) { this.premiereInfo = premiereInfo;}

}