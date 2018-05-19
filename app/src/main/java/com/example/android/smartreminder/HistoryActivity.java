package com.example.android.smartreminder;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHandler dbHandler = new DatabaseHandler(HistoryActivity.this);



        List<Books> allBooks = dbHandler.getAllBooks();
        //create defalt page

        //Scroll view
//        ScrollView parent = new ScrollView(context);
//        parent.setLayoutParams(new ScrollView.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
//        parent.setOrientation(ScrollView.SCROLL_AXIS_VERTICAL);
//        //LinearView view
//        LinearLayout parent2 = new LinearLayout(context);
//        parent2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//        parent2.setOrientation(LinearLayout.HORIZONTAL);
//        //History Text head
//        TextView tv1 = new TextView(context);
//        parent2.addView(tv1);
        // Loop head
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:layout_marginTop="30dp"
//        android:orientation="vertical">

//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(3, 300, 3, 3);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setLayoutParams(params);

        LinearLayout layout = (LinearLayout)findViewById(R.id.loopLinear);
        //For loop history records
        for (Books book : allBooks){
            //TODO replace xml to java in order to create dynamic view
//            LinearLayout parent = new LinearLayout(getApplicationContext());
//            parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//            parent.setOrientation(LinearLayout.VERTICAL);

            //dymanic variables
            String date = book.get_book_deadline();
            String bookName = book.get_book_name();
            String bookTotalPages = Integer.toString(book.get_book_total_pages());
            String bookDonePages = Integer.toString(book.get_book_done_pages());

            LinearLayout child1 = new LinearLayout(getApplicationContext());
            child1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            child1.setOrientation(LinearLayout.HORIZONTAL);
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView1.setPadding(20,0,0,0);
            textView1.setTextSize(20);
            textView1.setText("|");
            TextView textView2 = new TextView(this);
            textView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView2.setPadding(50,0,0,0);
            textView2.setTextSize(20);
            textView2.setText(date);
            child1.addView(textView1);
            child1.addView(textView2);

            LinearLayout child2 = new LinearLayout(getApplicationContext());
            child2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            child2.setOrientation(LinearLayout.HORIZONTAL);
            ImageView imageView = new ImageView(this);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(3, 300, 3, 3);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setLayoutParams(params);
//            imageView.set
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(3, 5 , 0, 0);
            imageView.setMaxHeight(50);
            imageView.setMaxWidth(50);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            imageView.setImageBitmap(Bitmap.createBitmap());
            imageView.setLayoutParams(lp);

            LinearLayout child22 = new LinearLayout(getApplicationContext());
            child22.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            child22.setOrientation(LinearLayout.VERTICAL);
            child22.setPadding(30,0,0,0);


            TextView textView21 = new TextView(this);
            textView21.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            textView21.setPadding(50,0,0,0);
//            textView21.setTextSize(20);
            textView21.setText(bookName);

            TextView textView22 = new TextView(this);
            textView22.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            textView22.setPadding(50,0,0,0);
//            textView22.setTextSize(20);
            textView22.setText(bookDonePages);

            TextView textView23 = new TextView(this);
            textView23.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            textView23.setPadding(50,0,0,0);
//            textView23.setTextSize(20);
            textView23.setText(bookTotalPages);

            child22.addView(textView21);
            child22.addView(textView22);
            child22.addView(textView23);
            child2.addView(imageView);
            child2.addView(child22);


            layout.addView(child1);
        }







        setContentView(R.layout.activity_history);
    }
}
