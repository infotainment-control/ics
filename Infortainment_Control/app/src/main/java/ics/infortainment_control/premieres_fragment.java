package ics.infortainment_control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jason on 3/5/2017.
 */

public class premieres_fragment extends Fragment {
    TableLayout table;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.premieres_layout,container,false);

        // reference the table layout
        table = (TableLayout) v.findViewById(R.id.premieres_table);

        PremieresController premieres = new PremieresController(this, getContext());
        premieres.execute();

        return v;
    }

    public void populateList(List<Premiere> premieres) {
        // make the pixel widths device independent
        float ninety_pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());
        float two_hundred_four_pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 65, getResources().getDisplayMetrics());

        // for loop would start here
        for (Premiere premiere : premieres) {
            // parse data from data structure to populate name, date, and time for each premiere in the database
            // create new rows
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
        }
    }
}
