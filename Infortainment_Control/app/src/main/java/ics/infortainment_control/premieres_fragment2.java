package ics.infortainment_control;
import static ics.infortainment_control.constants.FIRST_COLUMN;
import static ics.infortainment_control.constants.SECOND_COLUMN;
import static ics.infortainment_control.constants.THIRD_COLUMN;
import static ics.infortainment_control.constants.FOURTH_COLUMN;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

public class premieres_fragment2 extends Fragment {

    private ArrayList<HashMap<String, String>> list;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.premieres_layout2,container,false);

        ListView listView= (ListView) v.findViewById(R.id.listView1);
        list=new ArrayList<HashMap<String,String>>();
        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put(FIRST_COLUMN, "Silicon Valley");
        temp.put(SECOND_COLUMN, "HBO");
        temp.put(THIRD_COLUMN, "4/24/2017");
        temp.put(FOURTH_COLUMN, "9p ET");
        list.add(temp);


        ListViewAdapter adapter=new ListViewAdapter(this.getActivity(), list);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                Toast.makeText(premieres_fragment2.this.getContext(), Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
            }

        });

        return v;
    }
}
