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

        DatabaseHandler dbHandler = new DatabaseHandler(SettingActivity.this);


        Contacts user = dbHandler.getUser(username);


        edit_Name.setText(username);
        edit_Email.setText(user.get_email());




    }




}
