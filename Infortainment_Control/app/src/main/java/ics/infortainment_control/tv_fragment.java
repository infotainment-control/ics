package ics.infortainment_control;

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

import ics.infortainment_control.commands.Command;
import ics.infortainment_control.devices.Device;
import ics.infortainment_control.devices.DeviceManager;
import ics.infortainment_control.commands.IRBlasterManager;
import ics.infortainment_control.devices.DeviceRegistry;
import ics.infortainment_control.devices.DeviceRegistryProvider;
import ics.infortainment_control.devices.SimpleDeviceRegistryProvider;

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

    // non-infrared triggering button
    Button return_btn;


    // TODO if Buttons can be (or are!) persistent (or Views!!), then this can be purposeful;
    //      otherwise, delegating Command lookup to this is an indirection that saves nothing
    // TODO also: how to realize an enforcement strategy that Commands be of the DeviceType.TELEVISION subset?
    Map<Button, Command> commandAssociations;

    // TODO define retrieval of this and set it up in onCreateView,
    //      which is a perfect time for the fragment to poll the DeviceManager
    Device activeTVDevice;

    public DeviceManager deviceManager;
    public IRBlasterManager irBlasterManager;

    // TODO gray out the buttons that cannot be used because their commands are missing?
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // TODO define appropriately delegated retrieval
        DeviceRegistryProvider registryProvider_SHOULD_NOT_BE_HERE = new SimpleDeviceRegistryProvider();
        DeviceRegistry registry_SHOULD_NOT_BE_HERE = registryProvider_SHOULD_NOT_BE_HERE.getDeviceRegistry();

        String insigniaID = Device.createDeviceID("Insignia", "NS-32D312NA15|CUSTOM_SEARCH");
        Device insigniaTV = registry_SHOULD_NOT_BE_HERE.registerDevice(insigniaID);

        activeTVDevice = insigniaTV;

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

                one.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view){
                                               IRBlasterManager.getInstance().issueCommand("0000 006C 0022 0003 00AD 00AD 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 06A4 00AD 00AD 0016 0041 0016 0E6C");
                                               // ^ powers off/on an LG DVD player

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
//        _power.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                DeviceID activeDevice = deviceManager.getActiveDevice();
////                String code = deviceManager.getRawCommandCode(activeDevice, TelevisionCommand.POWER);
////                Log.d("[TV_FRAGMENT]", "Issuing code: " + code);
////                irBlasterManager.issueCommand(code);
//
//
//
//            }
//        });
        System.out.println("...");
        delegateButtonOnClickListener(_power, activeTVDevice);

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

        // TODO check that this pattern works for all
        delegateButtonOnClickListener(_tools, activeTVDevice);
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

    private void delegateButtonOnClickListener(Button button, final Device device) {
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
