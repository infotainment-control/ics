package ics.infortainment_control.user_interface;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.Button;

import ics.infortainment_control.R;

/**
 * Created by Jason on 3/5/2017.
 */



public class dvd_fragment extends Fragment {

    Button _power;
    Button _play;
    Button _prev;
    Button _next;
    Button _stop;
    Button _up;
    Button _down;
    Button _left;
    Button _right;
    Button _ok;
    Button _return;
    Button _topmenu;
    Button _menu;
    Button _fastforward;
    Button _display;
    Button _numpad;
    Button _mute;
    Button _rewind;
    Button _audio;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dvd_layout,container,false);

        // tie display resources to java objects
        _power = (Button) v.findViewById(R.id.power_btn);
        _menu = (Button) v.findViewById(R.id.menu_btn);
        _topmenu = (Button) v.findViewById(R.id.topmenu_btn);
        _up = (Button) v.findViewById(R.id.directional_up_btn);
        _down = (Button) v.findViewById(R.id.directional_down_btn);
        _left = (Button) v.findViewById(R.id.directional_left_btn);
        _right = (Button) v.findViewById(R.id.directional_right_btn);
        _ok = (Button) v.findViewById(R.id.ok_btn);
        _return = (Button) v.findViewById(R.id.return_btn);
        _display = (Button) v.findViewById(R.id.display_btn);
        _numpad = (Button) v.findViewById(R.id.numpad_btn);
        _mute = (Button) v.findViewById(R.id.mute_btn);
        _rewind = (Button) v.findViewById(R.id.rewind_btn);
        _fastforward = (Button) v.findViewById(R.id.fastforward_btn);
        _prev = (Button) v.findViewById(R.id.prev_btn);
        _play = (Button) v.findViewById(R.id.play_btn);
        _stop = (Button) v.findViewById(R.id.stop_btn);
        _next = (Button) v.findViewById(R.id.next_btn);
        _audio = (Button) v.findViewById(R.id.audio_btn);

        // event listeners
        _power.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

        _topmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                }
            });

        _next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                }
            });

        _stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                }
            });

        _play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                }
            });

        _prev.setOnClickListener(new View.OnClickListener() {
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

        _fastforward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                }
            });

        _display.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                }
            });

        _mute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                }
            });

        _fastforward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                }
            });

        _numpad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(dvd_fragment.this.getContext());
                    View dialogView = getActivity().getLayoutInflater().inflate(R.layout.numpad_layout, null);
                    dialogBuilder.setView(dialogView);
                    final AlertDialog numpadDialog = dialogBuilder.create();

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
                            public void onClick(View view){
                                numpadDialog.dismiss();
                            }
                        }
                    );

                    numpadDialog.show();
                }
            });

        _audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });


        return v;
    }
}
