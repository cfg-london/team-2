package com.example.bbia.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener
{
    private LocationManager locationManager;
    private String provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            Toast.makeText(this, "prov obtained",
                    Toast.LENGTH_SHORT).show();
            onLocationChanged(location);
        } else {
            Toast.makeText(this, "error",
                    Toast.LENGTH_SHORT).show();
            System.out.println("not available");
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider,50, 5, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            double lat = (location.getLatitude());
            double lng = (location.getLongitude());
            System.out.println("location obtained");
            Toast.makeText(this, "location obtained",
                    Toast.LENGTH_SHORT).show();
            //new HttpBackgroundWorker2("http://192.168.1.65/addPhoneEntry.php?xLocation=" + lat + "&yLocation=" + lng).execute("6");
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }



}
