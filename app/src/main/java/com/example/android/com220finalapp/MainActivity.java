package com.example.android.com220finalapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.android.com220finalapp.R;
import com.example.android.com220finalapp.cameraclass;

public class MainActivity extends AppCompatActivity {
    private ViewSwitcher simpleViewSwitcher;
    ImageButton btnChange;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        //Intent intent = new Intent(MainActivity.this, FoodActivity.class);
        //startActivity(intent);
    }

    public void foodBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, Eating.class);
        startActivity(intent);
    }

<<<<<<< HEAD
    public void callCameraClass(View view)
    {
        Intent intent = new Intent(this, cameraclass.class);
        startActivity(intent);
    }

=======
    public void cheersBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, CheersNoAsync.class);
        startActivity(intent);
    }

    public void eContactBtn(View view)
    {
        Intent intent = new Intent(MainActivity.this, EmergencyContact.class);
        startActivity(intent);
    }

    public void locaBtn(View view)
    {
        //Intent intent = new Intent(MainActivity.this, FoodActivity.class);
        //startActivity(intent);
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
        //Intent intent = new Intent(MainActivity.this, RemedyActivity.class);
        //startActivity(intent);
    }

    public void accBtn(View view)
    {
        //Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        //startActivity(intent);
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
        //Intent intent = new Intent(MainActivity.this, AddActivity.class);
        //startActivity(intent);
    }

>>>>>>> refs/remotes/rcaporino/master
}

