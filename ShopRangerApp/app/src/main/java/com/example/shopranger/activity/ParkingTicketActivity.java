package com.example.shopranger.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.shopranger.R;

public class ParkingTicketActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ParkingTicketActivity.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_ticket);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

        TextView license_text = findViewById(R.id.license_number);
        TextView date_text = findViewById(R.id.date);
        TextView time_text = findViewById(R.id.time);
        TextView mall_text = findViewById(R.id.mall);
        TextView floor_text = findViewById(R.id.floor);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        license_text.setText(prefs.getString("Number", "ABB1543"));
        date_text.setText(prefs.getString("Date", "1"));
        time_text.setText(prefs.getString("Time", "1"));
        mall_text.setText(prefs.getString("Mall", "1"));
        floor_text.setText(prefs.getString("Floor","1"));
    }
}
