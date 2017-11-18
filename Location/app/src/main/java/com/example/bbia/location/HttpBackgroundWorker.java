package com.example.bbia.location;

/**
 * Created by bbia on 11/18/2017.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.io.StreamCorruptedException;
import java.net.URL;

import java.net.URLConnection;

import static android.content.Context.NOTIFICATION_SERVICE;


/**

 * Created by biabe on 10/1/2016.

 */



public class HttpBackgroundWorker extends AsyncTask<String,Void,String> {

    private Context _mainContext;
    private SharedPreferences sharedpreferences;
    public  HttpBackgroundWorker(Context context)
    {
        _mainContext=context;
        sharedpreferences=context.getSharedPreferences("notification",Context.MODE_PRIVATE);
    }
    String loadNotificationText(String text)
    {
        return sharedpreferences.getString(text,"Notification number "+text);
    }
    public  void showNotification(String text)
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(_mainContext);
        Uri notificationSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(notificationSound);
        mBuilder.setSmallIcon(R.drawable.visa);
        mBuilder.setContentTitle(loadNotificationText(text));
        mBuilder.setContentText("Hi, This is Android Notification Detail!");

        NotificationManager mNotificationManager = (NotificationManager)_mainContext.getSystemService(NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());

    }
    protected String doInBackground(String... urls)

    {
        System.out.print(urls[0]);
        return "";

        try

        {

            String url="http://192.168.0.106/notif";

            System.out.println(url);

            URL oracle = new URL(url);



            URLConnection yc = oracle.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(

                    yc.getInputStream()));
            String text="";
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                text=text+inputLine;
            System.out.println(text);

            in.close();
            if(!text.equals("0"))
                showNotification(text);

        }

        catch (Exception ee) {

            System.out.println(ee);

        }

        return  "";

    }

}