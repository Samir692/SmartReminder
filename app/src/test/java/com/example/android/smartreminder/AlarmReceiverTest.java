package com.example.android.smartreminder;

import android.app.AlertDialog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class AlarmReceiverTest {

    private AlarmReceiver alarmReceiver = null;

    @Before
    public void setUp() throws Exception {
        alarmReceiver=new AlarmReceiver();
        assertNotNull(alarmReceiver);
    }

    @Test
    public void testShowDialog(){
       AlertDialog alertDialog=alarmReceiver.getAlertDialog();
       assertNotNull(alertDialog);
       assertTrue(alertDialog.isShowing());
    }

    @After
    public void tearDown() throws Exception {
        alarmReceiver = null;
    }


}