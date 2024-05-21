package com.example.basketball_quiz.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addQuestion(Question question);

    @Insert
    public void addCategory(Category category);

    @Insert
    public void addArticle(Article article);





    @Query("select * from questions ")
    public List<Question> getQuestions();

    @Query("select id from questions ")
    public List<Integer> getQIDS();

    @Query("select id from categories")
    public List<Integer> getCIDS();

    @Query("select id from articles")
    public List<Integer> getAIDS();

    @Query("select * from categories")
    public List<Category> getCategories();

    @Query("select * from  articles")
    public List<Article> getArticles();

    @Query("select * from questions where cid = :cid")
    public List<Question> getQuestionsCat(int cid);

    @Query("select distinct a.id as field1 ,a.Title as field2 from questions q  Join articles a on q.aid=a.id where cid = :acid")
    public List<ResultIntString> getArticlesByCid(int acid);

    @Query("select COUNT(*) as field1, c.categoryName as field2 from questions q  Join CATEGORIES c on q.cid=c.id group by q.cid")
    public List<ResultIntString> getCountCid();

    @Query("select id as field1,question_text as field2 from questions where question_text like '%' || :input || '%'")
            public List<ResultIntString> getQuestionText(String input);


    @Delete
    public void deleteQuestion(Question question);

    @Delete
    public void deleteCat(Category cat);

    @Delete
    public void deleteArt(Article art);

    @Update
    public void updateQuestion(Question question);

    @Update
    public void updateCategory(Category cat);

    @Update
    public void updateArticle(Article art);
}
