package com.ess.solent.cleaningservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class UserHomeActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_home_user);
        Button service = (Button)findViewById(R.id.button1);
        service.setOnClickListener(this);
        Button schedule = (Button)findViewById(R.id.button2);
        schedule.setOnClickListener(this);
        Button history = (Button)findViewById(R.id.button3);
        history.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            if (v.getId() == R.id.button1) {
                Intent intent = new Intent(this, UserServiceActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button2) {
                Intent intent = new Intent(this, UserScheduleActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.button3) {
                Intent intent = new Intent(this, UserServiceHistoryActivity.class);
                startActivity(intent);
            }
            Log.d("JAMES v.getID = ",  String.valueOf(v.getId()));
        }catch (Exception e){
            Log.d("JAMES DEBUG", e.getMessage());

        }
    }
}
