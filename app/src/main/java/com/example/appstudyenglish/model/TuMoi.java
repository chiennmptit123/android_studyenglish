package com.example.appstudyenglish.model;

public class TuMoi {
    private String content;
    private int mp3;

    public TuMoi(){}

    public TuMoi(String content, int mp3) {
        this.content = content;
        this.mp3 = mp3;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMp3() {
        return mp3;
    }

    public void setMp3(int mp3) {
        this.mp3 = mp3;
    }
}
