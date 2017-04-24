//package com.example.android.com220drunkapp;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//
//public class MainActivity extends AppCompatActivity {
//    drunkeness t1 = new drunkeness();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.drunkenessui);
//
//
//    }
//
//    public void start(View view)
//    {
//        t1.go();
//    }
//}
package com.example.android.com220drunkapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class drunkeness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drunkenessui);
    }

    int numDrinks = 0;
    double highestBAC = 0.0;
    double currentBAC = 0.0;
    double newBAC = 0;
    double r = 0;
    double n = 0.789;
    double poundsToGrams = 453.59237;
    double time = 0;
    double gramsOfAlc = 0;
    double weightInGrams = 0;
    double gc = 0;
    double rawNum = 0;
    String gender = "";

    public void start(View view)

    {
        numDrinks ++;

        EditText weight = (EditText) findViewById(R.id.weight);
        String weightValue = weight.getText().toString();
        double w = Double.parseDouble(weightValue);

        EditText percent = (EditText) findViewById(R.id.alcohol_percent);
        String percentValue = percent.getText().toString();
        double ap = Double.parseDouble(percentValue);

        EditText ounces = (EditText) findViewById(R.id.ounces);
        String ouncesValue = ounces.getText().toString();
        double oz = Double.parseDouble(ouncesValue);

        EditText numDrinks = (EditText) findViewById(R.id.num_d);
        String numDrinksValue = numDrinks.getText().toString();
        double nd = Double.parseDouble(numDrinksValue);

        EditText hours = (EditText) findViewById(R.id.hours);
        String hoursValue = hours.getText().toString();
        double h = Double.parseDouble(hoursValue);

//Spinner gend = (Spinner) findViewById(R.id.gender);
//ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.gender_choice));
//dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//gend.setAdapter(dataAdapter);
//gender = String.valueOf(gend.getSelectedItem());

        radioCheck();
        calcBAC(w, ap, oz, nd, h);
        setTextViews();;
        System.out.println(currentBAC);

    }

    public void radioCheck() {
        RadioButton male = (RadioButton) findViewById(R.id.male);
        RadioButton female = (RadioButton) findViewById(R.id.female);
        boolean m = male.isChecked();
        boolean f = female.isChecked();
        if (m == true) {
            gender = "male";
            gc = 0.73;
        } else if (f == true) {
            gender = "female";
            gc = 0.66;
        }
    }

    public void calcBAC(double weight, double alcPercent, double alcOunces, double numDrinks, double hours)
    {

//            gramsOfAlc = alcOunces * alcPercent * n;
//            weightInGrams = weight * poundsToGrams;
//            r = weightInGrams * gc;
//            rawNum = gramsOfAlc / r;
//            newBAC = rawNum * 100;
//            currentBAC = newBAC + currentBAC;


        gramsOfAlc = (numDrinks) * (alcOunces) * (alcPercent/100);
        currentBAC = ((gramsOfAlc * 5.14) / (weight * gc)) - (.015 * hours);

        if(currentBAC > highestBAC)
            highestBAC = currentBAC;


    }

    public void setTextViews()
    {
        TextView drinkNumber = (TextView) findViewById(R.id.num_drink);
        drinkNumber.setText("" + numDrinks);

        TextView timeSinceLast = (TextView) findViewById(R.id.timer);

        TextView record = (TextView) findViewById(R.id.highest_bac);
        record.setText("" + highestBAC);

        TextView current = (TextView) findViewById(R.id.current_bac);
        current.setText("" + currentBAC);
    }
}