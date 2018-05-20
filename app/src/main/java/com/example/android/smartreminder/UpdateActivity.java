package com.example.android.smartreminder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.smartreminder.database_sql.DatabaseHandler;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final Context context = UpdateActivity.this;
        final DatabaseHandler dbHandler =  new DatabaseHandler(context);


        final EditText more_pages = (EditText) findViewById(R.id.update_pagedone);
        Button btnSubmit = (Button) findViewById(R.id.update_submit);
        final CoordinatorLayout coordLayoutUpdate = (CoordinatorLayout) findViewById(R.id.coordLayoutUpdate);

        more_pages.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String done_more_pages_str = more_pages.getText().toString();



                if(done_more_pages_str.matches("")) {
                    Snackbar.make(coordLayoutUpdate, getString(R.string.error_pages), Snackbar.LENGTH_LONG).show();
                }else {
                    try {
                        final int done_more_pages = Integer.parseInt(done_more_pages_str);

                        String username = LoginActivity.username;
                        Contacts user = dbHandler.getUser(username);

                        String bookName = user.get_book_name();

                        Books getBookDetails = dbHandler.getBook(bookName);

                        int pastDonePages = getBookDetails.get_book_done_pages();
                        int totalPages = getBookDetails.get_book_total_pages();
                        int bookID = getBookDetails.get_id();

                        int currentDonePages = pastDonePages + done_more_pages;
                        //check done pages conditions

                        if (currentDonePages >= totalPages) {
                            Log.d("Completion", "BOOK COMPLETED");

                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                            alertDialog.setTitle("CONGRUTILATIONS!");
                            alertDialog.setMessage("YOU HAVE FINISHED " + bookName + "!!");

                            alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context, "Good Job", Toast.LENGTH_SHORT).show();
                                }
                            });

                            AlertDialog alert = alertDialog.create();


                            // line you have to add
                            alert.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
                            alert.show();

                            //set Book to Empty
                            Contacts user_book = new Contacts();
                            user_book.set_nick_name(username);
                            user_book.set_book_name("EMPTY");

                            dbHandler.addBookNameValue(user_book);


                        }


                        //update pages of Book
                        Books updatedDonePages = new Books();
                        updatedDonePages.set_id(bookID);
                        updatedDonePages.set_book_name(bookName);
                        updatedDonePages.set_book_done_pages(currentDonePages);


                        if (dbHandler.updateDonePages(updatedDonePages) > 0) {
                            Log.d("Update", "PAGES OF BOOK HAS BEEN UPDATED");


                            //ADD to History
                            History history = new History();
                            history.set_nick_name(username);
                            history.set_book_name(bookName);
                            history.set_book_done_pages(currentDonePages);
                            history.set_book_total_pages(totalPages);

                            if (dbHandler.addHistory(history) > 0) {
                                Log.d("Add", "Action added to History");


                                Intent intent = new Intent(UpdateActivity.this, BooksListActivity.class);
                                intent.putExtra("bookPages", "added");
                                startActivity(intent);
                            }
                        }


                }catch(Exception e){
                    Snackbar.make(coordLayoutUpdate, "Invalid Number", Snackbar.LENGTH_LONG).show();
                }
                }

            }



        });
    }
}
