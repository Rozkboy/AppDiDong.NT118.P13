package com.example.lab03_22521602_3;

public class SinhVien {
    private int id;
    private String hoTen;
    private String mssv;
    private String lop;

    public SinhVien() {
    }

    // Constructor với tham số
    public SinhVien( String hoTen, String mssv, String lop) {
        this.hoTen = hoTen;
        this.mssv = mssv;
        this.lop = lop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMSSV() {
        return mssv;
    }

    public void setMSSV(String mssv) {
        this.mssv = mssv;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
