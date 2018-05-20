package com.example.android.smartreminder;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.widget.LinearLayout;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

import static com.example.android.smartreminder.LoginActivity.username;

public class SettingActivity extends AppCompatActivity{

    private EditText edit_Name;
    private EditText edit_Email;
    private EditText edit_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        edit_Name = (EditText) findViewById(R.id.edit_Name);
        edit_Email = (EditText) findViewById(R.id.edit_Email);
        edit_Password = (EditText) findViewById(R.id.edit_Password);
        final EditText edit_Password2 = (EditText) findViewById(R.id.edit_Password2);
        final EditText edit_Password3 = (EditText) findViewById(R.id.edit_Password3);
        final LinearLayout newPassword = (LinearLayout) findViewById(R.id.newPassword);
        final LinearLayout newPassword2 = (LinearLayout) findViewById(R.id.newPassword2);

        newPassword.setVisibility(View.GONE);
        newPassword2.setVisibility(View.GONE);


        Button edit_submit = (Button) findViewById(R.id.edit_submit) ;
        final DatabaseHandler dbHandler = new DatabaseHandler(SettingActivity.this);

        edit_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_Password.setText("");
                newPassword.setVisibility(View.VISIBLE);
                newPassword2.setVisibility(View.VISIBLE);
            }
        });


        final Contacts user = dbHandler.getUser(username);
        final Authenication ath = new Authenication();

        edit_Name.setText(username);
        edit_Email.setText(user.get_email());

        final String EMAIL = user.get_email();

        System.out.println("EMAIL = " + user.get_email());





        edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideSoftKeyboard(view);
                String oldPassword = edit_Password.getText().toString();
                String newPasswordEdited1 = edit_Password2.getText().toString();
                String newPasswordEdited2 = edit_Password3.getText().toString();



                //check old password validity

                if(oldPassword.equals("")){
                    Snackbar.make(view, "Old password is empty. Enter right one", Snackbar.LENGTH_LONG).show();
                    return;
                }
                try {
                    if(ath.authenticate(getApplicationContext(), username, oldPassword)) {
                        System.out.println("Old password is correct");
                    }
                    else{
                        Snackbar.make(view, getString(R.string.error_incorrect_old_password), Snackbar.LENGTH_LONG).show();
                        return;
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }


                //check if new passwords are not empty
                if(newPasswordEdited1.equals("") || newPasswordEdited2.equals("")){
                    Snackbar.make(view, getString(R.string.error_empty_new_passwords), Snackbar.LENGTH_LONG).show();
                    return;
                }

                //check if new passwords are correct
                if(!newPasswordEdited1.equals(newPasswordEdited2)){
                    Snackbar.make(view, getString(R.string.error_incorrect_new_passwords), Snackbar.LENGTH_LONG).show();
                    return;
                }

                //update user
                Contacts user_local = new Contacts();


                //hash new password
                Contacts user_for_new_password = dbHandler.getPassAndSalt(username);
                char[] char_pass = newPasswordEdited2.toCharArray();
                byte[] got_salt = user_for_new_password.get_salt();
                int user_id = user_for_new_password.get_id();

                //System.out.println("User for new password = " + char_pass);
                try {
                    byte[] hash = ath.hashPassword(char_pass, got_salt);
                    user_local.set_nick_name(username);
                    user_local.set_email(EMAIL);
                    user_local.set_password(hash);
                    user_local.set_id(user_id);
                    //System.out.println("Users asdsaasdasd = " + username);

                    if (dbHandler.updateUser(user_local) > 0){
                        Snackbar.make(view, "User information has been updated", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(SettingActivity.this, BooksListActivity.class);
                        startActivity(intent);

                    }

                } catch (NoSuchAlgorithmException e) {
                    //e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    //e.printStackTrace();
                }








            }
        });


    }

    private void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




}
