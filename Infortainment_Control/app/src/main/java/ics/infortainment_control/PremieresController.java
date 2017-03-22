package ics.infortainment_control;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kevin Maul on 3/17/2017.
 */

public class PremieresController extends AsyncTask<Void, Void, List<Premiere>> {

    private Context context;

    public PremieresController(Context context) {
        this.context = context;
    }

    // ********************** IMPORTANT. DO NOT DELETE. **********************
    /*
        String filename = "premieres-data-" +
                new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) +
                ".txt";
       ***********************************************************************
    */


    @Override
    protected List<Premiere> doInBackground(Void... Params) {
        String filename = "Test04.txt";

        List<Premiere> premieres = getPremieresData(filename);

        for (Premiere p : premieres) {
            Log.d("DEBUG", "Premiere Data:\nName: " + p.name +
                    "\nChannel: " + p.channel +
                    "\nDate: " + p.date +
                    "\nTime: " + p.time +
                    "\nCategory: " + p.category +
                    "\nGenre: " + p.genre +
                    "\nType: " + p.genre +
                    "\nPlot: " + p.plot);
        }

        return premieres;
    }

    // Retrieves list of premieres from file or web scraping if no updated file exists
    private List<Premiere> getPremieresData(String filename) {
        List<Premiere> premiereData = readPremieres(filename, context);

        if (premiereData.isEmpty()) {
            Log.d("INFO", "No data found in cache. Scraping new data");

            // Get new data
            try {
                String url = "http://www.tvtango.com/premieres";
                Document document = Jsoup.connect(url).get();

                Elements shows = document.select(".premier_show");

                for (Element show : shows) {
                    List<String> premiere = new LinkedList();

                    // Get Link for more information on specific show
                    Element showLink = show.select("a[href]").first();
                    String showLinkURL = "http://www.tvtango.com" + showLink.attr("href");
                    Document subDoc = Jsoup.connect(showLinkURL).get();

                    // Show Name
                    premiere.add(showLink.text().trim());

                    // Date
                    premiere.add(subDoc.select(".info a[href]").text().trim());

                    // Premiere Details comes in combo'd string, so we separate by hyphen
                    String[] details = show.select(".premier_details").text().split(" - ");

                    if (details.length == 2) {
                        // Time
                        premiere.add(details[0].trim());
                        // Channel
                        premiere.add(details[1].trim());
                    }
                    else {
                        // Time (No value)
                        premiere.add("");
                        // Channel
                        premiere.add(details[0].trim());
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



                    String plot = "";

                    // Plot not guaranteed to exist, so need to try/catch retrival for it
                    try {
                        plot = subDoc.select("#plot_synopsis p").last().text().trim();
                    } catch (Exception e) {
                        Log.e("ERROR", "No plot found");
                    }

                    premiere.add(plot);

                    premiereData.add(Premiere.createPremiere(premiere));
                }

                // Save contents to file for future retrival
                writePremieresToFile(premiereData, filename, context);
            }
            catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }
        else
            Log.d("INFO", "Data retrieved from file.");

        return premiereData;
    }

    // Writes premieres to file
    private void writePremieresToFile(List<Premiere> premieres, String filename, Context context) {
        Log.d("INFO", "Attempting to write to file.");

        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            StringBuilder strBuilder = new StringBuilder();

            // Counter for debug purposes
            int lineCounter = 0;

            for (Premiere data : premieres) {
                strBuilder.append(data.name + ";" + data.channel + ";" + data.date + ";" + data.time + ";" + data.category + ";"
                        + data.genre + ";" + data.type + ";" + data.plot + "\n");
                lineCounter++;
            }
            writer.write(strBuilder.toString());
            writer.close();

            Log.d("INFO", "Successfully written " + lineCounter + " lines to " + filename);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    // Returns list of premieres from text file
    private List<Premiere> readPremieres(String filename, Context context) {
        List<Premiere> ret = new LinkedList();

        Log.d("INFO", "Attempting to retrieve premieres from file");

        try {
            InputStream inputStream = context.openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    List<String> premiereData = new LinkedList();
                    String[] splitString = receiveString.split(";");

                    for (String str : splitString) {
                        premiereData.add(str);
                    }

                    ret.add(Premiere.createPremiere(premiereData));
                }

                inputStream.close();

                Log.d("INFO", "Retrieved " + ret.size() + " premieres from " + filename);
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}