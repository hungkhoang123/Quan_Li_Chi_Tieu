package com.example.demo.controller;

import com.example.demo.model.ChiTieu;
import com.example.demo.model.DanhMuc;
import com.example.demo.repository.ChiTieuRepository;
import com.example.demo.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuanLyChiTieuController {

    @Autowired
    private ChiTieuRepository chiTieuRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;

    @GetMapping("/trang-chu")
    public String test(){
        return "trang-chu";
    }

    @GetMapping("/dang-nhap")
    public String dangNhap(){
        return "form-dang-nhap";
    }

    @GetMapping("/dang-ki")
    public String dangKi(){
        return "form-dang-ki";
    }

    @GetMapping("/chi-tieu")
    public String dsChiTieu(Model model){
        model.addAttribute("listChiTieu", chiTieuRepository.getAll());
        return "danh-sach-chi-tieu";
    }

    @GetMapping("/danh-muc")
    public String dsDanhMuc(Model model){
        model.addAttribute("listDanhMuc", danhMucRepository.getAll());
        return "danh-sach-danh-muc";
    }

    @GetMapping("/chi-tieu/form-them")
    public String formThemChiTieu(Model model) {
        model.addAttribute("listDanhMuc", danhMucRepository.getAll());
        return "form-them-chi-tieu";
    }

    @GetMapping("/danh-muc/form-them")
    public String formThemDanhMuc(Model model) {
        model.addAttribute("listDanhMuc", danhMucRepository.getAll());
        return "form-them-danh-muc";
    }

    @PostMapping("/chi-tieu/them")
    public String addChiTieu(@ModelAttribute ChiTieu chiTieu){
        chiTieuRepository.them(chiTieu);
        return "redirect:/chi-tieu";
    }

    @PostMapping("/danh-muc/them")
    public String addDanhMuc(@ModelAttribute DanhMuc danhMuc){
        danhMucRepository.them(danhMuc);
        return "redirect:/danh-muc";
    }

    @GetMapping("/chi-tieu/form-sua")
    public String formSuaChiTieu(Model model, @RequestParam Integer id) {
        model.addAttribute("chiTieu", chiTieuRepository.getById(id));
        model.addAttribute("listDanhMuc", danhMucRepository.getAll());
        return "form-sua-chi-tieu";
    }

    @GetMapping("/danh-muc/form-sua")
    public String formSuaDanhMuc(Model model, @RequestParam Integer id) {
        model.addAttribute("danhMuc", danhMucRepository.getById(id));
        model.addAttribute("listDanhMuc", danhMucRepository.getAll());
        return "form-sua-danh-muc";
    }

    @PostMapping("/chi-tieu/sua")
    public String updateChiTieu(@ModelAttribute ChiTieu chiTieu, @RequestParam Integer id){
        DanhMuc dm = danhMucRepository.getById(chiTieu.getDanhMuc().getId());
        chiTieu.setDanhMuc(dm);
        chiTieuRepository.sua(chiTieu, id);
        return "redirect:/chi-tieu";
    }

    @PostMapping("/danh-muc/sua")
    public String updateDanhMuc(@ModelAttribute DanhMuc danhMuc, @RequestParam Integer id){
        danhMucRepository.sua(danhMuc,id);
        return "redirect:/danh-muc";
    }

    @GetMapping("/chi-tieu/xoa")
    public String deleteChiTieu(@RequestParam Integer id){
        chiTieuRepository.xoa(id);
        return "redirect:/chi-tieu";
    }

    @GetMapping("/danh-muc/xoa")
    public String deleteDanhMuc(@RequestParam Integer id){
        danhMucRepository.xoa(id);
        return "redirect:/danh-muc";
    }
}
