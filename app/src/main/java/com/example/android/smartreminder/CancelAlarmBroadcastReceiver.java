package com.example.android.smartreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by samir692 on 4/27/18.
 */
public class CancelAlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("alerm-popup", "alarm cancelled");
        PendingIntent pendingIntent = intent.getParcelableExtra("key");
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pendingIntent);
    }
}