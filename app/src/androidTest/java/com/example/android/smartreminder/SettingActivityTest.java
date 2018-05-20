package com.example.android.smartreminder;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;


public class SettingActivityTest {

    @Rule
    public ActivityTestRule<SettingActivity> settingActivityActivityTestRule = new ActivityTestRule<SettingActivity>(SettingActivity.class);

    private SettingActivity sActivity = null;

    Instrumentation.ActivityMonitor monitor = InstrumentationRegistry.getInstrumentation().
                                              addMonitor(LoginActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        sActivity = settingActivityActivityTestRule.getActivity();
    }

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.android.smartreminder", appContext.getPackageName());
    }

    @Test
    public void testLaunch(){
        assertNotNull(sActivity.findViewById(R.id.toolbar));
        assertNotNull(sActivity.findViewById(R.layout.activity_setting));
        assertNotNull(sActivity.findViewById(R.id.edit_Name));
        assertNotNull(sActivity.findViewById(R.id.edit_Email));
        assertNotNull(sActivity.findViewById(R.id.edit_Password));
        assertNotNull(sActivity.findViewById(R.id.edit_Password2));

    }

    @After
    public void tearDown() throws Exception {
        sActivity = null;
    }

}