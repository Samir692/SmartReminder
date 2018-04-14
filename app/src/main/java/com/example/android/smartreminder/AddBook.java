package com.example.android.smartreminder;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

import org.w3c.dom.Text;

public class AddBook extends AppCompatActivity {

    private DatabaseHandler dbHandler;
//uyfhtrr
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DatabaseHandler(AddBook.this);


        QuestionsAnswersCharacterBased questAnsChar = new QuestionsAnswersCharacterBased();


        final EditText book_name = (EditText) findViewById(R.id.book_name);
        final EditText total_page = (EditText) findViewById(R.id.total_page);
        final TextView bookQuestion1 = (TextView) findViewById(R.id.bookQuestion1);
        final EditText answer1 = (EditText) findViewById(R.id.answer1);
        final TextView bookQuestion2 = (TextView) findViewById(R.id.bookQuestion2);
        final EditText answer2 = (EditText) findViewById(R.id.answer2);
        final TextView bookQuestion3 = (TextView) findViewById(R.id.bookQuestion3);
        final EditText answer3 = (EditText) findViewById(R.id.answer3);
        final TextView bookQuestion4 = (TextView) findViewById(R.id.bookQuestion4);
        final EditText answer4 = (EditText) findViewById(R.id.answer4);
        final TextView bookQuestion5 = (TextView) findViewById(R.id.bookQuestion5);
        final EditText answer5 = (EditText) findViewById(R.id.answer5);

        final TextView deadline = (TextView) findViewById(R.id.deadline);
        Button btnSetDeadline = (Button) findViewById(R.id.btnSetDeadline);



        //create questions

        switch () {

            case :
                break;

            case :
                break;

            case :
                break;

            case :
                break;
            bookQuestion1.setText(questAnsChar.);

        }
        //get deadline from Calendar class
        btnSetDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBook.this, CalendarActivity.class);
                intent.putExtra("className","com.example.android.smartreminder.AddBook");
                startActivity(intent);
            }
        });

        Intent incoming = getIntent();
        final String date = incoming.getStringExtra("date");
        System.out.println("got date = " + date);
        deadline.setText(date);
        //working example
        //add Book properties
        Button addBook = (Button) findViewById(R.id.addBook);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("transition", "adding book to database");
                String answer1Text = answer1.getText().toString();


                //TODO restrict editText to only integers
                //add Book
                Books book = new Books();
                String bookName = book_name.getText().toString();
                int totalPage = Integer.parseInt(total_page.getText().toString());
                String ANSWER1 = answer1.getText().toString();
                String ANSWER2 = answer2.getText().toString();
                String ANSWER3 = answer3.getText().toString();
                String ANSWER4 = answer4.getText().toString();
                String ANSWER5 = answer5.getText().toString();


                book.set_book_name(bookName);
                book.set_book_total_pages(totalPage);
                book.set_book_done_pages(0);
                book.set_book_deadline(date);

                switch (character){
                    case "questionerre":


                        break;
                    case "questionerre":

                        break;
                    case "questionerre":


                        break;
                    case "questionerre":


                        break;
                }

                if(dbHandler.addBook(book) > 0){
                    Intent intent = new Intent(AddBook.this, BooksListActivity.class);
                    intent.putExtra("book","added");
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddBook.this, R.string.error_add_book, Toast.LENGTH_LONG).show();
                }

                //add book information depending on category

            }
        });


    }

}
