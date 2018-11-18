package com.example.shopranger.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.shopranger.R;
import com.example.shopranger.model.Reservation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ReserveActivity extends AppCompatActivity implements OnClickListener {


    private EditText date, time, number, mall, floor;
    private FrameLayout progressOverlay;
    private ImageButton floorInfo;
    private Calendar calendar;
    final String[] malls = {"Pavillion", "Mall of Medini", "One Utama"};
    final String[] floors = {"1", "2", "3", "4", "5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        calendar = Calendar.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        date = findViewById(R.id.date);
        date.setOnClickListener(this);
        time = findViewById(R.id.time);
        time.setOnClickListener(this);
        number = findViewById(R.id.licenseNumber);
        mall = findViewById(R.id.mall);
        mall.setOnClickListener(this);
        floor = findViewById(R.id.floor);
        floor.setOnClickListener(this);
        floorInfo = findViewById(R.id.floor_info);
        floorInfo.setOnClickListener(this);
        progressOverlay = findViewById(R.id.progress_overlay);

        Button reserveButton = findViewById(R.id.reserve_button);
        reserveButton.setOnClickListener(this);

    }


    private void attemptReserve() {

        // Reset errors.
        date.setError(null);
        time.setError(null);
        mall.setError(null);
        floor.setError(null);
        number.setError(null);

        boolean cancel = false;
        View focusView = null;
        List<EditText> editTexts = new ArrayList<>(5);
        editTexts.add(number);
        editTexts.add(date);
        editTexts.add(time);
        editTexts.add(mall);
        editTexts.add(floor);

        String DATE = date.getText().toString();
        String TIME = time.getText().toString();
        String MALL = mall.getText().toString();
        String FLOOR = floor.getText().toString();
        String NUMBER = number.getText().toString();

        List<String> inputs = new ArrayList<>(5);
        inputs.add(NUMBER);
        inputs.add(DATE);
        inputs.add(TIME);
        inputs.add(MALL);
        inputs.add(FLOOR);

        for (int i = 0; i < 5; i++) {
            if (TextUtils.isEmpty(inputs.get(i))) {
                editTexts.get(i).setError(getString(R.string.error_field_required));
                focusView = editTexts.get(i);
                cancel = true;
                break;
            }
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            progressOverlay.setVisibility(View.VISIBLE);
            DatabaseReference myRef =  FirebaseDatabase.getInstance().getReference("reserve_for/");
            myRef.setValue(new Reservation("11233", NUMBER, DATE, TIME, MALL, FLOOR));
            SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            prefEditor.putString("Number", NUMBER);
            prefEditor.putString("Date", DATE);
            prefEditor.putString("Time" , TIME);
            prefEditor.putString("Mall", MALL);
            prefEditor.putString("Floor", FLOOR);
            prefEditor.apply();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressOverlay.setVisibility(View.GONE);
                    startActivity(new Intent(ReserveActivity.this, ParkingTicketActivity.class));
                }
            }, 1500);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String DATE = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                        date.setText(DATE);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

                break;
            case R.id.time:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);
                        String TIME = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                        time.setText(TIME);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            case R.id.mall:
                final int mallSelected = 0;
                new AlertDialog.Builder(this)
                        .setTitle("Select Shopping Mall")
                        .setSingleChoiceItems(malls, mallSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mall.setText(malls[i]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mall.setText(malls[mallSelected]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("cancel", null)
                        .show();
                break;
            case R.id.floor:
                final int floorsSelected = 0;
                new AlertDialog.Builder(this)
                        .setTitle("Select Floor")
                        .setSingleChoiceItems(floors, floorsSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                floor.setText(floors[i]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                floor.setText(floors[floorsSelected]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("cancel", null)
                        .show();
                break;
            case R.id.floor_info:

                break;
            case R.id.reserve_button:
                attemptReserve();
                break;
            default:
                break;
        }
    }
}

