package com.example.basketball_quiz.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "questions",
        foreignKeys = {
        @ForeignKey(entity = Category.class,
        parentColumns = "id",
        childColumns = "cid",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Article.class,
                        parentColumns = "id",
                        childColumns = "aid",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)


})



public class Question {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "question_text")
    private String q_text;

    @ColumnInfo (name = "cid")
    private int cid;

    @ColumnInfo (name = "aid")
    private int aid;

    @ColumnInfo(name = "SL")
    private String sl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQ_text() {
        return q_text;
    }

    public void setQ_text(String name) {
        this.q_text = name;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

}
