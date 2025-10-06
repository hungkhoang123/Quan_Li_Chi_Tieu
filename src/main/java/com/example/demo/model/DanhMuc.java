package com.example.demo.model;

import java.time.LocalDate;

public class DanhMuc {

    private Integer id;
    private String ten;
    private String moTa;
    private String loai;
    private LocalDate ngayTao;

    public DanhMuc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public DanhMuc(Integer id, String ten, String moTa, String loai, LocalDate ngayTao) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.loai = loai;
        this.ngayTao = ngayTao;
    }
}
