package com.example.appstudyenglish.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Tuan implements Serializable {
    private String title;
    private int status;
    private ArrayList<Buoi> buoiArrayList;

    public Tuan(){}

    public Tuan(String title, int status, ArrayList<Buoi> buoiArrayList) {
        this.title = title;
        this.status = status;
        this.buoiArrayList = buoiArrayList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Buoi> getBuoiArrayList() {
        return buoiArrayList;
    }

    public void setBuoiArrayList(ArrayList<Buoi> buoiArrayList) {
        this.buoiArrayList = buoiArrayList;
    }
}
