package com.example.android.smartreminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHandler dbHandler = new DatabaseHandler(HistoryActivity.this);



        List<Books> allBooks = dbHandler.getAllBooks();

        for (Books book : allBooks){
            //TODO replace xml to java in order to create dynamic view
            LinearLayout child1 = new LinearLayout(getApplicationContext());
            child1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            child1.setOrientation(LinearLayout.VERTICAL);

            //dymanic variables
            String date = book.get_book_deadline();
            String bookName = book.get_book_name();
            String bookTotalPages = Integer.toString(book.get_book_total_pages());
            String bookDonePages = Integer.toString(book.get_book_done_pages());


        }







        setContentView(R.layout.activity_history);
    }
}
