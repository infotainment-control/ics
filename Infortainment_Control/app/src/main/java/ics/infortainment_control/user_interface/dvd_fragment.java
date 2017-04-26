package ics.infortainment_control.user_interface;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import ics.infortainment_control.R;
import ics.infortainment_control.commands.Command;
import ics.infortainment_control.devices.AbstractDevice;
import ics.infortainment_control.devices.DeviceManager;
import ics.infortainment_control.devices.DeviceType;
import ics.infortainment_control.devices.SimpleDeviceManager;

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
    Button _mute;
    Button _rewind;
    Button _audio;

    // TODO check what's up here
    Button _numpad;

    Map<Button, Command> commandAssociations;

    AbstractDevice activeDVDDevice;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DeviceManager deviceManager = SimpleDeviceManager.getInstance();

        // This will always get the current DVD_PLAYER device
        activeDVDDevice = deviceManager.getActiveDevice(DeviceType.DVD_PLAYER);

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

        commandAssociations = new HashMap<>(18);

        // associate buttons with Commands
        assignButtonAssociations(commandAssociations);

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

        // delegate button command issuance to active TV device
        delegateButtonOnClickListener(_audio,       activeDVDDevice);
        delegateButtonOnClickListener(_rewind,      activeDVDDevice);
        delegateButtonOnClickListener(_display,     activeDVDDevice);
        delegateButtonOnClickListener(_fastforward, activeDVDDevice);
        delegateButtonOnClickListener(_stop,        activeDVDDevice);
        delegateButtonOnClickListener(_play,        activeDVDDevice);
        delegateButtonOnClickListener(_prev,        activeDVDDevice);
        delegateButtonOnClickListener(_next,        activeDVDDevice);
        delegateButtonOnClickListener(_power,       activeDVDDevice);
        delegateButtonOnClickListener(_up,          activeDVDDevice);
        delegateButtonOnClickListener(_down,        activeDVDDevice);
        delegateButtonOnClickListener(_left,        activeDVDDevice);
        delegateButtonOnClickListener(_right,       activeDVDDevice);
        delegateButtonOnClickListener(_ok,          activeDVDDevice);
        delegateButtonOnClickListener(_return,      activeDVDDevice);
        delegateButtonOnClickListener(_menu,        activeDVDDevice);
        delegateButtonOnClickListener(_mute,        activeDVDDevice);


        return v;
    }

    private void assignButtonAssociations(Map<Button, Command> map) {
        map.put(_audio, Command.AUDIO);
        map.put(_rewind, Command.REWIND);
        map.put(_mute, Command.MUTE);
        map.put(_display, Command.DISPLAY);
        map.put(_fastforward, Command.FASTFORWARD);
        map.put(_menu, Command.MENU);
        map.put(_return, Command.EXIT);
        map.put(_ok, Command.ENTER);
        map.put(_right, Command.RIGHT);
        map.put(_left, Command.LEFT);
        map.put(_up, Command.UP);
        map.put(_down, Command.DOWN);
        map.put(_stop, Command.PAUSE); // TODO this is blantant hacking of the map!
        map.put(_topmenu, Command.EJECT);  // TODO this is *also* blantant hacking of the map!
        map.put(_play, Command.PLAY);
        map.put(_power, Command.POWER);
        map.put(_prev, Command.REVERSE_STEP);
        map.put(_next, Command.FORWARD_STEP);
    }

    /**
     * Delegates issuing an infrared Command to the active television Device
     * @param button - "I will take it! I will take the infrared wavelength to Mordor... But I do not know the way."
     * @param device - "I will help you bear this burden, button."
     */
    private void delegateButtonOnClickListener(Button button, final AbstractDevice device) {
        // ensure the button has been registered with a Command
        if (commandAssociations.containsKey(button)) {
            final Command commandToBeIssued = commandAssociations.get(button);

            if (! device.knowsCommand(commandToBeIssued)) {
                // TODO [developer guts]
                button.setEnabled(false);
            } else {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        device.handleCommand(commandToBeIssued);
                    }
                });
            }
        }

        else {
            throw new RuntimeException(
                    // TODO [developer guts]
                    "DEVELOPER ERROR - trying to issue a command from a button not associated with a Command"
            );
        }
    }
}
