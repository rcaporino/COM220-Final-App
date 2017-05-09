package com.example.android.com220finalapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import service_and_storage.Friend;
import service_and_storage.Service;

/**
 * Created by Friedrich on 4/29/2017.
 */

public class FindFriends extends AppCompatActivity
{
    LocationManager locationManager;
    private Location currentLocation;
    private double currentLat;
    private double currentLon;

    private List<Friend> party = new LinkedList<Friend>(); //TODO load from file

    public boolean hasSMSPerms = false;
    public boolean hasLocPerms = false;
    public boolean hasContactPerms = false;

    private static final int RESULT_PICK_CONTACT = 8550;

    static String beginning = "http://maps.google.com/maps";

    private EditText txtV;

    ArrayAdapter<String> arrayAdapter;

    ListView partyList;

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

        partyList = (ListView) findViewById(R.id.partyList);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , (List) party);

        partyList.setAdapter(arrayAdapter);

        txtV = (EditText) findViewById(R.id.ffmessage);

        final Button smsBtn = (Button) findViewById(R.id.smsButton);
        smsBtn.setOnClickListener(new View.OnClickListener()
        {
           public void onClick(View v)
           {
               if(hasLocPerms)
               {

                   if (hasSMSPerms)
                   {
                       String msg =(String) txtV.getText().toString();
                       sendSMSToFriends(msg);
                   }
               }
           }
        });




    }

    public void pickContact(View v)
    {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }
    /**
     * Query the Uri and read contact details. Handle the picked contact data.
     * @param data
     */
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            // Set the value to the textviews
            String str = "";
            for(int i = 0; i < phoneNo.length(); i++)
            {
                if(phoneNo.charAt(i) >='0' && phoneNo.charAt(i) <='9')
                {
                    str += phoneNo.charAt(i);
                }
            }
            if(str.length() < 11) //defaults to us
            {
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String countryCode = tm.getSimCountryIso();
                str = countryCode + str;

            }

            Friend frnd = new Friend(name, str);
            Log.d("SiggiTest", frnd.toString());

            this.addFriend(frnd);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFriend(Friend frnd)
    {
        Service.getInstance().addCheersFriend(frnd);

        this.loadFriendsList();
    }

    private void sendSMSToFriends(String msg)
    {
        //TODO get friend's list
        SmsManager smsManager = SmsManager.getDefault();
        //TODO: have it loop through Friend's List.
        //the new schema doesnt work right
        //smsManager.sendTextMessage("+1" + phonenum, null, "Hey, I'm at this location: " + formURL("dir", currentLat, currentLon, 9 ), null, null);
        //old schema
        for (Friend frnd : party)
        {
            //form message
            String str = msg.replace("Friend", frnd.getName());

            smsManager.sendTextMessage("+" + frnd.getNum(), null, str + " " + formURL("dir", currentLat, currentLon, 9 ), null, null);
        }

        Toast toast = Toast.makeText(this, "SMS message sent to party", Toast.LENGTH_LONG);
        toast.show();


    }

    private void loadFriendsList()
    {
        party = Service.getInstance().getCheersFriends();
        arrayAdapter.clear();
        arrayAdapter.addAll((List)party);
        arrayAdapter.notifyDataSetChanged();
    }

    private String formURL(String rType, double lat, double lon, int zoom)
    {
        //new Schema
        //return beginning +"/" + rType + "/" + "My+Location/" + lat + "," + lon + "/@" + lat + "," + lon + "," + zoom + "z";
        //Old Schema
        return beginning + "?" + "q=" + "@" + currentLat + "+" + currentLon + "z=" + "9" ;
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
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            Log.d("SiggiReq", "Requesting Contact Read Permissions");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 7 /*TODO get a real request code.  any number may not work*/);
        }
        else
        {
            Log.d("SiggiReq", "Has Contact Read Permissions");
            this.hasContactPerms = true;
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
            case 7:{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("PERMISSIONS", "Contact Read permission granted");
                    this.hasContactPerms = true;
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

    /*public class Friend implements Serializable
    {
        private String name;
        /**
         * Should be in the format "12225551234", as that's what the SMSManager takes
         *
        private String num;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        private Friend(String name, String num)
        {
            this.name = name;
            this.num = num;
        }

        @Override
        public String toString() {
            return this.name + ": " + this.num;
        }

    }*/
}
