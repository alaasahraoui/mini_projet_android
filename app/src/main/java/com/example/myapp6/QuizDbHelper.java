package com.example.myapp6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                com.example.myapp6.QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                com.example.myapp6.QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                com.example.myapp6.QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                com.example.myapp6.QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + com.example.myapp6.QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Where is Algeria situated ?", "Africa ", "Europe", "Asia", 1);
        addQuestion(q1);
        Question q2 = new Question("What is the capital of the Czech Republic ?", "Stockholm", "Prague", "Alger", 2);
        addQuestion(q2);
        Question q3 = new Question("What is the capital of Greece ?", "Berlin", "Nicosia", "Athens", 3);
        addQuestion(q3);
        Question q4 = new Question("What is the capital of japan", "Tokyo", "Blida", "Paris", 1);
        addQuestion(q4);
        Question q5 = new Question("What is the capital of Italy", "Tipaza", "Rome", "Istanbul", 2);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(com.example.myapp6.QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }


    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + com.example.myapp6.QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(com.example.myapp6.QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }




}