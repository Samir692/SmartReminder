package com.example.android.smartreminder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionLibraryTest {
    private QuestionLibrary q;

    @Before
    public void init(){
        q=new QuestionLibrary();
    }

    @Test
    public void checkingQuestionsQualityRebel ()throws Exception {
        int counter=0;
        if (q.getChoice1(0) =="") counter++;
        if (q.getChoice2(1) =="") counter++;
//        if (q.getChoice3(2) =="") counter++;
//        if (q.getChoice4(3) =="") counter++;
        assertEquals(3,counter);
    }
    @Test
    public void checkingQuestionsQualityQuestioner ()throws Exception {
        int counter=0;
        if (q.getChoice1(0) =="") counter++;
        if (q.getChoice2(1) =="") counter++;
//        if (q.getChoice3(2) =="") counter++;
//        if (q.getChoice4(3) =="") counter++;
        assertEquals(3,counter);
    }

    @Test
    public void checkingQuestionsQualityUpholder () throws Exception {
        int counter=0;
        if (q.getChoice1(0) =="") counter++;
        if (q.getChoice2(1) =="") counter++;
//        if (q.getChoice3(2) =="") counter++;
//        if (q.getChoice4(3) =="") counter++;
        assertEquals(3,counter);
    }

    @Test
    public void checkingQuestionsQualityObliger () throws Exception {
        int counter=0;
        if (q.getChoice1(0) =="") counter++;
        if (q.getChoice2(1) =="") counter++;
//        if (q.getChoice3(2) =="") counter++;
//        if (q.getChoice4(3) =="") counter++;
        assertEquals(3,counter);
    }
}