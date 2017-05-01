package com.example.android.com220finalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class setup extends AppCompatActivity {

    private EditText etHight;
    private EditText etWidth;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private RadioButton rbFemale;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }


    private void findViews() {

        etHight = (EditText) findViewById(R.id.etHight);
        etWidth = (EditText) findViewById(R.id.etWidth) ;
        rgGender = (RadioGroup)findViewById(R.id.rgGender);
        rbMale = (RadioButton)findViewById(R.id.rbMale) ;
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);

    }






}
