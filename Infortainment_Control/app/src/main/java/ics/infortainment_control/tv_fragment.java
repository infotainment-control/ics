package ics.infortainment_control;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

/**
 * Created by Jason on 3/5/2017.
 */

public class tv_fragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tv_layout,containter,false);
        return v;
    }
}
