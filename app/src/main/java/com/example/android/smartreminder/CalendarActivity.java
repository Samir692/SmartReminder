package com.example.android.smartreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

/**
 * Created by samir692 on 3/30/18.
 */

public class CalendarActivity extends AppCompatActivity {

    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/"+ dayOfMonth ;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent incoming = getIntent();
                String className = incoming.getStringExtra("className");
                Class<?> myClass = null;
                try {
                    myClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(CalendarActivity.this,myClass);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }

}
