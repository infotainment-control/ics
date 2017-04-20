package ics.infortainment_control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kevin Maul on 3/17/2017.
 */

public class Premiere {
    public String name;
    public String channel;
    public String date;
    public String time;
    public String category;
    public String genre;
    public String type;
    public String plot;

    public Premiere(String pName, String pDate, String pTime, String pChannel, String pCategory, String pGenre, String pType, String pPlot) {
        name = pName;
        channel = pChannel;
        date = pDate;
        time = pTime;
        category = pCategory;
        genre = pGenre;
        type = pType;
        plot = pPlot;
    }

    public Premiere(String pName) {
        this(pName, " ", " ", " ", " ", " ", " ", " ");
    }

    public static Premiere createPremiere(List<String> data) {
        if (data.size() >= 8)
            return new Premiere(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7));
        else if (data.size() == 1)
            return new Premiere(data.get(0));
        else
            return new Premiere("");
    }

    @Override
    public String toString() {
        return new StringBuilder().append(name).append(channel).append(date).append(time).append(category).append(genre).append(type).append(plot).toString();
    }
}
