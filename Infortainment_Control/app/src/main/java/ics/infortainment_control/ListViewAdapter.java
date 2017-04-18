package ics.infortainment_control;
import static ics.infortainment_control.constants.FIRST_COLUMN;
import static ics.infortainment_control.constants.SECOND_COLUMN;
import static ics.infortainment_control.constants.THIRD_COLUMN;
import static ics.infortainment_control.constants.FOURTH_COLUMN;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;
    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater=activity.getLayoutInflater();
        if(convertView == null){
            convertView=inflater.inflate(R.layout.column_row, null);
            txtFirst=(TextView) convertView.findViewById(R.id.name);
            txtSecond=(TextView) convertView.findViewById(R.id.network);
            txtThird=(TextView) convertView.findViewById(R.id.date);
            txtFourth=(TextView) convertView.findViewById(R.id.time);

        }

        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get(FIRST_COLUMN));
        txtSecond.setText(map.get(SECOND_COLUMN));
        txtThird.setText(map.get(THIRD_COLUMN));
        txtFourth.setText(map.get(FOURTH_COLUMN));

        return convertView;
    }

}