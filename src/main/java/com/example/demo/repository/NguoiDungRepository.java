package com.example.demo.repository;

import com.example.demo.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    boolean existsByEmail(String email);

    boolean existsByTenTaiKhoan(String tenTaiKhoan);

    NguoiDung findByTenTaiKhoanOrEmail(String tenTaiKhoan, String email);
}
