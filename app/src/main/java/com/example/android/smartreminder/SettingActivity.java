package com.example.android.smartreminder;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import java.util.Set;

import static com.example.android.smartreminder.LoginActivity.username;

public class SettingActivity extends AppCompatActivity{

    private EditText edit_Name;
    private EditText edit_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        edit_Name = (EditText) findViewById(R.id.edit_Name);
        edit_Email = (EditText) findViewById(R.id.edit_Email);
        Button edit_submit = (Button) findViewById(R.id.edit_submit) ;
        DatabaseHandler dbHandler = new DatabaseHandler(SettingActivity.this);


        Contacts user = dbHandler.getUser(username);


        edit_Name.setText(username);
        edit_Email.setText(user.get_email());



        edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard(view);

            }
        });


    }

    private void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




}
