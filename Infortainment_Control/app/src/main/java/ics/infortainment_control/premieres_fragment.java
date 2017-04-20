package ics.infortainment_control;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ics.infortainment_control.constants.FIRST_COLUMN;
import static ics.infortainment_control.constants.FOURTH_COLUMN;
import static ics.infortainment_control.constants.SECOND_COLUMN;
import static ics.infortainment_control.constants.THIRD_COLUMN;

/**
 * Created by Jason on 3/5/2017.
 */

public class premieres_fragment extends Fragment {
    protected ArrayList<HashMap<String, String>> list;
    protected ListView listView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.premieres_layout,container,false);
        list = new ArrayList<HashMap<String,String>>();

        PremieresController premieres = new PremieresController(this, getContext());
        premieres.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        // fetch the listview from layout
        listView = (ListView) v.findViewById(R.id.listView1);

        return v;
    }

    public void populateList(List<Premiere> premieres) {
        // for loop would start here
        for (Premiere premiere : premieres) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put(FIRST_COLUMN, premiere.name);
            temp.put(SECOND_COLUMN, premiere.channel);
            temp.put(THIRD_COLUMN, premiere.date);
            temp.put(FOURTH_COLUMN, premiere.time);
            list.add(temp);
        }

        // list view adapter. needed to abstract the process of adding items from the arraylist (list) to the ListView
        ListViewAdapter adapter =new ListViewAdapter(this.getActivity(), list); // <---- right here is where it's failing. the list is empty here (because populateList isn't populating it)
        listView.setAdapter(adapter);

        final List<Premiere> premieres_copy = premieres;
        // onclick listener which will be used to display a dialog of additional information for shows
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                // handle custom dialog
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(premieres_fragment.this.getContext());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.premiere_details_layout, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog detailsDialog = dialogBuilder.create();

                // declare variables linking to layout elements
                Button return_btn;
                TextView tName;
                TextView tChannel;
                TextView tDate;
                TextView tTime;
                TextView tType;
                TextView tGenre;
                TextView tPlot;
                TextView tCategory;

                // initialize variables
                tName = (TextView) dialogView.findViewById(R.id.vname);
                tChannel = (TextView) dialogView.findViewById(R.id.vchannel);
                tDate = (TextView) dialogView.findViewById(R.id.vdate);
                tTime = (TextView) dialogView.findViewById(R.id.vtime);
                tPlot = (TextView) dialogView.findViewById(R.id.vplot);
                tType = (TextView) dialogView.findViewById(R.id.vtype);
                tGenre = (TextView) dialogView.findViewById(R.id.vgenre);
                tCategory = (TextView) dialogView.findViewById(R.id.vcategory);
                return_btn = (Button) dialogView.findViewById(R.id.return_btn);

                // get the selected premiere
                int pos=position+1;
                Premiere p = premieres_copy.get(pos-1);
                String name = p.name;
                String type = p.type;
                String genre = p.genre;
                String plot = p.plot;
                String category = p.category;
                // these three are somehow mixed up in the premiere object population process
                String channel = p.date;
                String date = p.time;
                String time = p.channel;

                // decorate textviews
                tName.setText(name);
                tChannel.setText(channel);
                tDate.setText(date);
                tTime.setText(time);
                tPlot.setText(plot);
                tType.setText(type);
                tGenre.setText(genre);
                tCategory.setText(category);

                //Toast.makeText(premieres_fragment.this.getContext(), category, Toast.LENGTH_SHORT).show();


                return_btn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          //Toast.makeText(tv_fragment.this.getContext(), "Success", Toast.LENGTH_SHORT).show();
                          //AlertDialog numpadDialog = new AlertDialog.Builder(tv_fragment.this.getContext()).create();
                          detailsDialog.dismiss();
                      }
              }
                );
                detailsDialog.show();
            }

        });
    }
}
