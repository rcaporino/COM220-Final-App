package com.example.android.com220finalapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Friedrich on 4/29/2017.
 */

public class FindFriends extends AppCompatActivity
{
    LocationManager locationManager;
    private Location currentLocation;
    private double currentLat;
    private double currentLon;

    private String phonenum = "6319742629";

    public boolean hasSMSPerms = false;
    public boolean hasLocPerms = false;


    static String beginning = "http://maps.google.com/maps";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findfriends);
        this.reqPermissions();

        if(this.hasLocPerms)
        {
            FindLocation();
        }

        final Button smsBtn = (Button) findViewById(R.id.smsButton);
        smsBtn.setOnClickListener(new View.OnClickListener()
        {
           public void onClick(View v)
           {
               if(hasLocPerms)
               {

                   if (hasSMSPerms)
                   {
                       sendSMSToFriends();
                   }
               }
           }
        });

    }

    private void sendSMSToFriends()
    {
        //TODO get friend's list
        SmsManager smsManager = SmsManager.getDefault();
        //TODO: have it loop through Friend's List.
        //the new schema doesnt work right
        //smsManager.sendTextMessage("+1" + phonenum, null, "Hey, I'm at this location: " + formURL("dir", currentLat, currentLon, 9 ), null, null);
        //old schema
        smsManager.sendTextMessage("+1" + phonenum, null, "Hey, I'm at this location: " + formURL("dir", currentLat, currentLon, 9 ), null, null);

    }

    private String formURL(String rType, double lat, double lon, int zoom)
    {
        //new Schema
        //return beginning +"/" + rType + "/" + "My+Location/" + lat + "," + lon + "/@" + lat + "," + lon + "," + zoom + "z";
        //Old Schema
        return beginning + "?" + "z=" + "9" + "q=" + "loc:" + currentLat + "+" + currentLon;
    }

    private void reqPermissions()
    {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("SiggiReq", "Requesting Coarse Location Permissions");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        else
        {
            Log.d("SiggiReq", "Has Coarse Location Permissions");
            this.hasLocPerms = true;
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("SiggiReq", "Requesting Fine Location Permissions");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        }
        else
        {
            Log.d("SiggiReq", "Has Fine Location Permissions");
            this.hasLocPerms = true;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("SiggiReq", "Requesting SMS Permissions");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 8 /*TODO get a real request code.  any number may not work*/);
        }
        else
        {
            Log.d("SiggiReq", "Has SMS Permissions");
            this.hasSMSPerms = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 8: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("PERMISSIONS", "SMS permission granted");
                    this.hasSMSPerms = true;
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("PERMISSIONS", "Location permission granted");
                    this.hasLocPerms = true;
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
                    Log.d("PERMISSIONS", "Location permission granted");
                    this.hasLocPerms = true;
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }

    public void FindLocation() {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener()
        {
            public void onLocationChanged(Location location)
            {
                Log.d("SiggiLoc", "Location Changed");
                currentLocation = location;
                currentLat = location.getLatitude();
                currentLon = location.getLongitude();
            }


            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };



        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            Log.d("SiggiLoc", "Didn't have Location permissions");
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }
}
