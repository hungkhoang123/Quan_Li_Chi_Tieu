package com.example.demo.repository;

import com.example.demo.model.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Integer>, JpaSpecificationExecutor<DanhMuc> {

    List<DanhMuc> findByTenDanhMucContainingIgnoreCase(String tenDanhMuc);

    List<DanhMuc> findByLoai(String loai);

    List<DanhMuc> findByNguoiDungId(Integer nguoiDungId);

    Optional<DanhMuc> findByIdAndNguoiDungId(Integer id, Integer nguoiDungId);
}

