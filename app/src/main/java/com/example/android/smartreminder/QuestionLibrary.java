package com.example.android.smartreminder;

/**
 * Created by samir692 on 3/10/18.
 */

public class QuestionLibrary {

    private String mQuestions [] = {
            "Whats up baby?",
            "When you guys will finish this project?",
            "DO you know how much time is left?",
            "Should I come there and force you to begin??  BEGIN TO CODEEEEEEEEE OR DIEEEE"
    };

    private String mChoices[][] = {
            {"Cool", "NOt bad", "FIne", "Perfect"},
            {"Today", "In a minute", "IN a second", "IN a millisecond"},
            {"No", "Yes", "Maybe", "Not sure"},
            {"Yes please", "No I will finish", "Never", "I have already finished"}
    };

    private String mCorrectAnswers[] = {"Perfect","IN a millisecond","No","Never"};

    public String getQuestion(int i){
        return mQuestions[i];
    };

    public String getChoice1(int i){
        return mChoices[i][0];
    };

    public String getChoice2(int i){
        return mChoices[i][1];
    };

    public String getChoice3(int i){
        return mChoices[i][2];
    };

    public String getChoice4(int i){
        return mChoices[i][3];
    };

    public String getCorrectAnswer(int i){
        return mCorrectAnswers[i];
    }
}
