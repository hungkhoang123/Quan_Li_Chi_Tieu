package com.example.demo.repository;

import com.example.demo.model.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Integer> {

    /**
     * Tìm danh mục theo tên (ví dụ dùng để kiểm tra trùng tên)
     */
    List<DanhMuc> findByTenDanhMucContainingIgnoreCase(String tenDanhMuc);

    /**
     * Tìm danh mục theo loại (thu hoặc chi)
     */
    List<DanhMuc> findByLoai(String loai);
}

