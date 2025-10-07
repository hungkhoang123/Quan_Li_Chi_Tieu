package com.example.demo.controller;

import com.example.demo.model.NguoiDung;
import com.example.demo.service.NguoiDungService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/dang-ky")
    public String dangKyForm(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "dang-ky";
    }

    @PostMapping("/dang-ky")
    public String dangKySubmit(@ModelAttribute NguoiDung nguoiDung, Model model) {
        if (nguoiDungService.dangKy(nguoiDung)) {
            return "redirect:/dang-nhap";
        } else {
            model.addAttribute("error", "Tên tài khoản hoặc email đã tồn tại");
            return "dang-ky";
        }
    }

    @GetMapping("/dang-nhap")
    public String dangNhapForm() {
        return "dang-nhap";
    }

    @PostMapping("/dang-nhap")
    public String dangNhapSubmit(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpSession session,
                                 Model model) {
        NguoiDung nd = nguoiDungService.dangNhap(username, password);
        if (nd != null) {
            session.setAttribute("user", nd);
            return "redirect:/chi-tieu";
        } else {
            model.addAttribute("error", "Tên tài khoản hoặc mật khẩu không đúng");
            return "dang-nhap";
        }
    }

    @GetMapping("/dang-xuat")
    public String dangXuat(HttpSession session) {
        session.invalidate(); // xóa session
        return "redirect:/dang-nhap";
    }
}

