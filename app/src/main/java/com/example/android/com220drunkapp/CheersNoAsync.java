package com.example.android.com220drunkapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CheersNoAsync extends AppCompatActivity implements SensorEventListener {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */

    private static final boolean AUTO_HIDE = true;
    long timeStamp = 0L;
    Location currentLocation;
    double currentLatitude, currentLongitude;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private final float THRESHOLD = (float) 1;
    private Context mContext;
    private float x = 0, y = 0;
    private float lastX = 0, lastY = 0;
    private float xSpeed = (float) (THRESHOLD + 0.000000001);
    private float ySpeed = (float) (THRESHOLD + 0.000000001);
    private long currentTime = 0L;
    private long lastTime = 0L;
    private long differenceInTime = 0L;
    private boolean forwardSuccessful = false;
    private boolean stoppedSuccessful = false;
    private boolean upwardSuccessful = false;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheers);
        reqPermissions();
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        Log.i("BakerContext", "Setting Context");
        mContext = CheersNoAsync.this;
        Log.i("BakerAccel", "Getting Accel");
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(accelerometer.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.i("BakerAccel", "Accel set up.");


        //TODO put the pairing notification to the server here with the time.
        FindLocation();
        //   new AsyncAccelChecker(this, this).execute();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }


    public void FindLocation() {

        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                updateLocation(location);
            }


            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("BakerDeny", "Didn't have permissions");
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

    public long getTimeInMilliseconds() {
        return System.currentTimeMillis();
    }


    void updateLocation(Location location) {
        currentLocation = location;
        currentLatitude = currentLocation.getLatitude();
        currentLongitude = currentLocation.getLongitude();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("BakerPause", "Pausing");
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BakerResume", "Resuming");
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        accelerometer = event.sensor;
        if (accelerometer.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            //stores the x and y acceleration.

            currentTime = System.currentTimeMillis();
            if (!upwardSuccessful) {
                if (currentTime - lastTime > 5) {
                    if (event.values[0] > THRESHOLD) {
                        x = event.values[0];

                    }
                    if (event.values[1] > THRESHOLD) {
                        y = event.values[1];
                    }

                    differenceInTime = currentTime - lastTime;

                    xSpeed = Math.abs(x - lastX) / differenceInTime * 100;
                    ySpeed = Math.abs(y - lastY) / differenceInTime * 100;
                    Log.i("BakerAccel", "X Speed:" + xSpeed);
                    Log.i("BakerAccel", "Y Speed:" + ySpeed);
                    if (xSpeed >= 1 && !stoppedSuccessful && !upwardSuccessful) {
                        forwardSuccessful = true;
                        Log.i("BakerAccel", "Went forward");
                    }
                    if (!upwardSuccessful && forwardSuccessful && xSpeed <= THRESHOLD) {
                        stoppedSuccessful = true;
                        Log.i("BakerAccel", "Stopped");
                    }
                    if (forwardSuccessful && stoppedSuccessful && event.values[1] >= 1) {
                        upwardSuccessful = true;
                        Log.i("BakerAccel", "Went upward");
                        timeStamp = System.currentTimeMillis();
                    }
                    //the above checks that you went forward, then stopped, then went upward.
                    lastTime = currentTime;
                    lastX = x;
                    lastY = y;

//TODO create an algorithm that gets the distance traveled forward and the distance traveled upward after

                }
            } else if (currentTime - lastTime > 3000) {
                Log.i("BakerComplete", "Mission Completed at " + timeStamp + "Location: " + currentLatitude + " , " + currentLongitude);
                lastTime = currentTime;
            }
        }
//TODO figure out how to have the thing listen for the boolean change
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    //TODO figure out how to find the time on the device with a lower API requirement (not 24)
    public void reqPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("BakerReq", "Requesting Coarse Permissions");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1 /*TODO get a real request code.  any number may not work*/);
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("BakerReq", "Requesting Fine Permissions");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    2 /*TODO get a real request code.  any number may not work*/);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    FindLocation();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case 2: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    FindLocation();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
