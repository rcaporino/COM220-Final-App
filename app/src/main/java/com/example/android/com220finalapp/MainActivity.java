package com.example.android.com220finalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    boolean ImMAle;
    boolean ImFemale;
    private ViewSwitcher simpleViewSwitcher;
    ImageButton btnChange;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accsetupact);

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


    public void done(View view) {
        EditText hight = (EditText) findViewById(R.id.etHight);
        String shight = hight.getText().toString();
        double HightValue = Double.parseDouble(shight);
        EditText Wight = (EditText) findViewById(R.id.etWidth);
        String sWight = Wight.getText().toString();
        double WightValue = Double.parseDouble(sWight);
        RadioButton M = (RadioButton) findViewById(R.id.rbMale);
        boolean men=  M.isChecked();
        RadioButton F = (RadioButton) findViewById(R.id.rbFemale);
        boolean fmen= F.isChecked();
        if (men== true)
            ImMAle = Boolean.TRUE;
        if (fmen==true)
            ImFemale = Boolean.TRUE;


    }

}

