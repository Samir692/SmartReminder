package com.example.android.smartreminder.database_sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.smartreminder.Contacts;

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
    private static final String TABLE_UPHOLDER = "upholder";
    private static final String TABLE_OBLIGER = "obliger";
    private static final String TABLE_REBEL = "rebel";
    private static final String TABLE_QUESTIONER = "questioner";


    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_NICK_NAME = "nick_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_SALT = "user_salt";
    private static final String COLUMN_USER_FILLED_QUIZ = "user_filled_quiz";

    // UPHOLDER Table Columns names
    private static final String COLUMN_USER_UPHOLDER_DEADLINE = "deadline";
    // OBLIGER Table Columns names
    private static final String COLUMN_USER_OBLIGER_PAGES = "pages";
    // REBEL Table Columns names
    private static final String COLUMN_USER_REBEL_REASON = "reason";
    // QUESTIONER Table Columns names
    private static final String COLUMN_USER_QUESTIONER_PURPOSE = "purpose";




    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT," + COLUMN_USER_NICK_NAME + " TEXT NOT NULL UNIQUE,"
            + COLUMN_USER_FILLED_QUIZ + " INTEGER,"
            + COLUMN_USER_EMAIL + " TEXT NOT NULL UNIQUE," + COLUMN_USER_PASSWORD + " BLOB,"
            + COLUMN_USER_SALT + " BLOB" + ")";

    private String CREATE_USER_UPHOLDER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_UPHOLDER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_UPHOLDER_DEADLINE + " TEXT"
            + ")";

    private String CREATE_USER_OBLIGER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_OBLIGER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_OBLIGER_PAGES + " INTEGER"
            + ")";
    private String CREATE_USER_REBEL_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_REBEL + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_REBEL_REASON + " TEXT"
            + ")";

    private String CREATE_USER_QUESTIONER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTIONER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_QUESTIONER_PURPOSE + " TEXT"
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

    public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_USER_TABLE);
    }

    public void createUPHOLDERtable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_USER_UPHOLDER_TABLE);
    }

    public void createOBLIGERtable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_USER_OBLIGER_TABLE);
    }

    public void createREBELtable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_USER_REBEL_TABLE);
    }

    public void createQUESTIONERtable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_USER_QUESTIONER_TABLE);
    }

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

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
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

     /*
     * This method to check user exist or not
     *
             * @param email
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
                COLUMN_USER_ID
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


}
