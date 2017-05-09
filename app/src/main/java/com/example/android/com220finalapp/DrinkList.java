package com.example.android.com220finalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.opengl.EGLExt;
import android.text.AndroidCharacter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkList extends AppCompatActivity {
    private SearchView srchDrinks;
    private ListView lstDrink;
    private ArrayAdapter drinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);
    }

    public class drinkSearch extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_drink_list);
            findViews();
            drinkAdapter = new ArrayAdapter(drinkSearch.this, R.layout.activity_drink_list);
            lstDrink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(drinkSearch.this, ((TextView)view).getText().toString(), Toast.LENGTH_LONG).show();
                    Drink drink = (Drink) drinkAdapter.getItem(position);
                    Toast.makeText(drinkSearch.this, drink.getName() + " ####### " + drink.randomNumber, Toast.LENGTH_LONG).show();
                }
            });
            srchDrinks.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(drinkSearch.this, query, Toast.LENGTH_LONG).show();
                    lstDrink.setAdapter(drinkAdapter);
                    drinkAdapter.clear();
                    for (int i = 0; i < 100; i++) {
                        drinkAdapter.add(new Drink(query + " " + i, i));
                    }
                    drinkAdapter.notifyDataSetInvalidated();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        private void findViews() {
            //srchDrinks = (SearchView) findViewById(R.id.srchDrinks);
            //lstDrink = (ListView) findViewById(R.id.lstDrink);
        }
    }
}