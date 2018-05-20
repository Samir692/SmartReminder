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

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import java.util.List;
import java.util.Random;


/**
 * Created by samir692 on 4/22/18.
 */

public class AlarmReceiver extends BroadcastReceiver{
    private Context context;
    private Intent intent;
    private DatabaseHandler dbHandler;
    private String userNAME;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        userNAME = intent.getStringExtra("username");
        //System.out.println("USERNAMEEEE FROM INTENT" + userNAME);
        dbHandler = new DatabaseHandler(context);
        System.out.println("assigning context...");
        ShowDialog();
    }

    public void ShowDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        //detect personality of user
        String personality = dbHandler.getPersonalityType(userNAME).get_personality_type();

        //detect name of book
        String bookName = dbHandler.getUser(userNAME).get_book_name();

        //TODO for data need to filter by bookname. Duplicated data is in table
        //System.out.println("PERSONAOSLMSA " + personality);
        //System.out.println("USERNAMEEEE " + userNAME);
        List<String> qaList = dbHandler.getQuestionAnswerPair(personality, bookName);
        Random rng = new Random();
        //System.out.println("Question List = " + qaList);

        int maxxNum = qaList.size();
        //System.out.println("maxx size = " + maxxNum);

        int chosenQuestInt = rng.nextInt(maxxNum);
        //System.out.println("chosen number = " + chosenQuestInt);


        String chosenQuestAnswerPair[] = qaList.get(chosenQuestInt).split(" answer = ");
        //System.out.println("Pair before split = " + qaList.get(chosenQuestInt));

        //System.out.println("Splitted str = " + chosenQuestAnswerPair);

        String chosenQuest = chosenQuestAnswerPair[0];
        String chosenAnswer = chosenQuestAnswerPair[1];

        Log.d("alerm-popup", "alarm fired");

        System.out.println("Alert dialog initilaized");
        alertDialog.setTitle(chosenQuest);
        alertDialog.setMessage(chosenAnswer);

        alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = alertDialog.create();


        // line you have to add
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        alert.show();



    }

    public AlertDialog getAlertDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("REMINDER!");
        builder.setMessage("Turn off alarm by pressing off");

        builder.setNegativeButton("Off", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = builder.create();
        // line you have to add
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);

        return alert;
    }
}
