package com.example.appstudyenglish.model;

import java.io.Serializable;

public class KhoaHoc implements Serializable {
    private int maKhoaHoc;
    private int avatar;
    private String name;
    private String teacher;
    private String date;
    private int viewer;
    private String title;

    public KhoaHoc(){}

    public KhoaHoc(int maKhoaHoc, int avatar, String name, String teacher, String date, int viewer, String title) {
        this.maKhoaHoc = maKhoaHoc;
        this.avatar = avatar;
        this.name = name;
        this.teacher = teacher;
        this.date = date;
        this.viewer = viewer;
        this.title = title;
    }

    public int getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(int maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getViewer() {
        return viewer;
    }

    public void setViewer(int viewer) {
        this.viewer = viewer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
