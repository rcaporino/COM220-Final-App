package com.example.android.com220finalapp;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import com.example.android.com220finalapp.Drunkeness;
import service_and_storage.Drink;
import service_and_storage.User;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{

    @Test
    public void setBloodAlcoholTest_success()
    {
        Drunkeness test1 = new Drunkeness();

        java.util.Date today = new java.util.Date();
        java.sql.Timestamp time1 = java.sql.Timestamp.valueOf("2017-05-05 14:00:10");
        long timeNow = time1.getTime();

        java.sql.Timestamp ts1 = java.sql.Timestamp.valueOf("2017-05-04 13:03:10");
        java.sql.Timestamp ts2 = java.sql.Timestamp.valueOf("2017-05-05 13:43:10");
        java.sql.Timestamp ts3 = java.sql.Timestamp.valueOf("2017-05-05 14:13:10");
        java.sql.Timestamp ts4 = java.sql.Timestamp.valueOf("2017-05-05 14:43:09");

        long time10 = ts1.getTime();
        long time2 = ts2.getTime();
        long time3 = ts3.getTime();
        long time4 = ts4.getTime();

        User user = new User(72, 200, User.Gender.Male);
        List<Drink> drinkList = new LinkedList<>();
        // Add some drinks to drinkList
        Drink drink1 = new Drink("troll1", 1.5, 80.0, time10);
        Drink drink2 = new Drink("troll2", 1.5, 80.0, time2);
        Drink drink3 = new Drink("troll3", 1.5, 80.0, time3);
        Drink drink4 = new Drink("troll4", 1.5, 80.0, time4);
        drinkList.add(drink1);
        drinkList.add(drink2);
        drinkList.add(drink3);
        drinkList.add(drink4);
        //call function with test data
     //   test1.setBloodAlcohol(user, drinkList);
        //do assertions
        assertEquals(.048, user.getIntoxLevel(), .001);
    }
}