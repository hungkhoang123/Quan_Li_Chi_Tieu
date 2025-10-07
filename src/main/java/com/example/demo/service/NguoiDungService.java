package com.example.demo.service;

import java.time.LocalDateTime;

import com.example.demo.model.NguoiDung;
import com.example.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepo;


    public boolean dangKy(NguoiDung nd) {
        // Kiểm tra username/email đã tồn tại

        if (nguoiDungRepo.existsByEmail(nd.getEmail()) || nguoiDungRepo.existsByTenTaiKhoan(nd.getTenTaiKhoan())) {
            return false;
        }
        nd.setNgayTao(LocalDateTime.now());
        nguoiDungRepo.save(nd);
        return true;
    }

    // Đăng nhập bình thường (so sánh trực tiếp password)
    public NguoiDung dangNhap(String taiKhoan, String matKhau) {
        NguoiDung nd = nguoiDungRepo.findByTenTaiKhoanOrEmail(taiKhoan, taiKhoan);
        if (nd != null && matKhau.equals(nd.getMatKhau())) {
            return nd;
        }
        return null;
    }
}


