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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.android.smartreminder.LoginActivity.username;

public class BooksListActivity extends AppCompatActivity

    implements NavigationView.OnNavigationItemSelectedListener {


        public static final String  EXTRA_MESSAGE = "com.example.android.smartreminder";
        private String book_name;
        private DatabaseHandler dbHandler;
        private NavigationView nav_view;
        private ScrollView history_view;
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


            nav_view = (NavigationView) findViewById(R.id.nav_view);
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            TextView book_name_main = (TextView) findViewById(R.id.book_name_main);
            TextView book_deadline_main = (TextView) findViewById(R.id.DeadlineText);
            TextView book_done_pages = (TextView) findViewById(R.id.DonePage);
            TextView book_total_pages = (TextView) findViewById(R.id.TotalPage);
            ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
            Button updateButton = (Button) findViewById(R.id.updateButton);







            fab.hide();
            fab.setEnabled(false);


            //check if there is already book stored or book is finished
            Contacts user_for_recent_book = dbHandler.getUsersBookName(username);
            book_name = user_for_recent_book.get_book_name();

            if(book_name.equals("EMPTY")) {

                updateButton.setVisibility(View.GONE);
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

                book_name_main.setText(book_name);
                Books last_book = dbHandler.getBook(book_name);
                String deadline = last_book.get_book_deadline();
                int total_pages = last_book.get_book_total_pages();
                int done_pages = last_book.get_book_done_pages();

                if(!deadline.isEmpty() && total_pages >= 0 && done_pages >= 0){
                    book_deadline_main.setText(deadline);
                    book_done_pages.setText(Integer.toString(done_pages));
                    book_total_pages.setText(Integer.toString(total_pages));

                    float donePages = (float) done_pages;
                    float totalPages = (float) total_pages;

                    float division = donePages/totalPages;

                    //uSystem.out.println("TOTAL PAGES = " + total_pages);

                    //System.out.println("Done PAGES = " + done_pages);

                    //System.out.println(" DIVISON = " + division);

                    float progress_division = division*100;
                    //System.out.println("PROGRESSSSSSSSSSSSSSS DIVISON = " + progress_division);
                    int progress = Math.round(progress_division);
                    //System.out.println("PROGRESSSSSSSSSSSSSSS = " + progress);
                    simpleProgressBar.setProgress(progress);

                }
            }


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLocal = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intentLocal);
            }
        });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


            View headerView = navigationView.getHeaderView(0);

            TextView user_name = (TextView) headerView.findViewById(R.id.user_name);
            TextView textView = (TextView) headerView.findViewById(R.id.textView);


            user_name.setText(username);
            textView.setText(dbHandler.getUser(username).get_email());

            showHistory();

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
            getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            if (id == R.id.nav_history) {
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            int id = item.getItemId();


            if (id == R.id.nav_history) {
                System.out.println("HISTORY CLICKED");
                drawer.closeDrawers();
                Intent intent1 = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent1);
                return true;


            } else if (id == R.id.nav_action_settings) {

                Intent intent2 = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent2);
                return true;

            } else if (id == R.id.nav_send) {

            }

            System.out.println("could not find");
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


        //show progress for given book

        private void showHistory(){


            List<History> allHistory = dbHandler.getAllHistory();
            List<Books> allBooks = dbHandler.getAllBooks();


            LinearLayout layout = (LinearLayout) findViewById(R.id.loopLinear);


            //For loop history records
//        layout.addView(new LinearLayout(getApplicationContext()));
            for (Books book : allBooks){
//            System.out.println("  " + book.get_book_name());
                //dymanic variables
                String date = book.get_book_deadline();
                String bookName = book.get_book_name();
                String bookTotalPages = Integer.toString(book.get_book_total_pages());
                String bookDonePages = Integer.toString(book.get_book_done_pages());
                System.out.println("date = " + date);
                System.out.println("bookName = " + bookName);
                System.out.println("bookTotalPages = " + bookTotalPages);
                System.out.println("bookDonePages = " + bookDonePages);


                LinearLayout child1 = new LinearLayout(getApplicationContext());
                child1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                child1.setOrientation(LinearLayout.HORIZONTAL);

                child1.setPadding(30,0,0,0);

                TextView textView1 = new TextView(this);
                textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textView1.setPadding(20,0,0,0);
                textView1.setTextSize(20);
                textView1.setText("|");
                TextView textView2 = new TextView(this);
                textView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textView2.setPadding(80,0,0,0);
                textView2.setTextSize(20);
                textView2.setText(date);
                child1.addView(textView1);
                child1.addView(textView2);

                LinearLayout child2 = new LinearLayout(getApplicationContext());
                child2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                child2.setOrientation(LinearLayout.HORIZONTAL);

                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.image13);
                imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(150,150));
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(80, 60);
                imageView.setMaxHeight(50);
                imageView.setMaxWidth(50);
//            lp.setMargins(3, 5 , 0, 0);
//            imageView.setLayoutParams(lp);

                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);


//            child2.addView(imageView);

                LinearLayout child22 = new LinearLayout(getApplicationContext());
                child22.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                child22.setOrientation(LinearLayout.VERTICAL);
                child22.setPadding(30,0,0,0);


                TextView textView21 = new TextView(this);
                textView21.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textView21.setPadding(50,0,0,0);
//            textView21.setTextSize(20);
                textView21.setText("Book: "+bookName);

                TextView textView22 = new TextView(this);
                textView22.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textView22.setPadding(50,0,0,0);
//            textView22.setTextSize(20);
                textView22.setText("Done: "+bookDonePages);

                TextView textView23 = new TextView(this);
                textView23.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                textView23.setPadding(50,0,0,0);
//            textView23.setTextSize(20);
                textView23.setText("Total: "+bookTotalPages);

                child22.addView(textView21);
                child22.addView(textView22);
                child22.addView(textView23);
                child2.addView(imageView);
                child2.addView(child22);


                layout.addView(child1);
                layout.addView(child2);
            }




            //number of pages have been finished


            //number of pages on total

            //calculate percentage

            //display result

            //optional add more information depending on character

        }

}
