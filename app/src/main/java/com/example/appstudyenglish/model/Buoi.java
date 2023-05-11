package com.example.appstudyenglish.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Buoi implements Serializable {
    private String tenBuoi;
    private ArrayList<BaiHocTrongNgay> baiHocTrongNgayArrayList;
    private int checkLoai;

    public Buoi(){

    }

    public Buoi(String tenBuoi, ArrayList<BaiHocTrongNgay> baiHocTrongNgayArrayList, int checkLoai) {
        this.tenBuoi = tenBuoi;
        this.baiHocTrongNgayArrayList = baiHocTrongNgayArrayList;
        this.checkLoai = checkLoai;
    }

    public String getTenBuoi() {
        return tenBuoi;
    }

    public void setTenBuoi(String tenBuoi) {
        this.tenBuoi = tenBuoi;
    }

    public ArrayList<BaiHocTrongNgay> getBaiHocTrongNgayArrayList() {
        return baiHocTrongNgayArrayList;
    }

    public void setBaiHocTrongNgayArrayList(ArrayList<BaiHocTrongNgay> baiHocTrongNgayArrayList) {
        this.baiHocTrongNgayArrayList = baiHocTrongNgayArrayList;
    }

    public int getCheckLoai() {
        return checkLoai;
    }

    public void setCheckLoai(int checkLoai) {
        this.checkLoai = checkLoai;
    }
}
