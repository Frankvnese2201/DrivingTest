package com.example.drivingtest.question;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class QuestionController {
    //Create DataBase Helper with input value Context
    private  DBHelper dbHelper;
    public QuestionController(Context context)
    {
        dbHelper= new DBHelper(context);
    }
    //Get Question From database
    public ArrayList<Question> getQuestion(String TypeLicense)
        {
            ArrayList<Question> listQuestion=new ArrayList<Question>();
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            Cursor cursor=db.rawQuery("Select * From drivingtest Where TypeLicense='"+TypeLicense+"'",null);
            cursor.moveToFirst();
            //add data to ArrayList
            do {
                Question item;
                item= new Question(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9),"");
                listQuestion.add(item);
            } while (cursor.moveToNext()); //until Read all data
            return listQuestion;
        }
}
