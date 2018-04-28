package com.example.android.smartreminder;

/**
 * Created by samir692 on 4/28/18.
 */

public class History {

    private int _id;
    private String _nick_name;
    private String _book_name;
    private int _book_total_pages;
    private int _book_done_pages;
    private String _book_created;

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

    public String get_book_created() {
        return _book_created;
    }

    public void set_book_created(String _book_created) {
        this._book_created = _book_created;
    }
}
