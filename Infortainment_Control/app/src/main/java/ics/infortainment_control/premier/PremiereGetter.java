package ics.infortainment_control.premier;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Hawkaloo on 4/19/2017.
 */

public class PremiereGetter extends AsyncTask<Void, Void, Void> {

    public int Number;
    public List<Premiere> premieresList;

    private Elements shows;
    private PremiereWorkTracker tracker;

    public PremiereGetter(Elements shows, PremiereWorkTracker tracker, int num) {
        this.shows = shows;
        this.Number = num;
        this.tracker = tracker;
    }

    @Override
    protected Void doInBackground(Void... Params) {
        PremiereHtmlParser parser = new PremiereHtmlParser();
        premieresList = parser.getPremieresData(shows);

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        tracker.TaskFinished(this);
    }

    // Retrieves list of premieres from file or web scraping if no updated file exists
    private List<Premiere> getPremieresData() {
        List<Premiere> premiereData = new LinkedList<>();

        Log.i("TASK " + Number, "Executing!");

        // Get new data
        try {
            for (Element show : shows) {
//                Log.i("TASK " + Number, "Adding new show...");
                List<String> premiere = new LinkedList();

                // Get Link for more information on specific show
                Element showLink = show.select("a[href]").first();
                Document subDoc = Jsoup.connect("http://www.tvtango.com" + showLink.attr("href")).get();

                // Show Name
                premiere.add(showLink.text().trim());

                // Date
                Element date = subDoc.select(".info a[href]").first();

                // Date may come from separate element
                if (date == null)
                    date = subDoc.select("#airdates a[href]").first();

                premiere.add(date.text().trim());

                // Premiere Details comes in combo'd string, so we separate by hyphen
                String[] details = show.select(".premier_details").text().split(" - ");

                if (details.length == 2) {
                    // Time and Channel
                    premiere.add(details[0].trim());
                    premiere.add(details[1].trim());
                } else if (details.length == 1) {
                    // Only channel value
                    premiere.add(" ");
                    premiere.add(details[0].trim());
                } else {
                    // No Time or Channel value
                    premiere.add(" ");
                    premiere.add(" ");
                }

                // Category
                String[] category = subDoc.select("li:contains(Category:)").last().text().split(":");
                premiere.add(category.length >= 2 ? category[1].trim() : "");

                // Genre
                String[] genre = subDoc.select("li:contains(Genre:)").last().text().split(":");
                premiere.add(genre.length >= 2 ? genre[1].trim() : "");

                // Type
                String[] type = subDoc.select("li:contains(Type:)").last().text().split(":");
                premiere.add(type.length >= 2 ? type[1].trim() : "");

                // Plot not guaranteed to exist
                String plot = " ";
                try {
                    plot = subDoc.select("#plot_synopsis p").last().text().trim();
                } catch (Exception e) {
                    Log.w("WARN", "Plot missing for show");
                }

                premiere.add(plot);
                Premiere p = Premiere.createPremiere(premiere);
                premiereData.add(p);

//                Log.i("TASK " + Number, "Show successfully added!");
//                Log.i("TASK " + Number, p.toString());
            }
        } catch (Exception e) {
            Log.e("ERROR", e.toString());
        }

        Log.i("TASK " + Number, "Shutting down...");

        return premiereData;
    }
}