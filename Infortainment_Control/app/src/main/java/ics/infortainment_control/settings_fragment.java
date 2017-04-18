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

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_layout,containter,false);


        return v;
    }
}
