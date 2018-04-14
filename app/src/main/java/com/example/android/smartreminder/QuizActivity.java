package com.example.android.smartreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.smartreminder.R;
import com.example.android.smartreminder.database_sql.DatabaseHandler;

import static com.example.android.smartreminder.BooksListActivity.EXTRA_MESSAGE;

public class QuizActivity extends AppCompatActivity {

    private QuestionLibrary mQuestLib = new QuestionLibrary();
    private DatabaseHandler databaseHandler = new DatabaseHandler(QuizActivity.this);

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionView = (TextView) findViewById(R.id.quiz_question);
        mButtonChoice1 = (Button) findViewById(R.id.quiz_answer1);
        mButtonChoice2 = (Button) findViewById(R.id.quiz_answer2);
        mButtonChoice4 = (Button) findViewById(R.id.quiz_answer3);
        mButtonChoice3 = (Button) findViewById(R.id.quiz_answer4);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            username = extras.getString(EXTRA_MESSAGE);
        }

        updateQuestion();


        //Button1 listener start
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logic for button

                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else{
                    if (mQuestionNumber >= 3){
                        Toast.makeText(QuizActivity.this, "Thanks for your quiz", Toast.LENGTH_LONG).show();
                        String personality = "UPHOLDER";
                        switch (personality){
                            case "UPHOLDER":
                                //databaseHandler.createUPHOLDERtable();
                                break;
                            case "OBLIGER":
                                //databaseHandler.createOBLIGERtable();
                                break;
                            case "REBEL":
                                //databaseHandler.createREBELtable();
                                break;
                            case "QUESTIONER":
                                //databaseHandler.createQUESTIONERtable();
                                break;

                        }
                        Contacts user = new Contacts();
                        if(username != null) {
                            user.set_nick_name(username);
                            user.setFilled_quiz(1);
                            databaseHandler.addUserQuizValue(user);
                            Intent intent = new Intent(QuizActivity.this, BooksListActivity.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        System.out.println("Question number = " + mQuestionNumber);
                        updateQuestion();
                    }
                }
            }
        });

        //Button2 listener start
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logic for button

                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else{
                    if (mQuestionNumber >= 3){
                        Toast.makeText(QuizActivity.this, "Thanks for your quiz", Toast.LENGTH_LONG).show();
                        String personality = "UPHOLDER";
                        switch (personality){
                            case "UPHOLDER":
                                //databaseHandler.createUPHOLDERtable();
                                break;
                            case "OBLIGER":
                                //databaseHandler.createOBLIGERtable();
                                break;
                            case "REBEL":
                                //databaseHandler.createREBELtable();
                                break;
                            case "QUESTIONER":
                                //databaseHandler.createQUESTIONERtable();
                                break;

                        }
                        Contacts user = new Contacts();
                        if(username != null) {
                            user.set_nick_name(username);
                            user.setFilled_quiz(1);
                            databaseHandler.addUserQuizValue(user);
                            Intent intent = new Intent(QuizActivity.this, BooksListActivity.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        System.out.println("Question number = " + mQuestionNumber);
                        updateQuestion();
                    }
                }
            }
        });

        //Button3 listener start
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logic for button

                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else{
                    if (mQuestionNumber >= 3){
                        Toast.makeText(QuizActivity.this, "Thanks for your quiz", Toast.LENGTH_LONG).show();
                        String personality = "UPHOLDER";
                        switch (personality){
                            case "UPHOLDER":
                                //databaseHandler.createUPHOLDERtable();
                                break;
                            case "OBLIGER":
                                //databaseHandler.createOBLIGERtable();
                                break;
                            case "REBEL":
                                //databaseHandler.createREBELtable();
                                break;
                            case "QUESTIONER":
                                //databaseHandler.createQUESTIONERtable();
                                break;

                        }
                        Contacts user = new Contacts();
                        if(username != null) {
                            user.set_nick_name(username);
                            user.setFilled_quiz(1);
                            databaseHandler.addUserQuizValue(user);
                            Intent intent = new Intent(QuizActivity.this, BooksListActivity.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        System.out.println("Question number = " + mQuestionNumber);
                        updateQuestion();
                    }
                }
            }
        });

        //Button4 listener start
        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logic for button

                if (mButtonChoice4.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else{
                    if (mQuestionNumber >= 3){
                        Toast.makeText(QuizActivity.this, "Thanks for your quiz", Toast.LENGTH_LONG).show();
                        String personality = "UPHOLDER";
                        switch (personality){
                            case "UPHOLDER":
                                //databaseHandler.createUPHOLDERtable();
                                break;
                            case "OBLIGER":
                                //databaseHandler.createOBLIGERtable();
                                break;
                            case "REBEL":
                                //databaseHandler.createREBELtable();
                                break;
                            case "QUESTIONER":
                                //databaseHandler.createQUESTIONERtable();
                                break;

                        }
                        Contacts user = new Contacts();
                        if(username != null) {
                            user.set_nick_name(username);
                            user.setFilled_quiz(1);
                            databaseHandler.addUserQuizValue(user);
                            Intent intent = new Intent(QuizActivity.this, BooksListActivity.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        System.out.println("Question number = " + mQuestionNumber);
                        updateQuestion();
                    }
                }
            }
        });
    }

    private void updateQuestion(){
        mQuestionView.setText(mQuestLib.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestLib.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestLib.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestLib.getChoice3(mQuestionNumber));
        mButtonChoice4.setText(mQuestLib.getChoice4(mQuestionNumber));

        mAnswer = mQuestLib.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
    }

    private void updateScore(int point){
        //can update Score in the view if you want
    }
}
