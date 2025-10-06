package com.example.demo.repository;

import com.example.demo.model.ChiTieu;
import com.example.demo.model.DanhMuc;
import com.example.demo.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTieuRepository extends JpaRepository<ChiTieu, Integer> {

    /**
     * Lấy danh sách chi tiêu theo người dùng
     */
    List<ChiTieu> findByNguoiDung(NguoiDung nguoiDung);

    /**
     * Lấy danh sách chi tiêu theo danh mục
     */
    List<ChiTieu> findByDanhMuc(DanhMuc danhMuc);

    /**
     * Lấy danh sách chi tiêu theo người dùng và danh mục
     */
    List<ChiTieu> findByNguoiDungAndDanhMuc(NguoiDung nguoiDung, DanhMuc danhMuc);

    /**
     * Lấy danh sách chi tiêu theo khoảng ngày
     */
    List<ChiTieu> findByNgayGiaoDichBetween(java.time.LocalDate fromDate, java.time.LocalDate toDate);
}
