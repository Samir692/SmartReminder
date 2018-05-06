package com.example.android.smartreminder.database_sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.smartreminder.Books;
import com.example.android.smartreminder.Contacts;
import com.example.android.smartreminder.History;
import com.example.android.smartreminder.QuestionsAnswersCharacterBased;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samir692 on 3/4/18.
 */

public class DatabaseHandler  extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "usersManager";


    // Tables names
    private static final String TABLE_CONTACTS = "contacts";

    private static final String TABLE_HISTORY = "history";
    private static final String TABLE_BOOKS = "books";
    private static final String TABLE_QUESTIONS_ANSWERS = "questions_answers_character_based";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_NICK_NAME = "nick_name";
    private static final String COLUMN_USER_BOOK_NAME = "book_name";
    private static final String COLUMN_USER_PERSONALITY_TYPE = "personality_type";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_SALT = "user_salt";
    private static final String COLUMN_USER_FILLED_QUIZ = "user_filled_quiz";


    // History Table Columns names
    private static final String COLUMN_USER_HISTORY_ID = "id";
    private static final String COLUMN_USER_HISTORY_NICK_NAME = "user_nick_name";
    private static final String COLUMN_USER_HISTORY_BOOK_NAME = "book_name";
    private static final String COLUMN_USER_HISTORY_BOOK_TOTAL_PAGES = "total_pages";
    private static final String COLUMN_USER_HISTORY_BOOK_DONE_PAGES = "done_pages";
    private static final String COLUMN_USER_HISTORY_BOOK_CREATED = "created";

    // BOOKS Table Columns names
    private static final String COLUMN_USER_BOOKS_ID = "id";
    private static final String COLUMN_USER_BOOKS_NAME = "name";
    private static final String COLUMN_USER_BOOKS_CREATED = "created";
    private static final String COLUMN_USER_BOOKS_TOTAL_PAGES = "total_pages";
    private static final String COLUMN_USER_BOOKS_DONE_PAGES = "done_pages";
    private static final String COLUMN_USER_BOOKS_DEADLINE = "deadline";


    // Character questions and answers Table Columns names
    private static final String COLUMN_USER_QUEST_ANSW_ID = "id";
    private static final String COLUMN_USER_QUEST_ANSW_CHARACHTER = "character";
    private static final String COLUMN_USER_QUEST_ANSW_QUESTIONS = "questions";
    private static final String COLUMN_USER_QUEST_ANSW_ANSWERS = "answers";
    private static final String COLUMN_USER_QUEST_ANSW_CREATED = "created";


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
            + COLUMN_USER_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME        + " TEXT," + COLUMN_USER_NICK_NAME + " TEXT NOT NULL UNIQUE,"
            + COLUMN_USER_FILLED_QUIZ + " INTEGER,"
            + COLUMN_USER_BOOK_NAME   + " TEXT,"
            + COLUMN_USER_PERSONALITY_TYPE + " TEXT,"
            + COLUMN_USER_EMAIL       + " TEXT NOT NULL UNIQUE," + COLUMN_USER_PASSWORD + " BLOB,"
            + COLUMN_USER_SALT        + " BLOB" + ")";

    // create table sql query
    private String CREATE_USER_HISTORY = "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY + "("
            + COLUMN_USER_HISTORY_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_HISTORY_NICK_NAME + " TEXT NOT NULL UNIQUE,"
            + COLUMN_USER_HISTORY_BOOK_NAME   + " TEXT,"
            + COLUMN_USER_HISTORY_BOOK_DONE_PAGES + " INTEGER,"
            + COLUMN_USER_HISTORY_BOOK_TOTAL_PAGES + " INTEGER,"
            + COLUMN_USER_HISTORY_BOOK_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";


    private String CREATE_USER_QUESTIONS_ANSWERS_CHARACTER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTIONS_ANSWERS + "("
            + COLUMN_USER_QUEST_ANSW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_QUEST_ANSW_CHARACHTER + " TEXT,"
            + COLUMN_USER_QUEST_ANSW_QUESTIONS + " TEXT,"
            + COLUMN_USER_QUEST_ANSW_ANSWERS + " TEXT,"
            + COLUMN_USER_QUEST_ANSW_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    private String CREATE_USER_BOOKS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + "("
            + COLUMN_USER_BOOKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_BOOKS_NAME + " TEXT,"
            + COLUMN_USER_BOOKS_DONE_PAGES + " INTEGER,"
            + COLUMN_USER_BOOKS_TOTAL_PAGES + " INTEGER,"
            + COLUMN_USER_BOOKS_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + COLUMN_USER_BOOKS_DEADLINE + " TEXT"
            + ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_CONTACTS;

    //constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //deleteDb(context);
    }


    public void deleteDb(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create db's here
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_USER_BOOKS_TABLE);
        db.execSQL(CREATE_USER_QUESTIONS_ANSWERS_CHARACTER_TABLE);
        db.execSQL(CREATE_USER_HISTORY);

    }

    public void dropAndCreate() {
        //Drop User Table if exist
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);
    }

//    public void createTable(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(CREATE_USER_TABLE);
//    }

//    public void createUPHOLDERtable() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(CREATE_USER_UPHOLDER_TABLE);
//    }
//
//    public void createOBLIGERtable() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(CREATE_USER_OBLIGER_TABLE);
//    }
//
//    public void createREBELtable() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(CREATE_USER_REBEL_TABLE);
//    }
//
//    public void createQUESTIONERtable() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL(CREATE_USER_QUESTIONER_TABLE);
//    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(Contacts user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.get_username());
        values.put(COLUMN_USER_NICK_NAME, user.get_nick_name());
        values.put(COLUMN_USER_EMAIL, user.get_email());
        values.put(COLUMN_USER_PASSWORD, user.get_password());
        values.put(COLUMN_USER_SALT, user.get_salt());
        values.put(COLUMN_USER_FILLED_QUIZ, user.getFilled_quiz());
        values.put(COLUMN_USER_BOOK_NAME, "EMPTY");
        values.put(COLUMN_USER_PERSONALITY_TYPE, "EMPTY");

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    /**
     * This method is to create book record
     *
     * @param book
     */
    public long addBook(Books book) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_BOOKS_NAME, book.get_book_name());
        values.put(COLUMN_USER_BOOKS_TOTAL_PAGES, book.get_book_total_pages());
        values.put(COLUMN_USER_BOOKS_DONE_PAGES, book.get_book_done_pages());
        values.put(COLUMN_USER_BOOKS_DEADLINE, book.get_book_deadline());

        // Inserting Row
        long id = db.insert(TABLE_BOOKS, null, values);
        db.close();
        return  id;
    }

    /**
     * This method is to create history record
     *
     * @param history
     */
    public long addHistory(History history) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_HISTORY_NICK_NAME, history.get_nick_name());
        values.put(COLUMN_USER_HISTORY_BOOK_NAME, history.get_book_name());
        values.put(COLUMN_USER_HISTORY_BOOK_TOTAL_PAGES, history.get_book_total_pages());
        values.put(COLUMN_USER_HISTORY_BOOK_DONE_PAGES, history.get_book_done_pages());

        // Inserting Row
        long id = db.insert(TABLE_HISTORY, null, values);
        db.close();
        return  id;
    }

    /**
     * This method is to create QUESTIONS ANSWERS TABLE ITEM
     * @param question
     * @param answer
     * @param personality_type
     */
    public void addQuestionAnswer(String question, String answer, String personality_type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_QUEST_ANSW_CHARACHTER, personality_type);
        values.put(COLUMN_USER_QUEST_ANSW_QUESTIONS, question);
        values.put(COLUMN_USER_QUEST_ANSW_ANSWERS, answer);

        // Inserting Row
        db.insert(TABLE_QUESTIONS_ANSWERS, null, values);
        db.close();
    }
    /**
     * This method is to get all values in Questions and Answers table
     */
    public List<String> getAllQuestionAnswer() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_QUEST_ANSW_CHARACHTER,
                COLUMN_USER_QUEST_ANSW_QUESTIONS,
                COLUMN_USER_QUEST_ANSW_ANSWERS
        };


        // sorting orders
        String sortOrder =
                COLUMN_USER_QUEST_ANSW_CHARACHTER + " ASC";
        List<String> qaList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the table of questions and answers
        /**
         * SELECT character, question, answer FROM user ORDER BY character;
         */
        Cursor cursor = db.query(TABLE_QUESTIONS_ANSWERS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String character_q_a="";
                character_q_a+=cursor.getString(cursor.getColumnIndex(COLUMN_USER_QUEST_ANSW_CHARACHTER));
                character_q_a+=cursor.getString(cursor.getColumnIndex(COLUMN_USER_QUEST_ANSW_QUESTIONS));
                character_q_a+=cursor.getString(cursor.getColumnIndex(COLUMN_USER_QUEST_ANSW_ANSWERS));
                qaList.add(character_q_a);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return qaList;
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(Contacts user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_CONTACTS, COLUMN_USER_NICK_NAME + " = ?",
                new String[]{user.get_nick_name()});
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Contacts> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_NICK_NAME,
                COLUMN_USER_PASSWORD
        };


        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<Contacts> userList = new ArrayList<Contacts>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_CONTACTS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contacts user = new Contacts();
                //user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.set_nick_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NICK_NAME)));
                user.set_email(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.set_password(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }


    /**
     * This method is to fetch all history and return the list of history records
     *
     * @return list
     */
    public List<History> getAllHistroy() {
        // array of columns to fetch


        String[] columns = {
                COLUMN_USER_HISTORY_NICK_NAME,
                COLUMN_USER_HISTORY_BOOK_NAME,
                COLUMN_USER_HISTORY_BOOK_DONE_PAGES,
                COLUMN_USER_HISTORY_BOOK_TOTAL_PAGES,
                COLUMN_USER_HISTORY_BOOK_CREATED
        };


        // sorting orders
        String sortOrder =
                COLUMN_USER_HISTORY_BOOK_CREATED + " DESC";
        List<History> histroyList = new ArrayList<History>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_HISTORY, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                //user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                history.set_book_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HISTORY_BOOK_NAME)));
                history.set_book_done_pages(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HISTORY_BOOK_DONE_PAGES))));
                history.set_book_total_pages(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HISTORY_BOOK_TOTAL_PAGES))));
                history.set_book_created(cursor.getString(cursor.getColumnIndex(COLUMN_USER_HISTORY_BOOK_CREATED)));

                // Adding book record to list
                histroyList.add(history);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return histroyList;
    }


    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Books> getAllBooks() {
        // array of columns to fetch

        String[] columns = {
                COLUMN_USER_BOOKS_NAME,
                COLUMN_USER_BOOKS_TOTAL_PAGES,
                COLUMN_USER_BOOKS_DONE_PAGES,
                COLUMN_USER_BOOKS_DEADLINE
        };


        // sorting orders
        String sortOrder =
                COLUMN_USER_BOOKS_CREATED + " DESC";
        List<Books> booksList = new ArrayList<Books>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_BOOKS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Books book = new Books();
                //user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                book.set_book_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOKS_NAME)));
                book.set_book_done_pages(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOKS_DONE_PAGES))));
                book.set_book_total_pages(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOKS_TOTAL_PAGES))));
                book.set_book_created(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOKS_CREATED)));
                book.set_book_deadline(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOKS_DEADLINE)));
                // Adding book record to list
                booksList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return booksList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(Contacts user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.get_username());
        values.put(COLUMN_USER_NICK_NAME, user.get_nick_name());
        values.put(COLUMN_USER_EMAIL, user.get_email());
        values.put(COLUMN_USER_PASSWORD, user.get_password());

        // updating row
        db.update(TABLE_CONTACTS, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.get_id())});
        db.close();
    }

    /**
     * This method to update user record
     *
     * @param book
     */
    public void updateBook(Books book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_BOOKS_NAME, book.get_book_name());
        values.put(COLUMN_USER_BOOKS_DONE_PAGES, book.get_book_done_pages());
        values.put(COLUMN_USER_BOOKS_TOTAL_PAGES, book.get_book_total_pages());
        values.put(COLUMN_USER_BOOKS_DEADLINE, book.get_book_deadline());

        // updating row
        db.update(TABLE_BOOKS, values, COLUMN_USER_BOOKS_ID + " = ?",
                new String[]{String.valueOf(book.get_id())});
        db.close();
    }


     /*
     * This method to check user exist or not
     *
     * @param emailuser
     * @param nick_name
     * @return true/false
     */
    public boolean checkUser(String email, String nick_name, int wh) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " OR " + COLUMN_USER_NICK_NAME + " = ?";

        // selection argument
        String[] selectionArgs = {email, nick_name};

        // query user table with condition
        /**
         * SELECT user_id FROM user WHERE user_email = 'user@inf.elte.hu';
         */
        Cursor cursor = db.query(TABLE_CONTACTS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param nickname
     * @return Contacts
     */
    public Contacts getPassAndSalt(String nickname) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_SALT
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NICK_NAME + " = ?";

        // selection arguments
        String[] selectionArgs = {nickname};

        // query user table with conditions
        /**
         * SELECT user_id FROM user WHERE user_nickname = 'user692';
         */
        Cursor cursor = db.query(TABLE_CONTACTS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        //System.out.println("LENGTH OF CURSOSRRRRRRRRRRRR = " +  cursorCount);

        Contacts user = new Contacts();

        if (cursorCount == 1) {
            if (cursor.moveToFirst()) {
                do {
                    //System.out.println("COULD GET USERRRRRRRRRRRRRRRRRRRR");
                    user.set_id((Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)))));
                    user.set_password((cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PASSWORD))));
                    user.set_salt((cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_SALT))));
                    //USER.set_hear_rate((cursor.getInt(cursor.getColumnIndex(COLUMN_USER_RATE))));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return user;
        }

        return user;

    }

    /**
     * This method fetches user and returns user records
     *
     * @param nickname
     * @return user
     */
    public Contacts getUser(String nickname) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_BOOK_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        if (nickname == null){
            // System.out.println("USERNAMEEEEEEEEEEEEEEEEEEEEE" + nickname);
            return new Contacts();
        }
        String selection = COLUMN_USER_NICK_NAME + " = ?" ;

        // selection arguments
        String[] selectionArgs = {nickname};

        // query user table with conditions
        /**
         * SELECT user_id FROM user WHERE user_nickname = 'user692';
         */
        Cursor cursor = db.query(TABLE_CONTACTS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        Contacts user = new Contacts();

        if (cursorCount == 1) {
            if (cursor.moveToFirst()) {
                do {

                    user.set_id((Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)))));
                    user.set_book_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOK_NAME)));
                    //USER.set_hear_rate((cursor.getInt(cursor.getColumnIndex(COLUMN_USER_RATE))));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return user;
        }

        System.out.print("Duplicated Users");
        return user;
    }


    /**
     * This method fetches user and returns user records
     *
     * @param bookname
     * @return book
     */
    public Books getBook(String bookname) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_BOOKS_DEADLINE,
                COLUMN_USER_BOOKS_DONE_PAGES,
                COLUMN_USER_BOOKS_TOTAL_PAGES
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        if (bookname == null){
            // System.out.println("USERNAMEEEEEEEEEEEEEEEEEEEEE" + nickname);
            return new Books();
        }
        String selection = COLUMN_USER_BOOKS_NAME+ " = ?" ;

        // selection arguments
        String[] selectionArgs = {bookname};

        // query user table with conditions
        /**
         * SELECT user_id FROM user WHERE user_nickname = 'user692';
         */
        Cursor cursor = db.query(TABLE_BOOKS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        Books book = new Books();

        if (cursorCount == 1) {
            if (cursor.moveToFirst()) {
                do {
                    book.set_book_deadline(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOKS_DEADLINE)));
                    book.set_book_total_pages((Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOKS_TOTAL_PAGES)))));
                    book.set_book_done_pages((Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOKS_DONE_PAGES)))));
                    //book.set_id((Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)))));
                    //book.set_book_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOK_NAME)));
                    //USER.set_hear_rate((cursor.getInt(cursor.getColumnIndex(COLUMN_USER_RATE))));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return book;
        }

        System.out.print("Duplicated Users");
        return book;
    }

    /**
     * This method is to save the value if quiz registered
     *
     * @param user
     */
    public void addUserQuizValue(Contacts user) {

        //get User's ID
        Contacts user_with_id = getUser(user.get_nick_name());
        int uID = user_with_id.get_id();


        //update row id number uID
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FILLED_QUIZ, user.getFilled_quiz());

        // updating row
        db.update(TABLE_CONTACTS, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(uID)});
        db.close();
    };


    /**
     * This method is to check if user has filled quiz
     * @param nickname
     * @return user
     */
    public Contacts getFilledQuiz(String nickname) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_FILLED_QUIZ
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        if (nickname == null) {
            // System.out.println("USERNAMEEEEEEEEEEEEEEEEEEEEE" + nickname);
            return new Contacts();
        }
        String selection = COLUMN_USER_NICK_NAME + " = ?";

        // selection arguments
        String[] selectionArgs = {nickname};

        // query user table with conditions
        /**
         * SELECT COLUMN_USER_FILLED_QUIZ FROM user WHERE user_nickname = 'user692';
         */
        Cursor cursor = db.query(TABLE_CONTACTS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        Contacts user = new Contacts();

        if (cursorCount == 1) {
            if (cursor.moveToFirst()) {
                do {
                    user.setFilled_quiz((Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FILLED_QUIZ)))));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return user;

        }
        System.out.print("Duplicated Users");
        return user;
    }

    /**
     * This method is to add personality of user
     *
     * @param user
     */
    public void addPersonalityTypeValue(Contacts user) {

        //get User's ID
        Contacts user_with_id = getUser(user.get_nick_name());
        int uID = user_with_id.get_id();


        //update row id number uID
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_PERSONALITY_TYPE, user.get_personality_type());

        // updating row
        db.update(TABLE_CONTACTS, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(uID)});
        db.close();
    };


    /**
     * This method is to check if user has filled quiz
     * @param nickname
     * @return user
     */
    public Contacts getPersonalityType(String nickname) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_PERSONALITY_TYPE
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        if (nickname == null) {
            return new Contacts();
        }
        String selection = COLUMN_USER_NICK_NAME + " = ?";

        // selection arguments
        String[] selectionArgs = {nickname};

        // query user table with conditions
        /**
         * SELECT COLUMN_USER_FILLED_QUIZ FROM user WHERE user_nickname = 'user692';
         */
        Cursor cursor = db.query(TABLE_CONTACTS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        Contacts user = new Contacts();

        if (cursorCount == 1) {
            if (cursor.moveToFirst()) {
                do {
                    user.set_personality_type(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PERSONALITY_TYPE)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return user;

        }
        System.out.print("Duplicated Users");
        return user;
    }


    /**
     * This method is to add personality of user
     *
     * @param user
     */
    public void addBookNameValue(Contacts user) {

        //get User's ID
        Contacts user_with_id = getUser(user.get_nick_name());
        int uID = user_with_id.get_id();


        //update row id number uID
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_BOOK_NAME, user.get_book_name());

        // updating row
        db.update(TABLE_CONTACTS, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(uID)});
        db.close();
    };

    /**
     * This method is to check if user has filled quiz
     * @param nickname
     * @return user
     */
    public Contacts getUsersBookName(String nickname) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_BOOK_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        if (nickname == null) {
            return new Contacts();
        }
        String selection = COLUMN_USER_NICK_NAME + " = ?";

        // selection arguments
        String[] selectionArgs = {nickname};

        // query user table with conditions
        /**
         * SELECT COLUMN_USER_FILLED_QUIZ FROM user WHERE user_nickname = 'user692';
         */
        Cursor cursor = db.query(TABLE_CONTACTS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        Contacts user = new Contacts();

        if (cursorCount == 1) {
            if (cursor.moveToFirst()) {
                do {
                    user.set_book_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BOOK_NAME)));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return user;

        }
        System.out.print("Duplicated Users");
        return user;
    }



}
