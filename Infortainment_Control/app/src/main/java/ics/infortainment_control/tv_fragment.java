package ics.infortainment_control;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import ics.infortainment_control.commands.Command;
import ics.infortainment_control.devices.Device;
import ics.infortainment_control.devices.DeviceManager;
import ics.infortainment_control.commands.IRBlasterManager;

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

    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button return_btn;

    // TODO if Buttons can be persistent (or Views!!), then this can be purposeful;
    //      otherwise, delegating Command lookup to this is an indirection that saves nothing
    Map<Button, Command> commandAssociations;

    // TODO define retrieval of this and set it up in onCreateView,
    //      which is a perfect time for the fragment to poll the DeviceManager
    Device activeTVDevice;

    public DeviceManager deviceManager;
    public IRBlasterManager irBlasterManager;

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
        _menu = (Button) v.findViewById(R.id.menu_btn);
        _numpad = (Button) v.findViewById(R.id.numpad_btn);
        _mute = (Button) v.findViewById(R.id.mute_btn);
        _info = (Button) v.findViewById(R.id.info_btn);
        _exit = (Button) v.findViewById(R.id.exit_btn);



        // TODO assign numpad buttons here?

        // TODO then delegate Command triggering




        _numpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(tv_fragment.this.getContext());
                View dialogView = getActivity().getLayoutInflater().inflate(R.layout.numpad_layout, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog numpadDialog = dialogBuilder.create();

                one = (Button) dialogView.findViewById(R.id.one);
                two = (Button) dialogView.findViewById(R.id.two);
                three = (Button) dialogView.findViewById(R.id.three);
                four = (Button) dialogView.findViewById(R.id.four);
                five = (Button) dialogView.findViewById(R.id.five);
                six = (Button) dialogView.findViewById(R.id.six);
                seven= (Button) dialogView.findViewById(R.id.seven);
                eight= (Button) dialogView.findViewById(R.id.eight);
                nine= (Button) dialogView.findViewById(R.id.nine);
                zero = (Button) dialogView.findViewById(R.id.zero);
                return_btn = (Button) dialogView.findViewById(R.id.return_btn);

                one.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view){
                                               IRBlasterManager.getInstance().issueCommand("0000 006C 0022 0003 00AD 00AD 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 06A4 00AD 00AD 0016 0041 0016 0E6C");
                                           }
                                       }
                );
                two.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view){

                                           }
                                       }
                );
                three.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view){

                                             }
                                         }
                );
                four.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view){

                                            }
                                        }
                );
                five.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view){

                                            }
                                        }
                );
                six.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view){

                                           }
                                       }
                );
                seven.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view){

                                             }
                                         }
                );
                eight.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view){

                                             }
                                         }
                );
                nine.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view){

                                            }
                                        }
                );
                zero.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view){

                                            }
                                        }
                );
                return_btn.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View view) {
                                                      //Toast.makeText(tv_fragment.this.getContext(), "Success", Toast.LENGTH_SHORT).show();
                                                      //AlertDialog numpadDialog = new AlertDialog.Builder(tv_fragment.this.getContext()).create();
                                                      numpadDialog.dismiss();
                                                  }
                                              }
                );

                numpadDialog.show();
            }
        });




        // event listeners for power and source buttons
        _power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DeviceID activeDevice = deviceManager.getActiveDevice();
//                String code = deviceManager.getRawCommandCode(activeDevice, TelevisionCommand.POWER);
//                Log.d("[TV_FRAGMENT]", "Issuing code: " + code);
//                irBlasterManager.issueCommand(code);



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

        return v;
    }
}
