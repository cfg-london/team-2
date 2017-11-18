package com.example.cosmin.linkageplus;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class FormActivity extends AppCompatActivity implements LocationListener {

    private EditText editTextName;
    private EditText editTextPhone;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTextName = (EditText) findViewById(R.id.nameInput);
        editTextPhone = (EditText) findViewById(R.id.phoneNumberInput);
        Button _submitButton = (Button) findViewById(R.id.submitButton);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
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
        _submitButton.setOnClickListener((e) -> {
            String name = editTextName.getText().toString();
            String phoneNumber = editTextPhone.getText().toString();

            System.out.println("NameText: " + name);
            System.out.println("PhoneNumber " + phoneNumber);

            final ProgressDialog progressDialog = new ProgressDialog(FormActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Sending message");
            progressDialog.show();

            new android.os.Handler().postDelayed(() -> {
                makeRequest(name, phoneNumber);
                progressDialog.dismiss();
            }, 3000);

        });
    }

    private void makeRequest(String name, String phoneNumber) {
        RequestQueue queue = Volley.newRequestQueue(FormActivity.this);

        String url = "http://192.168.137.1/addUser.php?Name=" + name + "&Contact=" + phoneNumber + "&Problem=heat&Priority=9";
        System.out.println("\n\n\nURL: " + url);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    System.out.println("\n\nRESPONSE: " + response);
                    final String successResponse = "1";
                    final String zeroResponse = "0";
                    final String parsedResponse = response.replaceAll(" ","");
                    if (parsedResponse.equals(successResponse)) {
                        System.out.println("success");
                        onRequestSuccess();
                    } else if (parsedResponse.equals(zeroResponse)) {
                        System.out.println("Zero response!");
                        onRequestFailed();
                    } else {
                        System.out.println("failed");
                        onRequestFailed();
                    }
                },
                error -> {
                    System.err.println("Request ERROR");
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Username", name);
                params.put("Password", phoneNumber);

                return params;
            }
        };
        queue.add(postRequest);
    }

    private void onRequestSuccess() {
        finish();
    }

    private void onRequestFailed() {
        Toast.makeText(getBaseContext(), "Failed to send message!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            double lat = (location.getLatitude());
            double lng = (location.getLongitude());
            System.out.println("location obtained");
            Toast.makeText(this, "location obtained",
                    Toast.LENGTH_SHORT).show();
            //new HttpBackgroundWorker("http://192.168.1.65/addPhoneEntry.php?xLocation=" + lat + "&yLocation=" + lng).execute("6");
        }
        catch (Exception e) {
            Toast.makeText(getBaseContext(), "Failed to GET LOCATION!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
