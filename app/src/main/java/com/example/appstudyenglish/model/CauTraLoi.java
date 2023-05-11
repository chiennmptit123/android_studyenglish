package com.example.appstudyenglish.model;

public class CauTraLoi {
    private String titleAnswer;
    private boolean isAnswer;

    public CauTraLoi(){

    }

    public CauTraLoi(String titleAnswer, boolean isAnswer) {
        this.titleAnswer = titleAnswer;
        this.isAnswer = isAnswer;
    }

    public String getTitleAnswer() {
        return titleAnswer;
    }

    public void setTitleAnswer(String titleAnswer) {
        this.titleAnswer = titleAnswer;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }
}
