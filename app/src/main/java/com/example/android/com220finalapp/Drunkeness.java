package com.example.android.com220finalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.*;
import service_and_storage.Drink;
import service_and_storage.Service;
import service_and_storage.User;
import service_and_storage.Meal;
import service_and_storage.Meal.MealType;

public class Drunkeness extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drunkenessui);
    }

    //Calculates and sets blood alcohol level for a user
    public void setBloodAlcohol()
    {
        User user = Service.getInstance().getUser();
        User.Gender gender = user.getGender();
        List<Drink> allDrinks = Service.getInstance().getDrinksConsumed();
        List<Drink> validDrinks = new LinkedList<>();
        double bloodAlcohol = 0.0;
        double gramsOfAlc;
        double oz;
        double proof;
        double timeDiff;
        double gc;
        long firstDrinkTime = 0;
        double weight = user.getWeight();
        double timeCheck;


        java.util.Date today = new java.util.Date();
        java.sql.Timestamp time1 = new java.sql.Timestamp(today.getTime());
        long timeNow = time1.getTime();

        if(gender == User.Gender.Male)
        {
            gc = 0.73;
        }else if(gender == User.Gender.Female)
        {
            gc = 0.66;
        }else{
            gc = 0.0;
        }

        //Itereate over allDrinks list to only take drinks that were drunk within
        //the past 12 hours;
        for(Drink drink: allDrinks)
        {
            // set timeCheck to the current time - the current elements time drank to
            // a number of hours
            timeCheck = (timeNow - drink.getTimeDrank()) / (60.0 * 60.0 * 1000.0);

            // If timeCheck is less than 12 hours then add the current element to
            // the validDrink list
            if(timeCheck < 12.0)
            {
                validDrinks.add(drink);
            }
        }

        // Iterate over validDrinks list to find the first drinks time drank
        for (Drink drink : validDrinks)
        {   // If it hasnt been set yet, set it to the first elements time in the list

            if (firstDrinkTime == 0)
            {
                firstDrinkTime = drink.getTimeDrank();

            }
            else
            {   // check if the current element's time drank is less than the first time drank,
                // set firstDrankTime to current elements time.
                if (firstDrinkTime > drink.getTimeDrank())
                {
                    firstDrinkTime = drink.getTimeDrank();
                }

            }
        }


        //Calcuate elapsed time
        timeDiff = timeNow - firstDrinkTime;

        //Iterate over validDrinks list, calculate BAC for each drink
        for(Drink drink : validDrinks)
        {

            oz = drink.getSizeInOz();
            proof = drink.getProof();

            //Converting milliseconds to hours
            timeDiff = timeDiff / (60.0 * 60.0 * 1000.0);

            gramsOfAlc = oz * (proof / 100.0) / 2.0;
            bloodAlcohol += (gramsOfAlc * 5.14) / (weight * gc) - (.015 * timeDiff);
            /*
             * Widmark formula % BAC = (A x 5.14 / W x r) – .015 x H
             * A = grams of alcohol
             * W = Weight in lbs
             * r = gender constant
             * H = hours passed
             * http://www.teamdui.com/bac-widmarks-formula/
             */
        }

//            if(Service.getInstance().getUserMealType() != null) {
//
//                if (Service.getInstance().getUserMealType() == Meal.MealType.Large) {
//                    bloodAlcohol = bloodAlcohol - 0.003;
//                } else if (Service.getInstance().getUserMealType() == Meal.MealType.Medium) {
//                    bloodAlcohol = bloodAlcohol - 0.002;
//                } else if (Service.getInstance().getUserMealType() == Meal.MealType.Small) {
//                    bloodAlcohol = bloodAlcohol - 0.001;
//                }
//            }

        Service.getInstance().setUserIntoxLevel(bloodAlcohol);
        //user.setIntoxLevel(bloodAlcohol);

    }

    public void getCurrentBAC()
    {
        User user = Service.getInstance().getUser();
        User.Gender gender = user.getGender();
        List<Drink> allDrinks = Service.getInstance().getDrinksConsumed();
        List<Drink> validDrinks = new LinkedList<>();
        double bloodAlcohol = 0.0;
        double gramsOfAlc;
        double oz;
        double proof;
        double timeDiff;
        double gc;
        long firstDrinkTime = 0;
        double weight = user.getWeight();
        double timeCheck;

        java.util.Date today = new java.util.Date();
        java.sql.Timestamp time1 = new java.sql.Timestamp(today.getTime());
        long timeNow = time1.getTime();

        if(gender == User.Gender.Male)
        {
            gc = 0.73;
        }else if(gender == User.Gender.Female)
        {
            gc = 0.66;
        }else{
            gc = 0.0;
        }

        for(Drink drink: allDrinks)
        {
            // set timeCheck to the current time - the current elements time drank to
            // a number of hours
            timeCheck = (timeNow - drink.getTimeDrank()) / (60.0 * 60.0 * 1000.0);

            // If timeCheck is less than 12 hours then add the current element to
            // the validDrink list
            if(timeCheck < 12.0)
            {
                validDrinks.add(drink);
            }
        }

        Drink water = new Drink("Water", 1, 0, timeNow);
        validDrinks.add(water);

        for (Drink drink : validDrinks)
        {   // If it hasnt been set yet, set it to the first elements time in the list

            if (firstDrinkTime == 0)
            {
                firstDrinkTime = drink.getTimeDrank();

            }
            else
            {   // check if the current element's time drank is less than the first time drank,
                // set firstDrankTime to current elements time.
                if (firstDrinkTime > drink.getTimeDrank())
                {
                    firstDrinkTime = drink.getTimeDrank();
                }

            }
        }

        timeDiff = timeNow - firstDrinkTime;

        for(Drink drink : validDrinks)
        {

            oz = drink.getSizeInOz();
            proof = drink.getProof();

            //Converting milliseconds to hours
            timeDiff = timeDiff / (60.0 * 60.0 * 1000.0);

            gramsOfAlc = oz * (proof / 100.0) / 2.0;
            bloodAlcohol += (gramsOfAlc * 5.14) / (weight * gc) - (.015 * timeDiff);
            /*
             * Widmark formula % BAC = (A x 5.14 / W x r) – .015 x H
             * A = grams of alcohol
             * W = Weight in lbs
             * r = gender constant
             * H = hours passed
             * http://www.teamdui.com/bac-widmarks-formula/
             */
        }


        Service.getInstance().setUserIntoxLevel(bloodAlcohol);

    }

}