package com.example.android.com220finalapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;

public class LastCall extends AppCompatActivity {

    int hour;
    int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_call);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setLastCall (View view)
    {
        TimePicker clock =  (TimePicker)findViewById(R.id.clock);
        hour = clock.getHour();
        minute = clock.getMinute();
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "Last Call!")
                .putExtra(AlarmClock.EXTRA_HOUR, 8)
                .putExtra(AlarmClock.EXTRA_MINUTES, 32)
                .putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
}

