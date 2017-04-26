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
import ics.infortainment_control.devices.BackendDeviceManager;
import ics.infortainment_control.devices.DeviceManager;
import ics.infortainment_control.devices.DeviceType;
import ics.infortainment_control.devices.SimpleDeviceManager;

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
    Button _mute;
    Button _info;

    // non-infrared triggering button
    Button _numpad;

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


    // TODO if Buttons can be (or are!) persistent (or Views!!), then this can be more purposeful
    // TODO also: how to realize an enforcement strategy that Commands be of the DeviceType.TELEVISION subset?
    Map<Button, Command> commandAssociations;

    AbstractDevice activeTVDevice;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DeviceManager deviceManager = BackendDeviceManager.getInstance();//SimpleDeviceManager.getInstance();

        activeTVDevice = deviceManager.getActiveDevice(DeviceType.TELEVISION);

        // the map that links Buttons to the appropriate Command terms so the active TV Device may be delegated to
        // when handling the button press (which will issue its IR pronto hex code string to the IRBlasterManager)
        commandAssociations = new HashMap<>(27);
        final Map<Button, Command> mainButtonAssociations = new HashMap<>(17);
        final Map<Button, Command> numberPadButtonAssociations = new HashMap<>(10);


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
        _mute = (Button) v.findViewById(R.id.mute_btn);
        _info = (Button) v.findViewById(R.id.info_btn);
        _exit = (Button) v.findViewById(R.id.exit_btn);

        // associate buttons with Commands
        assignMainButtonAssociations(mainButtonAssociations);

        // pull in the associated button:command map to the view's map
        commandAssociations.putAll(mainButtonAssociations);

        // doesn't delegate action to a Device
        _numpad = (Button) v.findViewById(R.id.numpad_btn);

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
                seven = (Button) dialogView.findViewById(R.id.seven);
                eight = (Button) dialogView.findViewById(R.id.eight);
                nine = (Button) dialogView.findViewById(R.id.nine);
                zero = (Button) dialogView.findViewById(R.id.zero);

                // associate number pad buttons with Commands
                assignNumberPadButtonAssociations(numberPadButtonAssociations);
                // pull in the associated button:command map to the view's map
                commandAssociations.putAll(numberPadButtonAssociations);

                // doesn't delegate action to a Device
                return_btn = (Button) dialogView.findViewById(R.id.return_btn);

                return_btn.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // TODO something to the effect of the below commented line is what's needed
                                // but the behavior is tricker to accommodate with the given interface
                                //activeTVDevice.handleCommand(commandAssociations.get(return_btn));
                                numpadDialog.dismiss();
                          }
                      }
                );

                // delegate button command issuance to active TV device
                delegateButtonOnClickListener(one,   activeTVDevice);
                delegateButtonOnClickListener(two,   activeTVDevice);
                delegateButtonOnClickListener(three, activeTVDevice);
                delegateButtonOnClickListener(four,  activeTVDevice);
                delegateButtonOnClickListener(five,  activeTVDevice);
                delegateButtonOnClickListener(six,   activeTVDevice);
                delegateButtonOnClickListener(seven, activeTVDevice);
                delegateButtonOnClickListener(eight, activeTVDevice);
                delegateButtonOnClickListener(nine,  activeTVDevice);
                delegateButtonOnClickListener(zero,  activeTVDevice);

                numpadDialog.show();
            }
        });

        // TODO surely with this much repetition....
        // delegate button command issuance to active TV device
        delegateButtonOnClickListener(_power,        activeTVDevice);
        delegateButtonOnClickListener(_source,       activeTVDevice);
        delegateButtonOnClickListener(_channel_up,   activeTVDevice);
        delegateButtonOnClickListener(_channel_down, activeTVDevice);
        delegateButtonOnClickListener(_volume_up,    activeTVDevice);
        delegateButtonOnClickListener(_volume_down,  activeTVDevice);
        delegateButtonOnClickListener(_up,           activeTVDevice);
        delegateButtonOnClickListener(_down,         activeTVDevice);
        delegateButtonOnClickListener(_left,         activeTVDevice);
        delegateButtonOnClickListener(_right,        activeTVDevice);
        delegateButtonOnClickListener(_ok,           activeTVDevice);
        delegateButtonOnClickListener(_return,       activeTVDevice);
        delegateButtonOnClickListener(_menu,         activeTVDevice);
        delegateButtonOnClickListener(_exit,         activeTVDevice);
        delegateButtonOnClickListener(_tools,        activeTVDevice);
        delegateButtonOnClickListener(_mute,         activeTVDevice);
        delegateButtonOnClickListener(_info,         activeTVDevice);

        return v;
    }

    // TODO there ought to be a mechanism in place to verify JUST ONCE that these are elements
    //      of the TELEVISION Command EnumSet

    private void assignMainButtonAssociations(Map<Button, Command> map) {
        map.put(_power,        Command.POWER);
        map.put(_source,       Command.SOURCE);
        map.put(_channel_up,   Command.CHANNEL_UP);
        map.put(_channel_down, Command.CHANNEL_DOWN);
        map.put(_volume_up,    Command.VOLUME_UP);
        map.put(_volume_down,  Command.VOLUME_DOWN);
        map.put(_up,           Command.UP);
        map.put(_down,         Command.DOWN);
        map.put(_left,         Command.LEFT);
        map.put(_right,        Command.RIGHT);
        map.put(_ok,           Command.ENTER);

        // TODO note the mismatch? This likely has standardization implications
        map.put(_return, Command.EXIT);
        map.put(_menu,   Command.MENU);
        map.put(_exit,   Command.EXIT);

        // TODO standardization mismatch?
        map.put(_tools, Command.DISPLAY);
        map.put(_mute,  Command.MUTE);
        map.put(_info,  Command.INFO);
    }

    private void assignNumberPadButtonAssociations(Map<Button, Command> map) {
        map.put(one,   Command.ONE);
        map.put(two,   Command.TWO);
        map.put(three, Command.THREE);
        map.put(four,  Command.FOUR);
        map.put(five,  Command.FIVE);
        map.put(six,   Command.SIX);
        map.put(seven, Command.SEVEN);
        map.put(eight, Command.EIGHT);
        map.put(nine,  Command.NINE);
        map.put(zero,  Command.ZERO);
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
