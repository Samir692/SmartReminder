package com.example.android.smartreminder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.test.InstrumentationRegistry;

import com.example.android.health_in_time.database_sql.DatabaseHandler;
import com.example.android.smartreminder.database_sql.DatabaseHandler;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.SecureRandom;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseHandlerInstrumentedTest{

    private DatabaseHandler mDataSource;

    @Before
    public void setUp(){
        mDataSource = new DatabaseHandler(InstrumentationRegistry.getTargetContext());
        Assert.assertNotNull(mDataSource);
        mDataSource.dropAndCreate();
    }

    @After
    public void finish() {
        mDataSource.close();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(mDataSource);
    }

    @Test
    public void testDeleteUser(){

        String name = "Firstname";
        String username = "Nickname";
        String emailAddress = "firstname.lastname@g.com";
        String password = "password";
        String salt = "password";
        int n_filled_quiz=0;

        Contacts user = new Contacts();
        user.set_username(name);
        user.set_nick_name(username);
        user.set_email(emailAddress);
        user.setFilled_quiz(n_filled_quiz);

        mDataSource.addUser(user);
        List<Contacts> allusers = mDataSource.getAllUser();
        assertThat(allusers.size(), greaterThan(0));

        mDataSource.deleteUser(user);
        allusers = mDataSource.getAllUser();
        assertThat(allusers.size(), is(0));

    }


    @Test
    public void testAddUser(){
        String name = "Firstname";
        String username = "Nickname";
        String emailAddress = "firstname.lastname@g.com";


        Contacts user = new Contacts();
        user.set_username(name);
        user.set_nick_name(username);
        user.set_email(emailAddress);

        mDataSource.addUser(user);
        List<Contacts> allusers = mDataSource.getAllUser();
        assertThat(allusers.size(), is(1));
    }

    @Test
    public void testAddBook(){

        Books book = new Books();
        book.set_id(1);
        book.set_book_name("Pride and Prejudice");
        book.set_book_total_pages(50);
        book.set_book_done_pages(0);
        book.set_book_deadline("21.05.2018");
        book.set_book_created("20.05.2018");

        mDataSource.addBook(book);

        List<Books> allBooks = mDataSource.getAllBooks();
        assertThat(allBooks.size(), is(1));
    }



    @Test
    public void testCheckUser(){

        final SecureRandom rand = new SecureRandom();
        String name = "Firstname";
        String username = "Nickname";
        String emailAddress = "firstname.lastname@g.com";
        byte[] password = new byte[4];
        rand.nextBytes(password);
        byte[] salt = new byte[4];
        rand.nextBytes(salt);

        Contacts user = new Contacts();
        user.set_username(name);
        user.set_nick_name(username);
        user.set_email(emailAddress);
        user.set_password(password);
        user.set_salt(salt);

        mDataSource.addUser(user);
        Contacts user_get_id = mDataSource.getUser(username);
        Contacts user_res = mDataSource.getPassAndSalt(username);

        assertEquals(mDataSource.checkUser(emailAddress, username, 1), true);
        assertEquals(user_res.get_password().length, 4);
        assertThat(user_get_id.get_id(), greaterThan(0));

    }
}
