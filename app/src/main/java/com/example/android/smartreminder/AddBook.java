package com.example.android.smartreminder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView bookQuestion1 = (TextView) findViewById(R.id.bookQuestion1);
        final EditText answer1 = (EditText) findViewById(R.id.answer1);

        Button addBook = (Button) findViewById(R.id.addBook);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("transition", "adding book to database");
                String answer1Text = answer1.getText().toString();

                //add Book
                Books book = new Books();
                book.set_book_name("book_name");
                book.set_book_total_pages(99);
                book.set_book_done_pages(0);
                book.set_book_deadline("11/12/2010");
                //add book information depending on category

            }
        });


    }

}
