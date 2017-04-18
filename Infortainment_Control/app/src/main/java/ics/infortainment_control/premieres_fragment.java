package ics.infortainment_control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.premieres_layout,container,false);
        list=new ArrayList<HashMap<String,String>>();

        PremieresController premieres = new PremieresController(this, getContext());
        premieres.execute();
        // fetch the listview from layout
        ListView listView= (ListView) v.findViewById(R.id.listView1);

        // list view adapter. needed to abstract the process of adding items from the arraylist (list) to the ListView
        ListViewAdapter adapter =new ListViewAdapter(this.getActivity(), list); // <---- right here is where it's failing. the list is empty here (because populateList isn't populating it)
        listView.setAdapter(adapter);

        // onclick listener which will be used to display a dialog of additional information for shows
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                Toast.makeText(premieres_fragment.this.getContext(), Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
            }

        });

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

            // parse data from data structure to populate name, date, and time for each premiere in the database
            // create new rows
            /*
            TableRow newRow = new TableRow(getActivity());
            TextView name = new TextView(getActivity());
            TextView date = new TextView(getActivity());
            TextView time = new TextView(getActivity());

            //style new rows
            name.setPadding(10, 0, 0, 0);
            date.setPadding(10, 0, 0, 0);
            time.setPadding(10, 0, 0, 0);

            name.setBackgroundResource(R.drawable.cell_shape);
            date.setBackgroundResource(R.drawable.cell_shape);
            time.setBackgroundResource(R.drawable.cell_shape);

            name.setWidth(Math.round(two_hundred_four_pixels));
            date.setWidth(Math.round(ninety_pixels));
            time.setWidth(Math.round(ninety_pixels));

            name.setHeight(100);
            date.setHeight(100);
            time.setHeight(100);

            name.setTextSize(13);
            date.setTextSize(13);
            time.setTextSize(13);

            name.setText(premiere.name);
            date.setText(premiere.date);
            time.setText(premiere.time);

            // add views to the row
            newRow.addView(name);
            newRow.addView(date);
            newRow.addView(time);

            // add the row to the table layout
            table.addView(newRow);
        } */
    }
}
