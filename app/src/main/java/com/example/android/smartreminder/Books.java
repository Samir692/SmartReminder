package com.example.android.smartreminder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by samir692 on 3/29/18.
 */

public class Books {

    private int _id;
    private String _book_name;
    private int _book_total_pages;
    private int _book_done_pages;
    private String _book_created;
    private String _book_deadline;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_book_name() {
        return _book_name;
    }

    public void set_book_name(String _book_name) {
        this._book_name = _book_name;
    }

    public int get_book_total_pages() {
        return _book_total_pages;
    }

    public void set_book_total_pages(int _book_total_pages) {
        this._book_total_pages = _book_total_pages;
    }

    public int get_book_done_pages() {
        return _book_done_pages;
    }

    public void set_book_done_pages(int _book_done_pages) {
        this._book_done_pages = _book_done_pages;
    }

    public String get_book_deadline() {
        return _book_deadline;
    }

    public void set_book_deadline(String _book_deadline) {
        this._book_deadline = _book_deadline;
    }

    public String get_book_created() {
        return _book_created;
    }

    public void set_book_created(String _book_created) {
        this._book_created = _book_created;
    }
}
