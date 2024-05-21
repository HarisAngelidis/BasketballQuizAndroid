package com.example.basketball_quiz.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class,Category.class,Article.class}, version = 3)

public abstract class MyAppDatabase extends RoomDatabase {
    public abstract MyDao myDao();

}
