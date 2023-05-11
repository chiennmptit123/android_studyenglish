package com.example.appstudyenglish.model;

import java.io.Serializable;

public class BaiHocTrongNgay implements Serializable {
    private String baiHoc;
    private int tienDo;

    public BaiHocTrongNgay(){}

    public BaiHocTrongNgay(String baiHoc, int tienDo) {
        this.baiHoc = baiHoc;
        this.tienDo = tienDo;
    }

    public String getBaiHoc() {
        return baiHoc;
    }

    public void setBaiHoc(String baiHoc) {
        this.baiHoc = baiHoc;
    }

    public int getTienDo() {
        return tienDo;
    }

    public void setTienDo(int tienDo) {
        this.tienDo = tienDo;
    }
}
