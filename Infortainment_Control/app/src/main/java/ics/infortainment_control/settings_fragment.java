package ics.infortainment_control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.DecimalFormat;

/**
 * Created by Jason on 3/5/2017.
 */



public class settings_fragment extends Fragment {


    Button add_tv_btn;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_layout,containter,false);
        add_tv_btn = (Button) v.findViewById(R.id.add_television_btn);
        add_tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_tv_fragment f = new add_tv_fragment();
                getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
            }
        });

        return v;
    }
}
