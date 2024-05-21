package com.example.basketball_quiz.database;

import androidx.room.ColumnInfo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "categories")

public class Category {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "categoryName")
    private String CName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String cName) {
        this.CName = cName;
    }

}

