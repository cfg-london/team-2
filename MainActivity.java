package com.example.pasca.a70version;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button _englishButton, _bengaliButton;
    private TextView _home, _finance, _food, _life, _health, _crisis, _transport, _other;
    private ImageButton _crisisAlert, _healthAlert, _homeAlert, _transportAlert, _foodAlert, _moneyAlert, _otherAlert, _lifeAlert;
    private int[] buttonClicks = new int[8];
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

        _crisisAlert = (ImageButton) findViewById(R.id.crisisAlert);
        _crisisAlert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                buttonClicks[0] = (buttonClicks[0]+1) %3;
                if (buttonClicks[0]==0)
                    _crisisAlert.setImageResource(R.drawable.crisis);
                else if (buttonClicks[0]==1)
                    _crisisAlert.setImageResource(R.drawable.crisis_med);
                else if (buttonClicks[0]==2)
                    _crisisAlert.setImageResource(R.drawable.crisis_urgent);
            } //btn.setImageResource(R.drawable.actions_record);
        });

        _healthAlert = (ImageButton) findViewById(R.id.healthAlert);
        _healthAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicks[1] = (buttonClicks[1]+1) %3;
                if (buttonClicks[1]==0)
                    _healthAlert.setImageResource(R.drawable.health);
                else if (buttonClicks[1]==1)
                    _healthAlert.setImageResource(R.drawable.health_med);
                else if (buttonClicks[1]==2)
                    _healthAlert.setImageResource(R.drawable.health_urgent);
            } //btn.setImageResource(R.drawable.actions_record);
        });

        _homeAlert = (ImageButton) findViewById(R.id.homeAlert);
        _homeAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicks[2] = (buttonClicks[2]+1) %3;
                if (buttonClicks[2]==0)
                    _homeAlert.setImageResource(R.drawable.home);
                else if (buttonClicks[2]==1)
                    _homeAlert.setImageResource(R.drawable.home_med);
                else if (buttonClicks[2]==2)
                    _homeAlert.setImageResource(R.drawable.home_urgent);
            } //btn.setImageResource(R.drawable.actions_record);
        });

        _transportAlert = (ImageButton) findViewById(R.id.transportAlert);
        _transportAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicks[3] = (buttonClicks[3]+1) %3;
                if (buttonClicks[3]==0)
                    _transportAlert.setImageResource(R.drawable.transport);
                else if (buttonClicks[3]==1)
                    _transportAlert.setImageResource(R.drawable.transport_med);
                else if (buttonClicks[3]==2)
                    _transportAlert.setImageResource(R.drawable.transport_urgent);
            } //btn.setImageResource(R.drawable.actions_record);
        });

        _foodAlert = (ImageButton) findViewById(R.id.foodAlert);
        _foodAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicks[4] = (buttonClicks[4]+1) %3;
                if (buttonClicks[4]==0)
                    _foodAlert.setImageResource(R.drawable.food);
                else if (buttonClicks[4]==1)
                    _foodAlert.setImageResource(R.drawable.food_med);
                else if (buttonClicks[4]==2)
                    _foodAlert.setImageResource(R.drawable.food_urgent);
            } //btn.setImageResource(R.drawable.actions_record);
        });

        _moneyAlert = (ImageButton) findViewById(R.id.moneyAlert);
        _moneyAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicks[5] = (buttonClicks[5]+1) %3;
                if (buttonClicks[5]==0)
                    _moneyAlert.setImageResource(R.drawable.finance);
                else if (buttonClicks[5]==1)
                    _moneyAlert.setImageResource(R.drawable.finance_med);
                else if (buttonClicks[5]==2)
                    _moneyAlert.setImageResource(R.drawable.finance_urgent);
            } //btn.setImageResource(R.drawable.actions_record);
        });

        _otherAlert = (ImageButton) findViewById(R.id.otherAlert);
        _otherAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicks[6] = (buttonClicks[6]+1) %3;
                if (buttonClicks[6]==0)
                    _otherAlert.setImageResource(R.drawable.other);
                else if (buttonClicks[6]==1)
                    _otherAlert.setImageResource(R.drawable.other_med);
                else if (buttonClicks[6]==2)
                    _otherAlert.setImageResource(R.drawable.other_urgent);
            } //btn.setImageResource(R.drawable.actions_record);
        });

        _lifeAlert = (ImageButton) findViewById(R.id.lifeAlert);
        _lifeAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicks[7] = (buttonClicks[7]+1) %3;
                if (buttonClicks[7]==0)
                    _lifeAlert.setImageResource(R.drawable.life_planning);
                else if (buttonClicks[7]==1)
                    _lifeAlert.setImageResource(R.drawable.life_planning_med);
                else if (buttonClicks[7]==2)
                    _lifeAlert.setImageResource(R.drawable.life_planning_urgent);
            } //btn.setImageResource(R.drawable.actions_record);
        });

    }


    @Override
    public void onClick(View view) {
        for (int i=0;i<8;i++){

        }
    }

//    private R.drawable getIcon(int index, int numberOfClicks){
//        ImageButton imageButton = getClickedImageButton(index);
//        String imageName = imageNameBuilder(index, numberOfClicks);
//        imageButton.setImageResource(R.drawable.imageName);
//    }
//
//    private String imageNameBuilder(int index,int numberOfClicks){
//        String name = (String)buttonMap.get(index);
//    }
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
}
