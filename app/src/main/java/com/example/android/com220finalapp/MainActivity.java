package com.example.android.com220finalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.com220finalapp.R;
import com.example.android.com220finalapp.cameraclass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callCameraClass(View view)
    {
        Intent intent = new Intent(this, cameraclass.class);
        startActivity(intent);
    }

}
