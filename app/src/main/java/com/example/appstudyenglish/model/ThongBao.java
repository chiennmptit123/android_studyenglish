package com.example.appstudyenglish.model;

public class ThongBao {
    private int maThongBao;
    private int avatar;
    private String tieuDe;
    private String nguoiGui;
    private String noiDung;
    private String time;

    public ThongBao(){}

    public ThongBao(int maThongBao, int avatar, String tieuDe, String nguoiGui, String noiDung, String time) {
        this.maThongBao = maThongBao;
        this.avatar = avatar;
        this.tieuDe = tieuDe;
        this.nguoiGui = nguoiGui;
        this.noiDung = noiDung;
        this.time = time;
    }

    public int getMaThongBao() {
        return maThongBao;
    }

    public void setMaThongBao(int maThongBao) {
        this.maThongBao = maThongBao;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNguoiGui() {
        return nguoiGui;
    }

    public void setNguoiGui(String nguoiGui) {
        this.nguoiGui = nguoiGui;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
