package com.example.android.com220finalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;

import service_and_storage.DataCollection;
import service_and_storage.Meal;
import service_and_storage.Service;
import service_and_storage.User;

public class Eating extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating) ;

        final Button savebutton = (Button) findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save();
            }
        });

        final Button menubutton = (Button) findViewById(R.id.menuButton);
        menubutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu();
            }
        });



    }

    public void save()
    {

        //RadioGroup radioButtonGroup = (RadioGroup) findViewById(R.id.radiogroup);
        //int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();

        try {


            EditText hourField = (EditText) findViewById(R.id.houramount);
            String hours = hourField.getText().toString();
            int hour = Integer.parseInt(hours);
            long timeEaten = new Date().getTime() - (hour * 60 * 60 * 1000);
            //Service.getInstance().setUserMealTimeEaten(timeEaten);


            RadioButton largeMeal = (RadioButton) findViewById(R.id.largemeal);
            RadioButton mediumMeal = (RadioButton) findViewById(R.id.mediummeal);
            RadioButton smallMeal = (RadioButton) findViewById(R.id.smallmeal);

            if (largeMeal.isChecked()) {
                //set large
                //System.out.println("IN LARGE");
                //service.setUserMealType(Meal.MealType.Large);
                Service.getInstance().setUserMeal(Meal.MealType.Large, timeEaten);
//          Meal MealObj = new Meal(Meal.MealType.Large, timeEaten);
//          user.setMeal(MealObj);

            }
            else if (mediumMeal.isChecked()) {
                //set medium
                //System.out.println("IN Medium");
                //service.setUserMealType(Meal.MealType.Medium);
                Service.getInstance().setUserMeal(Meal.MealType.Medium, timeEaten);
//            Meal MealObj = new Meal(Meal.MealType.Medium, timeEaten);
//            user.setMeal(MealObj);
            }
            else if (smallMeal.isChecked()) {
                //set small
                //System.out.println("IN small");
                //service.setUserMealType(Meal.MealType.Small);
                Service.getInstance().setUserMeal(Meal.MealType.Small, timeEaten);
//            Meal MealObj = new Meal(Meal.MealType.Small, timeEaten);
//            user.setMeal(MealObj);
            }
            else
            {
                Toast.makeText(this, "You forgot to chose a meal type", Toast.LENGTH_SHORT).show();
            }
        }
        catch(NumberFormatException e)
        {
            Toast.makeText(this, "You forgot to add number of hours", Toast.LENGTH_SHORT).show();
        }
    }



        //System.out.println(radioButtonID);
        //System.out.println(MealObj.getMealType());
        //System.out.println(MealObj.getTimeEaten());


    public void menu()
    {
        Intent intent = new Intent(Eating.this, MainActivity.class);
        startActivity(intent);

    }


}
