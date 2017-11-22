package com.example.android.com220finalapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;


import service_and_storage.Service;


public class MainActivity extends AppCompatActivity {

    private ViewSwitcher simpleViewSwitcher;
    ImageButton btnChange;
    public boolean hasStorageReadPerms = false, hasStorageWritePerms=false;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Service.setFilePath(getFilesDir());

        //btnChange = (ImageButton) findViewById(R.id.refresh);
       // simpleViewSwitcher = (ViewSwitcher) findViewById(R.id.drink);
        reqPermissions(1);
        reqPermissions(2);

    }

    public void reqPermissions(int i) {
        switch (i) {
            case 1: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Log.i("BakerReq", "Requesting Write Permissions");
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    Log.i("BakerReq", "Has Write Permissions");
                    this.hasStorageWritePerms = true;
                }
            }
            case 2:{
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Log.i("BakerReq", "Requesting Read Permissions");
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            2);
                } else {
                    Log.i("BakerReq", "Has Read Permissions");
                    this.hasStorageReadPerms = true;
                }
            }
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
                    hasStorageWritePerms = true;
                    reqPermissions(2);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                 //   reqPermissions(1);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case 2:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    hasStorageReadPerms = true;
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
//                    reqPermissions(2);
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;

            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public void changeBtn(View view)
    {
        //Intent intent = new Intent(MainActivity.this, ChangeActivity.class);
        //startActivity(intent);
        //simpleViewSwitcher.showNext();
    }

    public void lastCallBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, LastCall.class);
        startActivity(intent);
    }

    public void foodBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, Eating.class);
        startActivity(intent);
    }

    public void cheersBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, Cheers.class);
        startActivity(intent);
    }

    public void eContactBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, EmergencyContact.class);
        startActivity(intent);
    }

    public void friendsBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, FindFriends.class);
        startActivity(intent);
    }

    public void stryBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, cameraclass.class);
        startActivity(intent);
    }

    public void h20Btn(View view)
    {
        //Intent intent = new Intent(MainActivity.this, FoodActivity.class);
        //startActivity(intent);
    }

    public void favBtn(View view)
    {
        //Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
        //startActivity(intent);
    }

    public void remBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, HangoverWebView.class);
        startActivity(intent);
    }

    public void accBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, setup.class);
        startActivity(intent);
    }

    public void statBtn(View view)
    {
        //Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
        //startActivity(intent);
    }

    public void calBtn(View view)
    {
        //Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
        //startActivity(intent);
    }

    public void addBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, MyDrink.class);
        startActivity(intent);
    }

//TODO right now we have:
    /*last call
    food
    cheers
    econtact
    story
     */
}

