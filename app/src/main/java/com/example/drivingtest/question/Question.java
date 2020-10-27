package com.example.drivingtest.question;

import java.io.Serializable;

public class Question implements Serializable {
    private int _id;
    private String Question;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;
    private String Result;
    private int    Score;
    private String TypeLicense;
    private String Image;
    private String UserAnswer="";
    public int choiceID=-1;// Support to check radiogroup's ID

    public Question(int _id, String question, String answer1, String answer2, String answer3, String answer4, String result, int score, String typeLicense, String image,String UserAnswer) {
        this._id = _id;
        Question = question;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
        Result = result;
        Score = score;
        TypeLicense = typeLicense;
        Image = image;
        this.UserAnswer= UserAnswer;
    }

    public Question() {
    }

    public String getUserAnswer() {
        return UserAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        UserAnswer = userAnswer;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setAnswer4(String answer4) {
        Answer4 = answer4;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getTypeLicense() {
        return TypeLicense;
    }

    public void setTypeLicense(String typeLicense) {
        TypeLicense = typeLicense;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

}
