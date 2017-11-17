package com.example.cosmin.linkageplus;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        EditText editTextName = (EditText) findViewById(R.id.nameInput);
        EditText editTextPhone = (EditText) findViewById(R.id.phoneNumberInput);
        Button _submitButton = (Button) findViewById(R.id.submitButton);

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

}
