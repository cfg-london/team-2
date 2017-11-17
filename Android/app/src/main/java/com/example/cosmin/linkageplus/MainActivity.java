package com.example.cosmin.linkageplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    Button formActivityButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formActivityButton = (Button) findViewById(R.id.formActivityButton);
        formActivityButton.setOnClickListener((e) ->{
            Intent formActivity = new Intent(getApplicationContext(), FormActivity.class);
            startActivity(formActivity);
        });
    }
}
