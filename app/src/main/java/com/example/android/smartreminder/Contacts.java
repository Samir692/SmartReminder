package com.example.android.smartreminder;

/**
 * Created by samir692 on 3/3/18.
 */

public class Contacts {

    private int _id;
    private String _username;
    private String _nick_name;
    private String _book_name;
    private byte[] _password;
    private byte[] _salt;
    private String _email;
    private int filled_quiz;

    public Contacts(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nick_name() {
        return _nick_name;
    }

    public void set_nick_name(String _nick_name) {
        this._nick_name = _nick_name;
    }

    public byte[] get_password() {
        return _password;
    }

    public void set_password(byte[] _password) {
        this._password = _password;
    }

    public byte[] get_salt() {
        return _salt;
    }

    public void set_salt(byte[] _salt) {
        this._salt = _salt;
    }


    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public int getFilled_quiz() {
        return filled_quiz;
    }

    public void setFilled_quiz(int filled_quiz) {
        this.filled_quiz = filled_quiz;
    }

    public String get_book_name() {
        return _book_name;
    }

    public void set_book_name(String _book_name) {
        this._book_name = _book_name;
    }
}
