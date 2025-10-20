package com.example.demo.repository;

import com.example.demo.model.ChiTieu;
import com.example.demo.model.DanhMuc;
import com.example.demo.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTieuRepository extends JpaRepository<ChiTieu, Integer>, JpaSpecificationExecutor<ChiTieu> {

    List<ChiTieu> findByNguoiDungId(Integer nguoiDungId);

    List<ChiTieu> findByDanhMuc(DanhMuc danhMuc);

    List<ChiTieu> findByNguoiDungAndDanhMuc(NguoiDung nguoiDung, DanhMuc danhMuc);

    List<ChiTieu> findByNguoiDungIdAndNgayGiaoDichBetween(Integer nguoiDungId, LocalDate fromDate, LocalDate toDate);

    Optional<ChiTieu> findByIdAndNguoiDungId(Integer id, Integer nguoiDungId);
}
