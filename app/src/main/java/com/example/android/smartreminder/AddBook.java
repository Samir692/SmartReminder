package com.example.android.smartreminder;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.android.smartreminder.LoginActivity.username;

public class AddBook extends AppCompatActivity {

    private DatabaseHandler dbHandler;
    private TextView deadline;
    private String date;
    private Calendar myCalendar;
    private long millis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DatabaseHandler(AddBook.this);

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date_calendar = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date = updateLabel();

                String delegate = "hh:mm aaa";
                String date_hour_min = (String) DateFormat.format(delegate,Calendar.getInstance().getTime());
                String deadline_hour_min = date + " " + date_hour_min;
                System.out.println("date fully = " + deadline_hour_min);

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy h:mm a", Locale.ENGLISH);
                Date deadline_date_format = null;
                try {
                    deadline_date_format = sdf.parse(deadline_hour_min);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                millis = deadline_date_format.getTime();
                System.out.println("chosen millisecond = " + millis);

                deadline.setText(date);
            }
        };


        final QuestionsAnswersCharacterBased questAnsChar = new QuestionsAnswersCharacterBased();


        final EditText book_name = (EditText) findViewById(R.id.book_name);
        final EditText total_page = (EditText) findViewById(R.id.total_page);
        total_page.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        final TextView bookQuestion1 = (TextView) findViewById(R.id.bookQuestion1);
        final EditText answer1 = (EditText) findViewById(R.id.answer1);
        final TextView bookQuestion2 = (TextView) findViewById(R.id.bookQuestion2);
        final EditText answer2 = (EditText) findViewById(R.id.answer2);
        final TextView bookQuestion3 = (TextView) findViewById(R.id.bookQuestion3);
        final EditText answer3 = (EditText) findViewById(R.id.answer3);
        final TextView bookQuestion4 = (TextView) findViewById(R.id.bookQuestion4);
        final EditText answer4 = (EditText) findViewById(R.id.answer4);
        final TextView bookQuestion5 = (TextView) findViewById(R.id.bookQuestion5);
        final EditText answer5 = (EditText) findViewById(R.id.answer5);

        deadline = (TextView) findViewById(R.id.deadline);
        Button btnSetDeadline = (Button) findViewById(R.id.btnSetDeadline);

        final String[] mQuestionsQuestionner = questAnsChar.getmQuestionsQuestionner();
        final String[] mQuestionsUpholders = questAnsChar.getmQuestionsUpholders();
        final String[] mQuestionsObliger = questAnsChar.getmQuestionsObliger();
        final String[] mQuestionsRebel = questAnsChar.getmQuestionsRebel();


        Contacts user_local = dbHandler.getPersonalityType(username);
        final String personality = user_local.get_personality_type();
        System.out.println("personality = " + personality + " username = " + username);

        if(personality.length() > 0){
            //create questions
            switch (personality) {

                case "QUESTIONNER":
                    System.out.println("Type is questionnere");
                    bookQuestion1.setText(mQuestionsQuestionner[0]);
                    bookQuestion2.setText(mQuestionsQuestionner[1]);
                    bookQuestion3.setText(mQuestionsQuestionner[2]);
                    bookQuestion4.setText(mQuestionsQuestionner[3]);
                    bookQuestion5.setText(mQuestionsQuestionner[4]);
                    break;

                case "UPHOLDER":
                    System.out.println("Type is upholder");
                    bookQuestion1.setText(mQuestionsUpholders[0]);
                    bookQuestion2.setText(mQuestionsUpholders[1]);
                    bookQuestion3.setText(mQuestionsUpholders[2]);
                    bookQuestion4.setText(mQuestionsUpholders[3]);
                    bookQuestion5.setText(mQuestionsUpholders[4]);

                    break;

                case "OBLIGER":
                    System.out.println("Type is obliger");
                    bookQuestion1.setText(mQuestionsObliger[0]);
                    bookQuestion2.setText(mQuestionsObliger[1]);
                    bookQuestion3.setText(mQuestionsObliger[2]);
                    bookQuestion4.setText(mQuestionsObliger[3]);
                    bookQuestion5.setText(mQuestionsObliger[4]);

                    break;

                case "REBEL":
                    System.out.println("Type is rebel");
                    bookQuestion1.setText(mQuestionsRebel[0]);
                    bookQuestion2.setText(mQuestionsRebel[1]);
                    bookQuestion3.setText(mQuestionsRebel[2]);
                    bookQuestion4.setText(mQuestionsRebel[3]);
                    bookQuestion5.setText(mQuestionsRebel[4]);

                    break;

            }
        }
        else{
            Log.d("personality", "Personality type is missing");
        }




        //get deadline from Calendar class
        btnSetDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(AddBook.this, date_calendar, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                //Intent intent = new Intent(AddBook.this, CalendarActivity.class);
                //intent.putExtra("className","com.example.android.smartreminder.AddBook");
                //startActivity(intent);
            }
        });

        //Intent incoming = getIntent();
        //final String date = incoming.getStringExtra("date");

        //add Book properties
        Button addBook = (Button) findViewById(R.id.addBook);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(view);
                Log.d("transition", "adding book to database");
                String answer1Text = answer1.getText().toString();

                //add Book
                Books book = new Books();
                String bookName = book_name.getText().toString();
                int totalPage = 0;
                try {
                    totalPage = Integer.parseInt(total_page.getText().toString());
                }
                catch (Exception e){

                    Toast.makeText(getApplicationContext(), R.string.error_add_book_total_number, Toast.LENGTH_SHORT).show();
                }
                String ANSWER1 = answer1.getText().toString();
                String ANSWER2 = answer2.getText().toString();
                String ANSWER3 = answer3.getText().toString();
                String ANSWER4 = answer4.getText().toString();
                String ANSWER5 = answer5.getText().toString();

                if(!bookName.equals("") && totalPage != 0 && !ANSWER1.equals("") && !ANSWER2.equals("") && !ANSWER3.equals("")
                        && !ANSWER4.equals("") && !ANSWER5.equals("") && millis > System.currentTimeMillis()) {

                    System.out.println("Setting book details");
                    book.set_book_name(bookName);
                    book.set_book_total_pages(totalPage);
                    book.set_book_done_pages(0);
                    book.set_book_deadline(date);

                    switch (personality) {
                        case "QUESTIONNER":
                            String[] mAnswersQuestionner = new String[5];
                            mAnswersQuestionner[0] = answer1.getText().toString();
                            mAnswersQuestionner[1] = answer2.getText().toString();
                            mAnswersQuestionner[2] = answer3.getText().toString();
                            mAnswersQuestionner[3] = answer4.getText().toString();
                            mAnswersQuestionner[4] = answer5.getText().toString();

                            questAnsChar.setmAnswersQuestionner(mAnswersQuestionner);
                            dbHandler.addQuestionAnswer(mQuestionsQuestionner[0], mAnswersQuestionner[0], "QUESTIONNER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsQuestionner[1], mAnswersQuestionner[1], "QUESTIONNER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsQuestionner[2], mAnswersQuestionner[2], "QUESTIONNER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsQuestionner[3], mAnswersQuestionner[3], "QUESTIONNER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsQuestionner[4], mAnswersQuestionner[4], "QUESTIONNER", bookName);

                            break;

                        case "UPHOLDER":
                            String[] mAnswersUpholders = new String[5];
                            mAnswersUpholders[0] = answer1.getText().toString();
                            mAnswersUpholders[1] = answer2.getText().toString();
                            mAnswersUpholders[2] = answer3.getText().toString();
                            mAnswersUpholders[3] = answer4.getText().toString();
                            mAnswersUpholders[4] = answer5.getText().toString();

                            questAnsChar.setmAnswersQuestionner(mAnswersUpholders);
                            dbHandler.addQuestionAnswer(mQuestionsUpholders[0], mAnswersUpholders[0], "UPHOLDER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsUpholders[1], mAnswersUpholders[1], "UPHOLDER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsUpholders[2], mAnswersUpholders[2], "UPHOLDER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsUpholders[3], mAnswersUpholders[3], "UPHOLDER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsUpholders[4], mAnswersUpholders[4], "UPHOLDER", bookName);

                            break;

                        case "OBLIGER":
                            String[] mAnswersObliger = new String[5];
                            mAnswersObliger[0] = answer1.getText().toString();
                            mAnswersObliger[1] = answer2.getText().toString();
                            mAnswersObliger[2] = answer3.getText().toString();
                            mAnswersObliger[3] = answer4.getText().toString();
                            mAnswersObliger[4] = answer5.getText().toString();

                            questAnsChar.setmAnswersQuestionner(mAnswersObliger);

                            dbHandler.addQuestionAnswer(mQuestionsObliger[0], mAnswersObliger[0], "OBLIGER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsObliger[1], mAnswersObliger[1], "OBLIGER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsObliger[2], mAnswersObliger[2], "OBLIGER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsObliger[3], mAnswersObliger[3], "OBLIGER", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsObliger[4], mAnswersObliger[4], "OBLIGER", bookName);

                            break;

                        case "REBEL":
                            String[] mAnswersRebel = new String[5];
                            mAnswersRebel[0] = answer1.getText().toString();
                            mAnswersRebel[1] = answer2.getText().toString();
                            mAnswersRebel[2] = answer3.getText().toString();
                            mAnswersRebel[3] = answer4.getText().toString();
                            mAnswersRebel[4] = answer5.getText().toString();

                            questAnsChar.setmAnswersQuestionner(mAnswersRebel);
                            dbHandler.addQuestionAnswer(mQuestionsRebel[0], mAnswersRebel[0], "REBEL", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsRebel[1], mAnswersRebel[1], "REBEL", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsRebel[2], mAnswersRebel[2], "REBEL", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsRebel[3], mAnswersRebel[3], "REBEL", bookName);
                            dbHandler.addQuestionAnswer(mQuestionsRebel[4], mAnswersRebel[4], "REBEL", bookName);

                            break;
                    }

                    if (dbHandler.addBook(book) > 0) {
                        Contacts user_local = new Contacts();
                        user_local.set_book_name(bookName);
                        user_local.set_nick_name(username);
                        dbHandler.addBookNameValue(user_local);

                        //set alarms
                        long time_interval_alarm = millis;

                        scheduleAlarm(time_interval_alarm);

                        Toast.makeText(getApplicationContext(), bookName + " has been registered", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddBook.this, BooksListActivity.class);
                        intent.putExtra("book", "added");
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddBook.this, R.string.error_add_book, Toast.LENGTH_LONG).show();
                    }

                    //add book information depending on category
                }
                else{
                    if(millis <= System.currentTimeMillis()){
                        Toast.makeText(getApplicationContext(), R.string.error_add_book_deadline_early, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), R.string.error_add_book_not_filled, Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }


    private void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private String updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String date = sdf.format(myCalendar.getTime());
        return date;

    }

    private void scheduleAlarm(long deadline){

        /* Create the PendingIntent that will launch the BroadcastReceiver */
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmIntent.putExtra("username", username);
        System.out.println("USERNAMEBEFORE ALARM = " + username);
        PendingIntent pending = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d("alarm", "Setted repeated alarms");


        //
        //set alarmmanaget to cancel already existing alarm
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+86400000, manager.INTERVAL_DAY, pending);
        Intent cancellationIntent = new Intent(this, CancelAlarmBroadcastReceiver.class);
        cancellationIntent.putExtra("key", pending);
        PendingIntent cancellationPendingIntent = PendingIntent.getBroadcast(this, 0, cancellationIntent, 0);
        manager.set(AlarmManager.RTC_WAKEUP,  deadline, cancellationPendingIntent);

        Log.d("alarm", "Setted cancellation");

    }


}
