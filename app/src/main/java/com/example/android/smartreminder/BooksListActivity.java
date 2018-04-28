package com.example.android.smartreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.android.smartreminder.LoginActivity.username;

public class BooksListActivity extends AppCompatActivity

    implements NavigationView.OnNavigationItemSelectedListener {


        public static final String  EXTRA_MESSAGE = "com.example.android.smartreminder";
        private DatabaseHandler dbHandler;
        private TextView thedate;
        private TextView currentBook;
        private Button btngocalendar;
        private Button getLastBook;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_books_list);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            dbHandler = new DatabaseHandler(BooksListActivity.this);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            TextView book_name_main = (TextView) findViewById(R.id.book_name_main);
            TextView book_deadline_main = (TextView) findViewById(R.id.DeadlineText);
            TextView book_done_pages = (TextView) findViewById(R.id.DonePage);
            TextView book_total_pages = (TextView) findViewById(R.id.TotalPage);
            ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);



            fab.hide();
            fab.setEnabled(false);


            //check if there is already book stored or book is finished
            Contacts user_for_recent_book = dbHandler.getUsersBookName(username);
            String book_name = user_for_recent_book.get_book_name();
            //System.out.println("book name = " + book_name);
            if(book_name.equals("EMPTY")) {

                fab.show();
                fab.setEnabled(true);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("transition", "adding book");
                        Intent intent = new Intent(BooksListActivity.this, AddBook.class);
                        startActivity(intent);

                    }
                });
            }
            else{
                book_name_main.setText(book_name);
                Books last_book = dbHandler.getBook(book_name);
                String deadline = last_book.get_book_deadline();
                int total_pages = last_book.get_book_total_pages();
                int done_pages = last_book.get_book_done_pages();

                if(!deadline.isEmpty() && total_pages >= 0 && done_pages >= 0){
                    book_deadline_main.setText(deadline);
                    book_done_pages.setText(Integer.toString(done_pages));
                    book_total_pages.setText(Integer.toString(total_pages));
                    int progress = (done_pages/total_pages)*100;
                    simpleProgressBar.setProgress(progress);

                }
            }


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


//            //calendar view test
//            thedate = (TextView) findViewById(R.id.dateCalendar);
//            btngocalendar = (Button) findViewById(R.id.btngocalendar);
//            getLastBook = (Button) findViewById(R.id.getLastBook);
//            currentBook = (TextView) findViewById(R.id.currentBook);
//
//            //default addbook is not visible
//            getLastBook.setVisibility(View.GONE);
//            currentBook.setVisibility(View.GONE);
//
//            Contacts user = dbHandler.getUser(username);
//            System.out.println("nickname = " + username);
//            String last_book_of_user = user.get_book_name();
//            if(last_book_of_user.equals("EMPTY")){
//                getLastBook.setVisibility(View.VISIBLE);
//                currentBook.setVisibility(View.VISIBLE);
//            }
//
//            Intent incoming = getIntent();
//            String date = incoming.getStringExtra("date");
//            thedate.setText(date);
//
//            btngocalendar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(BooksListActivity.this, CalendarActivity.class);
//                    intent.putExtra("className","com.example.android.smartreminder.BooksListActivity");
//                    startActivity(intent);
//                }
//            });
//
//            getLastBook.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
        }



        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_camera) {
                // Handle the camera action
            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


        //show progress for given book

        private void displayProgressBook(){

            //number of pages have been finished


            //number of pages on total

            //calculate percentage

            //display result

            //optional add more information depending on character

        }

}
