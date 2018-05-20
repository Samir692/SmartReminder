package com.example.android.smartreminder;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import java.util.List;

public class HistoryActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DatabaseHandler dbHandler = new DatabaseHandler(HistoryActivity.this);


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










    }



}
