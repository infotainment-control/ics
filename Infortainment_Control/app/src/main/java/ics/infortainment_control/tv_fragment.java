package ics.infortainment_control;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jason on 3/5/2017.
 */

public class tv_fragment extends Fragment {

    Button power;
    Button source;
    TextView power_label;
    TextView source_label;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // tie display resources to java objects
        View v = inflater.inflate(R.layout.tv_layout,container,false);
        power = (Button) v.findViewById(R.id.power_btn);
        source = (Button) v.findViewById(R.id.source_btn);
        power_label = (TextView) v.findViewById(R.id.power_pressed_label);
        source_label = (TextView) v.findViewById(R.id.source_pressed_label);


        // event listeners for power and source buttons
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                power_label.setText("Power button pressed");
            }
        });

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                source_label.setText("Source button pressed");
            }
        });


        return v;
    }
}
