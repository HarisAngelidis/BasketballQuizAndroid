package com.example.basketball_quiz.database;

import com.google.firebase.firestore.DocumentReference;

public class Quiz {

    private DocumentReference qsid;
    private DocumentReference qcid;

    public DocumentReference getQsid() {
        return qsid;
    }

    public void setQsid(DocumentReference qsid) {
        this.qsid = qsid;
    }

    public DocumentReference getQcid() {
        return qcid;
    }

    public void setQcid(DocumentReference qcid) {
        this.qcid = qcid;
    }

    private String QuizName;

    private int QuestCat;

    public int getQuestCat() {
        return QuestCat;
    }

    public void setQuestCat(int questCat) {
        QuestCat = questCat;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String quizName) {
        QuizName = quizName;
    }
}
