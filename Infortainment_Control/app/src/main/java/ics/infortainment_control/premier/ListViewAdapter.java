package ics.infortainment_control.premier;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ics.infortainment_control.R;

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
        final ViewHolder holder;
        LayoutInflater inflater=activity.getLayoutInflater();
        if(convertView == null){
            convertView=inflater.inflate(R.layout.column_row, null);
            holder = new ViewHolder();
            holder.txtFirst=(TextView) convertView.findViewById(R.id.name);
            holder.txtSecond=(TextView) convertView.findViewById(R.id.network);
            holder.txtThird=(TextView) convertView.findViewById(R.id.date);
            holder.txtFourth=(TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        HashMap<String, String> map=list.get(position);
        holder.txtFirst.setText(map.get(constants.FIRST_COLUMN));
        holder.txtSecond.setText(map.get(constants.SECOND_COLUMN));
        holder.txtThird.setText(map.get(constants.THIRD_COLUMN));
        holder.txtFourth.setText(map.get(constants.FOURTH_COLUMN));

        return convertView;
    }

}
