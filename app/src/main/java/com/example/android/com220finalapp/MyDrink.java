package com.example.android.com220finalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import service_and_storage.Drink;
import service_and_storage.Service;

public class MyDrink extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_drink);

        double BAC = Service.getInstance().getUserIntoxLevel();
        String formatBAC = myFormatter.format(BAC);

        TextView displayBAC = (TextView) findViewById(R.id.bac);
        displayBAC.setText(formatBAC);

    }

    List<Drink> drinkList = Service.getInstance().getDrinksConsumed();

    DecimalFormat myFormatter = new DecimalFormat("#.###");

    public void addButton(View v)
    {
        String drinkName;
        double drinkSizeInOz;
        double drinkProof;

        java.util.Date today = new java.util.Date();
        java.sql.Timestamp time1 = new java.sql.Timestamp(today.getTime());
        long timeDrank = time1.getTime();

        EditText name = (EditText) findViewById(R.id.drink_name);
        drinkName = name.getText().toString();
        try {
            EditText proof = (EditText) findViewById(R.id.proof);
            drinkProof = Double.parseDouble(proof.getText().toString());

            EditText drinkOz = (EditText) findViewById(R.id.ozs);
            drinkSizeInOz = Double.parseDouble(drinkOz.getText().toString());

            addDrinkToList(drinkName, drinkSizeInOz, drinkProof, timeDrank);

            Drunkeness getBAC = new Drunkeness();
            getBAC.setBloodAlcohol();

            double BAC = Service.getInstance().getUserIntoxLevel();
            String formatBAC = myFormatter.format(BAC);

            TextView displayBAC = (TextView) findViewById(R.id.bac);
            displayBAC.setText(formatBAC);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addDrinkToList(String drinkName, double drinkSizeInOz, double drinkProof, long timeDrank)
    {
        Drink drink = new Drink(drinkName, drinkSizeInOz, drinkProof, timeDrank);
        drinkList.add(drink);

    }

    public void mainMenu(View view)
    {
        Intent intent = new Intent(MyDrink.this,MainActivity.class);
        startActivity(intent);
    }

    public void getCurrentBAC(View view)
    {
        Drunkeness currentBAC = new Drunkeness();
        currentBAC.getCurrentBAC();

        double BAC = Service.getInstance().getUserIntoxLevel();
        String formatBAC = myFormatter.format(BAC);

        TextView displayBAC = (TextView) findViewById(R.id.bac);
        displayBAC.setText(formatBAC);
    }

    public void clearBAC(View view)
    {
        Service.getInstance().setUserIntoxLevel(0.0);
        double BAC = Service.getInstance().getUserIntoxLevel();

        TextView displayBAC = (TextView) findViewById(R.id.bac);
        displayBAC.setText(Double.toString(BAC));
    }
}
