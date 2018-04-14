package com.example.android.smartreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.smartreminder.database_sql.DatabaseHandler;
import com.example.android.smartreminder.helpers.InputValidation;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


import static com.example.android.smartreminder.BooksListActivity.EXTRA_MESSAGE;
import static com.example.android.smartreminder.BooksListActivity.EXTRA_MESSAGE;

public class LoginActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, View.OnClickListener{


    Button b1;
    int counter = 3;
    private static final int PERMISSION_REQUEST_CAMERA = 0;

    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    public static String username;

    private TextInputLayout textInputLayoutNickName;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextNickName;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHandler databaseHelper;
    private Contacts user;

    private static final String TAG = "MyActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        initListeners();
        initObjects();




    }

    //initialize views
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutNickName = (TextInputLayout) findViewById(R.id.textInputLayoutNickName);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextNickName = (TextInputEditText) findViewById(R.id.textInputEditTextNickName);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHandler(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    static final int REGISTER_CONTACT_REQUEST = 0;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                //System.out.println("user = " + databaseHelper.getAllUser());
                //System.out.println();
                //Log.d(TAG, "This entered");
                verifyFromSQLite();

                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                //System.out.println("register enterd");
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivityForResult(intentRegister, REGISTER_CONTACT_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REGISTER_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // A contact was picked.  Here we will just display it
                // to the user.
                Context context = getApplicationContext();
                CharSequence text = "User registered succesfully :)";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        Log.d(TAG, textInputEditTextNickName.toString());
        if (!inputValidation.isInputEditTextFilled(textInputEditTextNickName, textInputLayoutNickName, getString(R.string.error_message_nickname))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        System.out.println("FIELds are corrrrrrrrrrrrrrrrrect");
        Authenication ath = new Authenication();
        String usernm = textInputEditTextNickName.getText().toString().trim();
        String pass = textInputEditTextPassword.getText().toString().trim();

        try {
            if(ath.authenticate(getApplicationContext(), usernm, pass)){

                System.out.println("Input is valid");
                username = textInputEditTextNickName.getText().toString().trim();

                System.out.println("Username is savedddddddddddddddddd = " + username);
                //user = databaseHelper.getUser(username);

                //Intent accountsIntent = new Intent(activity, UsersListActivity.class);
                //accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                //Intent accountsIntent = new Intent(this, HeartRateMonitor.class);

                //CHECK IF USER HAS FILLED QUESTIONERE FROM TABLEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
                Contacts user = databaseHelper.getFilledQuiz(username);
                int userFilledQuestionerre = user.getFilled_quiz();

                if(userFilledQuestionerre == 1) {
                    Intent intent = new Intent(this, BooksListActivity.class);
                    String message = username;
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Please fill the questionerre", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, QuizActivity.class);
                    String message = username;
                    intent.putExtra(EXTRA_MESSAGE, message);
                    startActivity(intent);
                }

                emptyInputEditText();
                //startActivity(accountsIntent);


            } else {
                // Snack Bar to show success message that record is wrong
                Snackbar.make(nestedScrollView, getString(R.string.error_valid_nick_password), Snackbar.LENGTH_LONG).show();
            }
        } catch (NoSuchAlgorithmException e) {
            //e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            //e.printStackTrace();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextNickName.setText(null);
        textInputEditTextPassword.setText(null);
        System.out.println("Access granted");
    }

    public String getTextInputLayoutNickName() {
        return textInputLayoutNickName.toString();
    }












}
