package com.example.appstudyenglish.model;

import java.util.List;

public class CauHoi {
    private int stt;
    private String title;
    private List<CauTraLoi> cauTraLoiList;
    private int dapAnChon;

    public CauHoi(){}

    public CauHoi(int stt, String title, List<CauTraLoi> cauTraLoiList, int dapAnChon) {
        this.stt = stt;
        this.title = title;
        this.cauTraLoiList = cauTraLoiList;
        this.dapAnChon = dapAnChon;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CauTraLoi> getCauTraLoiList() {
        return cauTraLoiList;
    }

    public void setCauTraLoiList(List<CauTraLoi> cauTraLoiList) {
        this.cauTraLoiList = cauTraLoiList;
    }

    public int getDapAnChon() {
        return dapAnChon;
    }

    public void setDapAnChon(int dapAnChon) {
        this.dapAnChon = dapAnChon;
    }
}
