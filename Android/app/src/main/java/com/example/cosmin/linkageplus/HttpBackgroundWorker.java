package com.example.cosmin.linkageplus;

import android.os.AsyncTask;
import android.widget.Toast;


import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
public class HttpBackgroundWorker extends AsyncTask<String,Void,String> {
    private String _url;

    public HttpBackgroundWorker(String url) {
        _url=url;
    }

    protected String doInBackground(String... urls) {
        try {
            String url = _url;
            System.out.println(url);
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return  "";
    }

}