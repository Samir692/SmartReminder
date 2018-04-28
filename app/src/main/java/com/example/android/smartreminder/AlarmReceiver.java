package com.example.android.smartreminder;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by samir692 on 4/22/18.
 */

public class AlarmReceiver extends BroadcastReceiver{
    private Context context;
    private Intent intent;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        System.out.println("assigning context...");
        ShowDialog();
    }

    public void ShowDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        Log.d("alerm-popup", "alarm fired");

        System.out.println("Alert dialog initilaized");
        alertDialog.setTitle("REMINDER!");
        alertDialog.setMessage("Turn off alarm by pressing off");

        alertDialog.setNegativeButton("Off", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = alertDialog.create();


        // line you have to add
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        alert.show();



    }
}
