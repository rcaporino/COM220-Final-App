package com.example.android.com220finalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

import java.util.List;

import service_and_storage.Drink;
import service_and_storage.Service;
import service_and_storage.User;

public class MainActivity extends AppCompatActivity {
    private ViewSwitcher simpleViewSwitcher;
    ImageButton btnChange;

    //Service service = Service.getInstance();
    //User user = service.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Service.getInstance().setFilePath(getFilesDir().getPath());

        btnChange = (ImageButton) findViewById(R.id.refresh);
        simpleViewSwitcher = (ViewSwitcher) findViewById(R.id.drink);
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

//    public void repeatDrink(View view)
//    {
//        List<Drink> listOfDrinks = service.getDrinksConsumed();
//        int sizeOfList = listOfDrinks.size();
//
//        Drink lastDrink = listOfDrinks.get(sizeOfList - 1);
//
//        java.util.Date today = new java.util.Date();
//        java.sql.Timestamp time1 = new java.sql.Timestamp(today.getTime());
//        long timeNow = time1.getTime();
//
//        service.addConsumedDrink(lastDrink.getName(),lastDrink.getSizeInOz(),
//        lastDrink.getProof(), timeNow);
//
//        System.out.println(user.getIntoxLevel());
//
//    }
//TODO right now we have:
    /*last call
    food
    cheers
    econtact
    story
     */
}

