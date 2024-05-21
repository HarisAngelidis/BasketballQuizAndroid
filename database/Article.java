package com.example.basketball_quiz.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "articles")

public class Article {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "Title")
    private String aTitle;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getATitle() {
        return aTitle;
    }

    public void setATitle(String aTitle) {
        this.aTitle = aTitle;
    }


}
