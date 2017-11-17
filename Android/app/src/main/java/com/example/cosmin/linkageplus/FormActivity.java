package com.example.cosmin.linkageplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

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

            new android.os.Handler().postDelayed(() -> {
                makeRequest(name, phoneNumber);
            }, 3000);

        });
    }

    private void makeRequest(String name, String phoneNumber) {
        RequestQueue queue = Volley.newRequestQueue(FormActivity.this);

        // createing request and waiting for response
        //String url = "http://192.168.1.104/checkUser.php?Email=" + emailAddress + "&Password=" + hashedPassword;
        String url = ""; 
        System.out.println("\n\n\nURL: " + url);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    System.out.println("\n\nRESPONSE: " + response);
                    final String successResponse = "1";
                    final String zeroResponse = "0";
                    if (response.equals(successResponse)) {
                        System.out.println("success");
                        // TODO on success
                    } else if (response.equals(zeroResponse)) {
                        System.out.println("Zero response!");
                        // TODO  on failed
                    } else {
                        System.out.println("failed");
                        // TODO on other
                    }
                },
                error -> {
                    System.err.println("LOGIN ERROR");
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

}
