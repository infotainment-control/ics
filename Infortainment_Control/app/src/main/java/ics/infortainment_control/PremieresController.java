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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import ics.infortainment_control.devices.Data.Model.Channel;
import ics.infortainment_control.devices.Data.Model.PremiereDB;
import ics.infortainment_control.devices.Data.Model.TimeZone;
import ics.infortainment_control.devices.Data.Model.ServiceProvider;
import ics.infortainment_control.devices.Data.Repo.TimeZoneRepo;
import ics.infortainment_control.devices.Data.Repo.ServiceProviderRepo;
import ics.infortainment_control.devices.Data.Repo.ChannelRepo;
import ics.infortainment_control.devices.Data.Repo.PremiereRepo;
import ics.infortainment_control.devices.Model.PremiereList;


/**

 * Created by Kevin Maul on 3/17/2017.

 *

 * This class exists to kick start the

 */

public class PremieresController extends AsyncTask<Void, Void, List<Premiere>> {

    public static final String TAG = PremieresController.class.getSimpleName();
    private Context context;
    private premieres_fragment fragment;
    private static final int NUMBER_OF_TASKS = 3;

    public PremieresController(premieres_fragment fragment, Context context) {
        this.context = context;
        this.fragment = fragment;
    }
    @Override
    protected List<Premiere> doInBackground(Void... Params) {
        String filename = "testfile3"; /*"premieres-data-" +
                new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) +
                ".txt";*/
        return getPremieresData(filename);
    }
    @Override
    protected void onPostExecute(List<Premiere> result) {
        fragment.populateList(result);
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
                PremiereWorkTracker tracker = new PremiereWorkTracker(fragment, NUMBER_OF_TASKS, filename, context);
                tracker.LaunchTasks(shows);
            }
            catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }
        else
            Log.d("INFO", "Data retrieved from file.");
        return premiereData;
    }
    // Returns list of premieres from text file

    private void insertPremieres(String filename, Context context) {
        List<Premiere> ret = new LinkedList();
        Log.d("INFO", "Attempting to retrieve premieres from file");

        try {
            InputStream inputStream = context.openFileInput(filename);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ((receiveString = bufferedReader.readLine()) != null) {
                    List<String> premiereData = new LinkedList();
                    String[] splitString = receiveString.split(";");
//                    Log.d("READ", receiveString);

                    PremiereRepo premiereRepo = new PremiereRepo();
                    ChannelRepo channelRepo = new ChannelRepo();
                    TimeZoneRepo timeZoneRepo = new TimeZoneRepo();
                    ServiceProviderRepo serviceProviderRepo = new ServiceProviderRepo();


                    serviceProviderRepo.delete();
                    timeZoneRepo.delete();
                    channelRepo.delete();
                    premiereRepo.delete();

                    Channel channel = new Channel();
                    TimeZone timeZone = new TimeZone();
                    PremiereDB premiere = new PremiereDB();
                    ServiceProvider serviceProvider = new ServiceProvider();

                    Integer i = 1111;

                    for (String str : splitString) {

                        channel.setChannelId(i.toString());
                        timeZone.setTimeZoneId(i.toString());
                        premiere.setPremiereId(i.toString());
                        serviceProvider.setPremiereId(i.toString());
                        serviceProvider.setChannelId(i.toString());
                        premiere.setPremiereTitle(str);
                        serviceProvider.setChannelNumber(str);
                        timeZone.setTimeZone(str);
                        timeZone.setAirTime(str);
                        premiere.setPremiereCategory(str);
                        premiere.setPremiereGenre(str);
                        premiere.setPremiereType(str);
                        premiere.setPremiereInfo(str);

                        serviceProviderRepo.insert(serviceProvider);
                        premiereRepo.insert(premiere);
                        channelRepo.insert(channel);
                        timeZoneRepo.insert(timeZone);

                        i++;

                    }
                }
                inputStream.close();
//                Log.d("INFO", "Retrieved " + ret.size() + " premieres from " + filename);
            }
            }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {

            Log.e("login activity", "Can not read file: " + e.toString());
        }

    }
    private void readPremieres() {

        ServiceProviderRepo serviceProviderRepo = new ServiceProviderRepo();
        List<PremiereList> premiereLists = serviceProviderRepo.getServiceProvider();

        Log.d(TAG, String.format("%-35s", "PremiereDB ID") +
                String.format("%-35s", "PremiereDB Title") +
                String.format("%-35s", "PremiereDB Genre") +
                String.format("%-35s", "PremiereDB Category") +
                String.format("%-35s", "PremiereDB Information") +
                String.format("%-35s", "Channel") +
                String.format("%-35s", "Channel Name") +
                String.format("%-35s", "Channel Number") +
                String.format("%-35s", "TimeZone") +
                String.format("%-105s", "TimeZone Name")
        );

        Log.d(TAG,"=============================================================");
        for (int i = 0; i< premiereLists.size(); i++ ){
            Log.d(TAG, "0000000000".substring( premiereLists.get(i).getPremiereId().length())+ premiereLists.get(i).getPremiereId() +
                    "                                 " + String.format("%-35s", premiereLists.get(i).getPremiereTitle())+
                    String.format("%-35s", premiereLists.get(i).getPremiereGenre())+
                    String.format("%-35s", premiereLists.get(i).getPremiereCategory())+
                    String.format("%-35s", premiereLists.get(i).getPremiereInfo())+
                    String.format("%-35s", premiereLists.get(i).getChannelName())+
                    String.format("%-35s", premiereLists.get(i).getChannelName())+
                    String.format("%-31s", premiereLists.get(i).getChannelNumber())+
                    String.format("%-6s", premiereLists.get(i).getAirTime())

            );


        }
        Log.d(TAG,"=============================================================");
    }

}