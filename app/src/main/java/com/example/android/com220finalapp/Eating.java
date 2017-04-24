package com.example.android.com220finalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;

public class Eating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating) ;
    }

    public void save()
    {
        RadioGroup radioButtonGroup = (RadioGroup) findViewById(R.id.radiogroup);
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        Meal MealObj = new Meal();

        if(radioButtonID == 1)
        {
            //set large
            MealObj.setMealType(Large);
        }
        if(radioButtonID == 2)
        {
            //set medium
            MealObj.setMealType(Medium);
        }
        if(radioButtonID == 3)
        {
            //set small
            MealObj.setMealType(Small);
        }

        EditText hourField = (EditText) findViewById(R.id.houramount);
        String hours = hourField.getText().toString();
        int hour = Integer.parseInt(hours);
        long timeEaten = new Date().getTime() - (hour * 60 * 60 * 1000);
    }

    public void menu()
    {

    }


}
