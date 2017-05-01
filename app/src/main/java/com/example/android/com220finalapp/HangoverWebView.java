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
        mWebView.loadUrl("https://content.time.com/time/specials/packages/completelist/0,29569,2039990,00.html");





    }






}
