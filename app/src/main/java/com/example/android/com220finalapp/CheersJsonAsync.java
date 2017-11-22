package com.example.android.com220finalapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class CheersJsonAsync extends AsyncTask<String, String, ArrayList<String>> {
    //TODO input the URL
    private String url = "";
    private BufferedReader JSONBuffer;

    public CheersJsonAsync(String newURL){
        url = newURL;
    }
    public CheersJsonAsync (){}
    public CheersJsonAsync(String url, Handoff handoff){
        this.url = url;
        this.handoff = handoff;
    }

    public interface Handoff{
        void backToMainActivity(ArrayList<String> s);
    }
    public Handoff handoff = null;

    public void setURL( String newURL){
        url = newURL;
    }

    public String getURL(){
        return url;
    }

    private String callURL(String url) throws Exception{
        BufferedReader getJsonData = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"));
        String line = "";

        StringBuilder sb = new StringBuilder();
        while ((line = getJsonData.readLine()) !=null){
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        Log.i("BakerCALL", "Attempt Internet Call");
        try {
            Log.i("BAKERCALL", "CALLING");
            String json = callURL(url);
            json=json.replaceAll("\\\\","");
            json=json.replaceAll("\""+"\\{","\\{");
            json=json.replaceAll("\\}"+"\"","\\}");

            Log.i("BakerJSONRAW", json);
            ArrayList<String> jsonFriend = new ArrayList<String>();
            if(!json.equals("\"No Cheers Found\"")){
                JSONObject urlRet = new JSONObject(json);
                JSONObject val = urlRet.getJSONObject("Value");

                jsonFriend.add(val.getString("name"));
                jsonFriend.add(val.getString("phone"));
            }else{
                jsonFriend.add("No Cheers");
                jsonFriend.add("No Cheers");
            }
            return jsonFriend;

        } catch (Exception e) {
            Log.e ("BakerEXCEPTION","Exception Thrown, Debug", e);
            return new ArrayList<String>();
        }
    }

    @Override
    protected void onPostExecute(ArrayList<String> friendInfo) {

        super.onPostExecute(friendInfo);
        Log.i("BakerPOST", "Attempt Post Execute");
        handoff.backToMainActivity(friendInfo);
    }
}
