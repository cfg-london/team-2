package com.example.cosmin.linkageplus;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    private ImageButton _crisisAlert, _healthAlert, _homeAlert, _transportAlert, _foodAlert, _moneyAlert, _otherAlert, _lifeAlert;
    private int[] buttonClicks = new int[8];
    private ImageButton _checkButton;
    private Button _englishButton, _bengaliButton;
    private TextView _home, _finance, _food, _life, _health, _crisis, _transport, _other;
    Map<Integer, String> buttonMap= new HashMap<Integer, String>();
    /*button 0 ->crisis, 1 -> health, 2-> home, 3-> transport, 4->food, 5->money, 6-> other, 7->life /*

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buttonMap.put(0,"crisis");
        buttonMap.put(1,"health");
        buttonMap.put(2,"home");
        buttonMap.put(3,"transport");
        buttonMap.put(4,"food");
        buttonMap.put(5,"finance");
        buttonMap.put(6,"other");
        buttonMap.put(7,"life_planning");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _home = (TextView) findViewById(R.id.home);
        _finance = (TextView) findViewById(R.id.finance);
        _food = (TextView) findViewById(R.id.food);
        _life = (TextView) findViewById(R.id.life);
        _health = (TextView) findViewById(R.id.health);
        _crisis = (TextView) findViewById(R.id.crisis);
        _transport = (TextView) findViewById(R.id.transport);
        _other = (TextView) findViewById(R.id.other);

        _crisisAlert = (ImageButton) findViewById(R.id.crisisAlert);
        //btn.setImageResource(R.drawable.actions_record);
        _crisisAlert.setOnClickListener(view -> {
            buttonClicks[0] = (buttonClicks[0]+1) %3;
            if (buttonClicks[0]==0)
                _crisisAlert.setImageResource(R.drawable.crisis);
            else if (buttonClicks[0]==1)
                _crisisAlert.setImageResource(R.drawable.crisis_med);
            else if (buttonClicks[0]==2)
                _crisisAlert.setImageResource(R.drawable.crisis_urgent);
        });

        _healthAlert = (ImageButton) findViewById(R.id.healthAlert);
        //btn.setImageResource(R.drawable.actions_record);
        _healthAlert.setOnClickListener(view -> {
            buttonClicks[1] = (buttonClicks[1]+1) %3;
            if (buttonClicks[1]==0)
                _healthAlert.setImageResource(R.drawable.health);
            else if (buttonClicks[1]==1)
                _healthAlert.setImageResource(R.drawable.health_med);
            else if (buttonClicks[1]==2)
                _healthAlert.setImageResource(R.drawable.health_urgent);
        });

        _homeAlert = (ImageButton) findViewById(R.id.homeAlert);
        //btn.setImageResource(R.drawable.actions_record);
        _homeAlert.setOnClickListener(view -> {
            buttonClicks[2] = (buttonClicks[2]+1) %3;
            if (buttonClicks[2]==0)
                _homeAlert.setImageResource(R.drawable.home);
            else if (buttonClicks[2]==1)
                _homeAlert.setImageResource(R.drawable.home_med);
            else if (buttonClicks[2]==2)
                _homeAlert.setImageResource(R.drawable.home_urgent);
        });

        _transportAlert = (ImageButton) findViewById(R.id.transportAlert);
        //btn.setImageResource(R.drawable.actions_record);
        _transportAlert.setOnClickListener(view -> {
            buttonClicks[3] = (buttonClicks[3]+1) %3;
            if (buttonClicks[3]==0)
                _transportAlert.setImageResource(R.drawable.transport);
            else if (buttonClicks[3]==1)
                _transportAlert.setImageResource(R.drawable.transport_med);
            else if (buttonClicks[3]==2)
                _transportAlert.setImageResource(R.drawable.transport_urgent);
        });

        _foodAlert = (ImageButton) findViewById(R.id.foodAlert);
        //btn.setImageResource(R.drawable.actions_record);
        _foodAlert.setOnClickListener(view -> {
            buttonClicks[4] = (buttonClicks[4]+1) %3;
            if (buttonClicks[4]==0)
                _foodAlert.setImageResource(R.drawable.food);
            else if (buttonClicks[4]==1)
                _foodAlert.setImageResource(R.drawable.food_med);
            else if (buttonClicks[4]==2)
                _foodAlert.setImageResource(R.drawable.food_urgent);
        });

        _moneyAlert = (ImageButton) findViewById(R.id.moneyAlert);
        //btn.setImageResource(R.drawable.actions_record);
        _moneyAlert.setOnClickListener(view -> {
            buttonClicks[5] = (buttonClicks[5]+1) %3;
            if (buttonClicks[5]==0)
                _moneyAlert.setImageResource(R.drawable.finance);
            else if (buttonClicks[5]==1)
                _moneyAlert.setImageResource(R.drawable.finance_med);
            else if (buttonClicks[5]==2)
                _moneyAlert.setImageResource(R.drawable.finance_urgent);
        });

        _otherAlert = (ImageButton) findViewById(R.id.otherAlert);
        //btn.setImageResource(R.drawable.actions_record);
        _otherAlert.setOnClickListener(view -> {
            buttonClicks[6] = (buttonClicks[6]+1) %3;
            if (buttonClicks[6]==0)
                _otherAlert.setImageResource(R.drawable.other);
            else if (buttonClicks[6]==1)
                _otherAlert.setImageResource(R.drawable.other_med);
            else if (buttonClicks[6]==2)
                _otherAlert.setImageResource(R.drawable.other_urgent);
        });

        _lifeAlert = (ImageButton) findViewById(R.id.lifeAlert);
        //btn.setImageResource(R.drawable.actions_record);
        _lifeAlert.setOnClickListener(view -> {
            buttonClicks[7] = (buttonClicks[7]+1) %3;
            if (buttonClicks[7]==0)
                _lifeAlert.setImageResource(R.drawable.life_planning);
            else if (buttonClicks[7]==1)
                _lifeAlert.setImageResource(R.drawable.life_planning_med);
            else if (buttonClicks[7]==2)
                _lifeAlert.setImageResource(R.drawable.life_planning_urgent);
        });

        _checkButton = (ImageButton) findViewById(R.id.confirmationButton);
        _checkButton.setOnClickListener((e) -> {

            String problemMessage = "";
            int priorityInt = 0;
            // List<Problem> problemList = new ArrayList<>();
            // Problem[] problems = new Problem[8];
            String problem = "";
            String priority = "";
            for (int i = 0; i < buttonClicks.length; i++) {
                problem += Integer.toString(buttonClicks[i]);
                priorityInt += buttonClicks[i];
            }

            priority = Integer.toString(priorityInt);
            Intent formIntent = new Intent(this, FormActivity.class);
            formIntent.putExtra("arg1", problem);
            formIntent.putExtra("arg2", priority);
            startActivity(formIntent);
        });

        _bengaliButton = (Button) findViewById(R.id.bengaliButton) ;
        _bengaliButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                _home.setText("বাড়ি");
                _finance.setText("মূলধন যোগান");
                _food.setText("খাদ্য");
                _life.setText("জীবন পরিকল্পনা");
                _health.setText("স্বাস্থ্য");
                _crisis.setText("সঙ্কট");
                _transport.setText("পরিবহন");
                _other.setText("অন্যান্য");
            }
        });

        _englishButton = (Button) findViewById(R.id.englishButton) ;
        _englishButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                _home.setText("Home");
                _finance.setText("Finance");
                _food.setText("Food");
                _life.setText("Life Planning");
                _health.setText("Health");
                _crisis.setText("Crisis");
                _transport.setText("Transport");
                _other.setText("Other");
            }
        });

    }

    private ImageButton getClickedImageButton(int index) {
        switch (index) {
            case 0 : return _crisisAlert;
            case 1 : return _healthAlert;
            case 2 : return _homeAlert;
            case 3 : return _transportAlert;
            case 4 : return _foodAlert;
            case 5 : return _moneyAlert;
            case 6 : return _otherAlert;
            default : return _lifeAlert;
        }
    }

    private String getClickedImageName(int index) {
        switch (index) {
            case 0 : return "Crisis Alert";
            case 1 : return "Health Alert";
            case 2 : return "Home Alert";
            case 3 : return "Transport Alert";
            case 4 : return "Food Alert";
            case 5 : return "Money Alert";
            case 6 : return "Other Alert";
            default : return "Life Alert";
        }
    }
}
