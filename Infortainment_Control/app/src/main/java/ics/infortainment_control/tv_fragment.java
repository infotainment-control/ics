package ics.infortainment_control;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Jason on 3/5/2017.
 */

public class tv_fragment extends Fragment {

    Button _power;
    Button _source;
    Button _channel_up;
    Button _channel_down;
    Button _volume_up;
    Button _volume_down;
    Button _up;
    Button _down;
    Button _left;
    Button _right;
    Button _ok;
    Button _return;
    Button _menu;
    Button _exit;
    Button _tools;
    Button _numpad;
    Button _mute;
    Button _info;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // tie display resources to java objects
        View v = inflater.inflate(R.layout.tv_layout,container,false);
        _power = (Button) v.findViewById(R.id.power_btn);
        _source = (Button) v.findViewById(R.id.source_btn);
        _channel_down = (Button) v.findViewById(R.id.channel_down_btn);
        _channel_up = (Button) v.findViewById(R.id.channel_up_btn);
        _volume_down = (Button) v.findViewById(R.id.volume_down_btn);
        _volume_up = (Button) v.findViewById(R.id.volume_up_btn);
        _up = (Button) v.findViewById(R.id.directional_up_btn);
        _down = (Button) v.findViewById(R.id.directional_down_btn);
        _left = (Button) v.findViewById(R.id.directional_left_btn);
        _right = (Button) v.findViewById(R.id.directional_right_btn);
        _ok = (Button) v.findViewById(R.id.ok_btn);
        _return = (Button) v.findViewById(R.id.return_btn);
        _tools = (Button) v.findViewById(R.id.tools_btn);
        _menu = (Button) v.findViewById(R.id.topmenu_btn);
        _numpad = (Button) v.findViewById(R.id.numpad_btn);
        _mute = (Button) v.findViewById(R.id.mute_btn);
        _info = (Button) v.findViewById(R.id.info_btn);
        _exit = (Button) v.findViewById(R.id.exit_btn);




        // event listeners for power and source buttons
        _power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        _source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _volume_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _volume_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _channel_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _channel_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });

        _numpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                /*
                DialogFragment numpad = new numpad_fragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
*/
                //numpad.show(fm, "numpad");
            }
        });


        return v;
    }
}
