package com.zao.httpdownload;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/26 14:26
 */
public class Subjects {
    private String title, year, id;

    public Subjects(String title, String year, String id) {
        this.title = title;
        this.year = year;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getId() {
        return id;
    }
}