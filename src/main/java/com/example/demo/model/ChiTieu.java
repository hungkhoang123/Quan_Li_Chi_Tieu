package com.example.demo.model;

import java.time.LocalDate;

public class ChiTieu {

    private Integer id;
    private String noiDung;
    private Double soTien;
    private LocalDate ngay;
    private String ghiChu;
    private DanhMuc danhMuc;

    public ChiTieu(){
    }

    public ChiTieu(Integer id, String noiDung, Double soTien, LocalDate ngay, String ghiChu, DanhMuc danhMuc) {
        this.id = id;
        this.noiDung = noiDung;
        this.soTien = soTien;
        this.ngay = ngay;
        this.ghiChu = ghiChu;
        this.danhMuc = danhMuc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }
}
