package com.example.android.com220finalapp;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;


        import org.w3c.dom.Text;

        import java.sql.Timestamp;
        import java.text.SimpleDateFormat;
        import java.util.*;

        import service_and_storage.DataCollection;
        import service_and_storage.Drink;
        import service_and_storage.User;


public class Drunkeness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drunkenessui);
    }

//    int numDrinks = 0;
//    double highestBAC = 0.0;
//    double currentBAC = 0.0;
//    double newBAC = 0;
//    double r = 0;
//    double n = 0.789;
//    double poundsToGrams = 453.59237;
//    double time = 0;
//    double weightInGrams = 0;
//    double gc = 0;
//    double rawNum = 0;


    DataCollection data = new DataCollection();



    public void start(View view)

    {

//
//        EditText weight = (EditText) findViewById(R.id.weight);
//        String weightValue = weight.getText().toString();
//        double w = Double.parseDouble(weightValue);
//
//        EditText percent = (EditText) findViewById(R.id.alcohol_percent);
//        String percentValue = percent.getText().toString();
//        double ap = Double.parseDouble(percentValue);
//
//        EditText ounces = (EditText) findViewById(R.id.ounces);
//        String ouncesValue = ounces.getText().toString();
//        double oz = Double.parseDouble(ouncesValue);
//
//        EditText numDrinks = (EditText) findViewById(R.id.num_d);
//        String numDrinksValue = numDrinks.getText().toString();
//        double nd = Double.parseDouble(numDrinksValue);
//
//        EditText hours = (EditText) findViewById(R.id.hours);
//        String hoursValue = hours.getText().toString();
//        double h = Double.parseDouble(hoursValue);


//        radioCheck();
//        calcBAC(w, ap, oz, nd, h);
//        setTextViews();;

    }
//
//    public void radioCheck() {
//        RadioButton male = (RadioButton) findViewById(R.id.male);
//        RadioButton female = (RadioButton) findViewById(R.id.female);
//        boolean m = male.isChecked();
//        boolean f = female.isChecked();
//        if (m == true) {
//            gc = 0.73;
//        } else if (f == true) {
//            gc = 0.66;
//        }
//    }

//    public void calcBAC(double weight, double alcPercent, double alcOunces, double numDrinks, double hours)
//    {
//
//
//                        //1             ounces        percent = proof/2
//        gramsOfAlc = (numDrinks) * (alcOunces) * (alcPercent/100);
//        currentBAC = ((gramsOfAlc * 5.14) / (weight * gc)) - (.015 * hours);
//
//        if(currentBAC > highestBAC)
//            highestBAC = currentBAC;
//
//
//    }

    public void setBloodAlcohol()
    {

//        java.util.Date today = new java.util.Date();
//        java.sql.Timestamp time1 = new java.sql.Timestamp(today.getTime());
//
//        java.sql.Timestamp ts2 = java.sql.Timestamp.valueOf("2017-05-03 16:15:10");
//
//        long timeNow = time1.getTime();
//        long timeTest = ts2.getTime();
//
//        long diff = timeNow - timeTest;
//        long diffhours = diff / (60 * 60 * 1000);
//
//        System.out.println(diffhours);

        java.util.Date today = new java.util.Date();
        java.sql.Timestamp time1 = new java.sql.Timestamp(today.getTime());
        long timeNow = time1.getTime();
        System.out.println(timeNow);

        User user = new User();
        List<Drink> listOfDrinks;
        listOfDrinks = data.getDrinksConsumed();

        double bloodAlcohol = 0;
        double gramsOfAlc = 0;
        double weight = 0;
        double gc = 0;
        long diff = 0;
        long diffhours = 0;


        double [] oz = new double[listOfDrinks.size()];
        double [] proof = new double [listOfDrinks.size()];
        long [] timeDrank = new long[listOfDrinks.size()];



        weight = user.getWeight();

        User.Gender gender = user.getGender();
        if (gender == User.Gender.Male)
        {
            gc = 0.73;
        }else if (gender == User.Gender.Female) {
            gc = 0.66;
        }

        for(int i = 0; i < listOfDrinks.size(); i++)
        {
            oz[i] = listOfDrinks.get(i).getSizeInOz();
            proof[i] = listOfDrinks.get(i).getProof();
            timeDrank[i] = listOfDrinks.get(i).getTimeDrank();

            diff = timeNow - timeDrank[i];
            diffhours =  diff / (60 * 60 * 1000);

            gramsOfAlc = (oz[i]) * ((proof[i]/100))/(2);
            bloodAlcohol += (gramsOfAlc * 5.14) / (weight * gc) - (.015 * diffhours);
        }

        user.setIntoxLevel(bloodAlcohol);


    }

//    public void setTextViews()
//    {
//        TextView drinkNumber = (TextView) findViewById(R.id.num_drink);
//        drinkNumber.setText("" + numDrinks);
//
//        TextView timeSinceLast = (TextView) findViewById(R.id.timer);
//
//        TextView record = (TextView) findViewById(R.id.highest_bac);
//        record.setText("" + highestBAC);
//
//        TextView current = (TextView) findViewById(R.id.current_bac);
//        current.setText("" + currentBAC);
//    }
}