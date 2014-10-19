package us.wylder.remotecontrol;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;


public class Remote extends Activity {

    public Button but0;
    public Button but1;
    public Button but2;
    public Button but3;
    public Button but4;
    public Button but5;
    public Button but6;
    public Button but7;
    public Button but8;
    public Button but9;
    public Button chUp;
    public Button chDown;
    public Button fave1;
    public Button fave2;
    public Button fave3;

    public Switch switch_power;
    public SeekBar seek_volume;

    public TextView cur_power;
    public TextView cur_volume;
    public TextView cur_chan;

    public String cur_chan_str = "003";

    public int cur_chan_int = 3;
    public int cur_vol_int;
    public boolean power_is_on;

    public LinearLayout tv_info;

    public static final String TAG = "Remote.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        getWidgets();
        setDefaults();
        setListeners();
    }

    private void getWidgets()
    {
        but0 = (Button) findViewById(R.id.button0);
        but1 = (Button) findViewById(R.id.button1);
        but2 = (Button) findViewById(R.id.button2);
        but3 = (Button) findViewById(R.id.button3);
        but4 = (Button) findViewById(R.id.button4);
        but5 = (Button) findViewById(R.id.button5);
        but6 = (Button) findViewById(R.id.button6);
        but7 = (Button) findViewById(R.id.button7);
        but8 = (Button) findViewById(R.id.button8);
        but9 = (Button) findViewById(R.id.button9);

        chUp = (Button) findViewById(R.id.button_chUp);
        chDown = (Button) findViewById(R.id.button_chDown);
        fave1 = (Button) findViewById(R.id.button_fav1);
        fave2 = (Button) findViewById(R.id.button_fav2);
        fave3 = (Button) findViewById(R.id.button_fav3);

        switch_power = (Switch) findViewById(R.id.switch_power);
        seek_volume = (SeekBar) findViewById(R.id.slider_volume);

        cur_power = (TextView) findViewById(R.id.cur_power);
        cur_volume = (TextView) findViewById(R.id.cur_volume);
        cur_chan = (TextView) findViewById(R.id.cur_chan);

        tv_info = (LinearLayout) findViewById(R.id.Tv_Info);
    }

    private void setDefaults()
    {
        cur_chan.setText(cur_chan_str);
        turnOff();
        cur_vol_int = 0;
    }

    private void setListeners()
    {
        but0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(0);
            }
        });

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(1);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(2);
            }
        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(3);
            }
        });

        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(4);
            }
        });

        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(5);
            }
        });

        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(6);
            }
        });

        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(7);
            }
        });

        but8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(8);
            }
        });

        but9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberButtonPressed(9);
            }
        });

        chUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channelButtonPressed(1);
            }
        });

        chDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channelButtonPressed(-1);
            }
        });

        fave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteButtonPressed("ABC");
            }
        });

        fave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteButtonPressed("CBS");
            }
        });

        fave3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteButtonPressed("NBC");
            }
        });

        switch_power.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchChanged(isChecked);
            }
        });

        seek_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cur_vol_int = progress;
                cur_volume.setText("" + cur_vol_int);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void numberButtonPressed(int num)
    {
       /* if(cur_chan_str.length() < 3) //Channels can only be 3 digits long.
        {
            cur_chan_str += num;
        }
        else
        {
            cur_chan_str = "" + num;
        }

        cur_chan.setText(cur_chan_str);

        */
       // Log.d(TAG, )
        int temp_chan = (cur_chan_int * 10) + num;
        if(temp_chan > 999)
        {
            temp_chan = 0;
        }
        cur_chan_int = temp_chan;
        if(cur_chan_int > 99  )
            cur_chan_str = "" + cur_chan_int;
        else if(cur_chan_int > 9)
            cur_chan_str = "0" + cur_chan_int;
        else
            cur_chan_str = "00" + cur_chan_int;

        cur_chan.setText(cur_chan_str);
    }

    private void turnOn()
    {
        power_is_on = true;
        seek_volume.setEnabled(true);
        but0.setEnabled(true);
        but1.setEnabled(true);
        but2.setEnabled(true);
        but3.setEnabled(true);
        but4.setEnabled(true);
        but5.setEnabled(true);
        but6.setEnabled(true);
        but7.setEnabled(true);
        but8.setEnabled(true);
        but9.setEnabled(true);
        chUp.setEnabled(true);
        chDown.setEnabled(true);
        fave1.setEnabled(true);
        fave2.setEnabled(true);
        fave3.setEnabled(true);

        cur_power.setText(R.string.On);
        tv_info.setBackgroundColor(getResources().getColor(R.color.green));
    }

    private void turnOff()
    {
        power_is_on = false;
        seek_volume.setEnabled(false);
        but0.setEnabled(false);
        but1.setEnabled(false);
        but2.setEnabled(false);
        but3.setEnabled(false);
        but4.setEnabled(false);
        but5.setEnabled(false);
        but6.setEnabled(false);
        but7.setEnabled(false);
        but8.setEnabled(false);
        but9.setEnabled(false);
        chUp.setEnabled(false);
        chDown.setEnabled(false);
        fave1.setEnabled(false);
        fave2.setEnabled(false);
        fave3.setEnabled(false);

        cur_power.setText(R.string.Off);
        tv_info.setBackgroundColor(getResources().getColor(R.color.grey));
    }

    private void channelButtonPressed(int num)
    {
        int cur_chan_int = Integer.parseInt(cur_chan_str);
        if(num == -1)
        {
           cur_chan_int --;
        }
        else if(num == 1)
        {
            cur_chan_int ++;
        }
        cur_chan_str = "" + cur_chan_int;
        cur_chan.setText(cur_chan_str);
    }

    private void favoriteButtonPressed(String chName)
    {
        if(chName.equals("ABC"))
        {
            Log.d(TAG, "chName = " + chName);
            cur_chan_int = 4;
            cur_chan_str = "004";
        }
        else if(chName.equals("CBS"))
        {
            Log.d(TAG, "chName = " + chName);
            cur_chan_int = 7;
            cur_chan_str = "007";
        }
        else if(chName.equals("NBC"))
        {
            Log.d(TAG, "chName = " + chName);
            cur_chan_int = 9;
            cur_chan_str = "009";
        }
        Log.d(TAG, "chName = " + chName);
        cur_chan.setText(cur_chan_str);
    }

    private void switchChanged(boolean isOn)
    {
        if(isOn)
        {
            turnOn();
        }
        else
        {
            turnOff();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.remote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
