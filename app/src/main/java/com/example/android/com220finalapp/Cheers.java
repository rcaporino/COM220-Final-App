package com.example.android.com220finalapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import service_and_storage.Friend;
import service_and_storage.Service;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Cheers extends AppCompatActivity implements SensorEventListener, CheersJsonAsync.Handoff {
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
    private boolean confirmedDirections = false;
    LocationManager locationManager;
    LocationListener locationListener;
    private boolean hasLocPerms = false;
    private String friendName = "", friendNum="";
    private String url = "";

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
private boolean done = false;
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
    TextView cheersText;
    Button cheersButton;

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
        mContentView = findViewById(R.id.cheersMainText);
        cheersText = (TextView) findViewById(R.id.cheersMainText);
        cheersButton = (Button) findViewById(R.id.cheers_button);
        Log.i("BakerContext", "Setting Context");
        Log.i("BakerAccel", "Getting Accel");
        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(accelerometer.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.i("BakerAccel", "Accel set up.");

        //TODO put the pairing notification to the server here with the time.
        if(hasLocPerms)
            findLocation();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    public void findLocation() {

        locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Log.i("BAKERLOC","Location changed");
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
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("BakerDeny", "Didn't have permissions");
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

    public long getTimeInMilliseconds() {
        return System.currentTimeMillis();
    }


    public void updateLocation(Location location) {
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
        if (currentLatitude == 0.0) {
cheersText.setText("Loading Location \n");
        } else {
            accelerometer = event.sensor;
            if (accelerometer.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                //stores the x and y acceleration.

                currentTime = System.currentTimeMillis();
                if (!upwardSuccessful) {
                    if(confirmedDirections == false) {
                        cheersText.setTextSize(20);
                        cheersText.setText("Instructions: \n\n" +
                                "At the same time, thrust your phones forward, bump knuckles, and up toward the sky!\n\n" +
                                "Push the button when ready!");
                        cheersButton.setText("Go!");
                    } else{
                    if (currentTime - lastTime > 5) {

                        cheersText.setTextSize(40);
                        cheersText.setText("Go!\nCheers mate!\n");
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


                    }
                    }
                } else if (done == false) {
                    Log.i("BakerComplete", "Mission Completed at " + timeStamp + " Location: " + currentLatitude + " , " + currentLongitude);
                    cheersText.setText("Attempting Pairing");
                    cheersText.setTextSize(30);
                   //cheersText.setText("Cheers Complete at: \n" + timeStamp + "\n" + "Location: " + "\n" + currentLatitude + " , " + currentLongitude);
                    new CheersJsonAsync(createUrl(),this).execute();
                    lastTime = currentTime;
                    done = true;
                    cheersButton.setText("Restart");
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void reqPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("BakerReq", "Requesting Coarse Permissions");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }        else
        {
            Log.i("BakerReq", "Has Coarse Location Permissions");
            this.hasLocPerms = true;
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("BakerReq", "Requesting Fine Permissions");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    2);
        }     else
        {
            Log.i("BakerReq", "Has Fine Location Permissions");
            this.hasLocPerms = true;
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
                    hasLocPerms = true;
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
                    hasLocPerms = true;
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

    //TODO add in the below once the friends list is ready to be implemented
    @Override
    public void backToMainActivity(ArrayList<String> s){
        if(!s.isEmpty()) {
            friendName = s.get(0);
            friendNum = s.get(1);

            if (!friendName.equals("No Cheers")) {
                cheersText.setText("Friend Name: \n" + friendName + "\nFriend Number =" + friendNum);
            } else {
                cheersText.setText("No match found!\nPress the button below to try again!");
            }

        }
        else{
            cheersText.setText("Cheers pairing currently unavailable!\nPress the button below to try again!");
        }
    }
public String createUrl(){
    String urlStart ="http://quizkidappapi.azurewebsites.net/api/com220?";
    String time = "time="+Long.toString(timeStamp);
    String locLat = "&locationLat="+Double.toString(currentLatitude);
    String locLong = "&locationLong="+Double.toString(currentLongitude);
    //TODO get the user phone number
    String phone = "&phone="+"6315664542";
    String number = "&number="+"6315664542";
    //TODO create an xml slot where you put in your name before you begin and integrate it
    String name = "&name="+"John";
    //String name = "name="+Service.getInstance().getName;

    return urlStart+time+locLat+locLong+phone+number+name;
}

    public void confirmFriend(){
        // addFriend(friendName,friendNum);
    }

    public void addFriend(String name, String num){
       Service.getInstance().addCheersFriend(new Friend(name,num));
    }

    public void buttonPress(View view){
        if(confirmedDirections==true && currentLatitude!=0.0) {
            done = false;
            forwardSuccessful = false;
            stoppedSuccessful = false;
            upwardSuccessful = false;
            cheersText.setTextSize(20);
            cheersText.setText("Instructions: \n\n" +
                    "At the same time, thrust your phones forward, bump knuckles, and up toward the sky!\n\n" +
                    "Push the button when ready!");
            cheersButton.setText("Restart");
            confirmedDirections=false;
        } else {
confirmedDirections =true;
            cheersButton.setText("Restart");
        }
    }

}
