package com.example.shopranger.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.shopranger.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView weatherCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, ParkingTicketActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupUI(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        weatherCard = findViewById(R.id.weather_card);
        ImageButton close  = findViewById(R.id.closeTip);
        close.setOnClickListener(this);
        Button details = findViewById(R.id.weather_details);
        details.setOnClickListener(this);
        CardView reserve = findViewById(R.id.reserve);
        reserve.setOnClickListener(this);
        CardView fashion = findViewById(R.id.fashion);
        fashion.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.closeTip:
                weatherCard.setVisibility(View.GONE);
                break;
            case R.id.weather_details:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://weather.com/weather/today/"));
                startActivity(browserIntent);
                break;
            case R.id.reserve:
                startActivity(new Intent(MainActivity.this, ReserveActivity.class));
                break;
            case R.id.fashion:
                startActivity(new Intent(MainActivity.this, FashionSearchActivity.class ));
                break;
            default:
                break;
        }
    }

}
