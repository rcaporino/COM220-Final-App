package com.example.android.com220finalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MyDrink extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_drink);
    }

//hangover remedies webview
    public void seeRemedies(View view) {
        setContentView(R.layout.activity_hangover_webview);
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("http://content.time.com/time/specials/packages/completelist/0,29569,2039990,00.html");
    }






}
