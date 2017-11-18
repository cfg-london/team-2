package com.example.cosmin.linkageplus;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FormActivity extends AppCompatActivity implements LocationListener {

    private EditText editTextName;
    private EditText editTextPhone;
    private LocationManager locationManager;
    private String provider;
    private CheckBox consentBox;
    private boolean checkedStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTextName = (EditText) findViewById(R.id.nameInput);
        editTextPhone = (EditText) findViewById(R.id.phoneNumberInput);
        Button _submitButton = (Button) findViewById(R.id.submitButton);
        consentBox = (CheckBox) findViewById(R.id.consentBox);

        String problem = getIntent().getExtras().getString("arg1");
        String priority = getIntent().getExtras().getString("arg2");

        consentBox.setOnClickListener(e -> {
            if (consentBox.isChecked())
                checkedStatus = true;
            else
                checkedStatus = false;

        });

        _submitButton.setOnClickListener((e) -> {
            String name = editTextName.getText().toString();
            String phoneNumber = editTextPhone.getText().toString();

            System.out.println("NameText: " + name);
            System.out.println("PhoneNumber " + phoneNumber);

            if (checkedStatus)
                Toast.makeText(getBaseContext(), "You allowed us to have your location!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getBaseContext(), "You posted anonymously!", Toast.LENGTH_LONG).show();

            final ProgressDialog progressDialog = new ProgressDialog(FormActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Sending message");
            progressDialog.show();

            new android.os.Handler().postDelayed(() -> {
                makeRequest(name, phoneNumber, problem, priority);
                progressDialog.dismiss();
            }, 3000);

        });
    }

    private void makeRequest(String name, String phoneNumber, String problem, String priority) {
        RequestQueue queue = Volley.newRequestQueue(FormActivity.this);
        //http://192.168.137.1/addUser.php?Name=TestName&Contact=0745750542&Problem=heat&Priority=9&XLoc=0&YLoc=0
        //String priority = "9"; // TODO SPECIFIC type


        // getting the location of the user
        double[] location = getGPS();
        String latitude = Double.toString(location[0]);
        String longitude = Double.toString(location[1]);

        String url = "http://192.168.137.1/addUser2.php?Name=" + name + "&Contact=" + phoneNumber + "&Problem=" + problem + "&Priority=" + priority + "&XLoc=" + latitude + "&YLoc=" + longitude;
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
                params.put("name", name);
                params.put("phonenumber", phoneNumber);

                return params;
            }
        };
        queue.add(postRequest);
    }

    private void onRequestSuccess() {
        Toast.makeText(getBaseContext(), "Request sent successful!", Toast.LENGTH_LONG).show();
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

    @SuppressLint("MissingPermission") //case it doesn't have it it will get location from the internet
    private double[] getGPS() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
        Location l = null;

        for (int i=providers.size()-1; i>=0; i--) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) break;
        }

        double[] gps = new double[2];
        if (l != null) {
            gps[0] = l.getLatitude();
            gps[1] = l.getLongitude();
        }
        return gps;
    }
}
