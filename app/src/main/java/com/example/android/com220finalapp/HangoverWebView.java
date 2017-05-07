package com.example.android.com220finalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Danielle on 4/30/2017.
 */

public class HangoverWebView extends AppCompatActivity {


    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hangover_webview);

        mWebView = (WebView) findViewById(R.id.hangover_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("http://content.time.com/time/specials/packages/article/0,28804,2039990_2039991_2040041,00.html");





    }






}
