package com.example.demo.controller;

import com.example.demo.model.ChiTieu;
import com.example.demo.model.DanhMuc;
import com.example.demo.model.NguoiDung;
import com.example.demo.repository.ChiTieuRepository;
import com.example.demo.repository.DanhMucRepository;
import com.example.demo.service.ChiTieuService;
import com.example.demo.service.DanhMucService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Controller
public class QuanLyChiTieuController {


    @Autowired
    private ChiTieuRepository chiTieuRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private ChiTieuService  chiTieuService;

    @Autowired
    private DanhMucService danhMucService;

    @GetMapping("")
    public String macDinh() {
        return "trang-chu";
    }

    @GetMapping("/trang-chu")
    public String trangChu() {
        return "trang-chu";
    }

    @GetMapping("/chi-tieu")
    public String chiTieu(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) Long danhMucId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model,
            HttpSession session) {

        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Sort sort = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ChiTieu> pageChiTieu = chiTieuService.timKiemVaLoc(
                user.getId().longValue(),
                keyword,
                minAmount,
                maxAmount,
                danhMucId,
                pageable
        );

        List<DanhMuc> listDanhMuc = danhMucRepository.findByNguoiDungId(user.getId());

        model.addAttribute("listChiTieu", pageChiTieu.getContent());
        model.addAttribute("listDanhMuc", listDanhMuc);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageChiTieu.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("minAmount", minAmount);
        model.addAttribute("maxAmount", maxAmount);
        model.addAttribute("danhMucId", danhMucId);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "danh-sach-chi-tieu";
    }


    @GetMapping("/danh-muc")
    public String danhMuc(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String loai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "tenDanhMuc") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model,
            HttpSession session) {

        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Sort sort = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<DanhMuc> pageDanhMuc = danhMucService.timKiemVaLoc(user.getId(), keyword, loai, pageable);

        model.addAttribute("listDanhMuc", pageDanhMuc.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageDanhMuc.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("loai", loai);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "danh-sach-danh-muc";
    }


    @GetMapping("/chi-tieu/form-them")
    public String formThemChiTieu(Model model, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");

        List<DanhMuc> listDanhMuc = danhMucRepository.findByNguoiDungId(user.getId());
        model.addAttribute("listDanhMuc", listDanhMuc != null ? listDanhMuc : new ArrayList<>());
        model.addAttribute("chiTieu", new ChiTieu());
        return "form-them-chi-tieu";
    }

    @GetMapping("/danh-muc/form-them")
    public String formThemDanhMuc(Model model, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");

        model.addAttribute("danhMuc", new DanhMuc());
        return "form-them-danh-muc";
    }

    @PostMapping("/chi-tieu/them")
    public String addChiTieu(@ModelAttribute ChiTieu chiTieu, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        chiTieu.setNguoiDung(user);
        chiTieuRepository.save(chiTieu);
        return "redirect:/chi-tieu";
    }

    @PostMapping("/danh-muc/them")
    public String addDanhMuc(@ModelAttribute DanhMuc danhMuc, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        danhMuc.setNguoiDung(user);
        danhMucRepository.save(danhMuc);
        return "redirect:/danh-muc";
    }

    @GetMapping("/chi-tieu/form-sua")
    public String formSuaChiTieu(Model model, @RequestParam Integer id, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        ChiTieu chiTieu = chiTieuRepository.findByIdAndNguoiDungId(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Chi tiêu không tồn tại hoặc không thuộc user: " + id));

        List<DanhMuc> listDanhMuc = danhMucRepository.findByNguoiDungId(user.getId());

        model.addAttribute("chiTieu", chiTieu);
        model.addAttribute("listDanhMuc", listDanhMuc);
        return "form-sua-chi-tieu";
    }

    @GetMapping("/danh-muc/form-sua")
    public String formSuaDanhMuc(Model model, @RequestParam Integer id, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        DanhMuc danhMuc = danhMucRepository.findByIdAndNguoiDungId(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại hoặc không thuộc user: " + id));

        model.addAttribute("danhMuc", danhMuc);
        return "form-sua-danh-muc";
    }

    @PostMapping("/chi-tieu/sua")
    public String updateChiTieu(@ModelAttribute ChiTieu chiTieu, @RequestParam Integer id, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        ChiTieu chiTieuDb = chiTieuRepository.findByIdAndNguoiDungId(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Chi tiêu không tồn tại hoặc không thuộc user: " + id));

        chiTieuDb.setSoTien(chiTieu.getSoTien());
        chiTieuDb.setDanhMuc(chiTieu.getDanhMuc());
        chiTieuDb.setNgayGiaoDich(chiTieu.getNgayGiaoDich());
        chiTieuDb.setMoTa(chiTieu.getMoTa());
        chiTieuRepository.save(chiTieuDb);

        return "redirect:/chi-tieu";
    }

    @PostMapping("/danh-muc/sua")
    public String updateDanhMuc(@ModelAttribute DanhMuc danhMuc, @RequestParam Integer id, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        DanhMuc danhMucDb = danhMucRepository.findByIdAndNguoiDungId(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại hoặc không thuộc user: " + id));

        danhMucDb.setTenDanhMuc(danhMuc.getTenDanhMuc());
        danhMucDb.setLoai(danhMuc.getLoai());
        danhMucDb.setBieuTuong(danhMuc.getBieuTuong());
        danhMucRepository.save(danhMucDb);

        return "redirect:/danh-muc";
    }

    @GetMapping("/chi-tieu/xoa")
    public String deleteChiTieu(@RequestParam Integer id, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        ChiTieu chiTieu = chiTieuRepository.findByIdAndNguoiDungId(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Chi tiêu không tồn tại hoặc không thuộc user: " + id));
        chiTieuRepository.delete(chiTieu);
        return "redirect:/chi-tieu";
    }

    @GetMapping("/danh-muc/xoa")
    public String deleteDanhMuc(@RequestParam Integer id, HttpSession session) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        DanhMuc danhMuc = danhMucRepository.findByIdAndNguoiDungId(id, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại hoặc không thuộc user: " + id));
        danhMucRepository.delete(danhMuc);
        return "redirect:/danh-muc";
    }

    @GetMapping("/thong-ke")
    public String thongKe(
            @RequestParam(name = "loai", defaultValue = "tuan") String loai,
            HttpSession session,
            Model model
    ) {
        NguoiDung user = (NguoiDung) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<ChiTieu> listChiTieu = chiTieuRepository.findByNguoiDungId(user.getId());
        Map<String, Double> tongTheoDanhMuc = new HashMap<>();

        LocalDate now = LocalDate.now();

        if (loai.equals("tuan")) {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int currentWeek = now.get(weekFields.weekOfWeekBasedYear());

            listChiTieu.stream()
                    .filter(ct -> ct.getNgayGiaoDich() != null &&
                            ct.getNgayGiaoDich().get(weekFields.weekOfWeekBasedYear()) == currentWeek &&
                            ct.getNgayGiaoDich().getYear() == now.getYear())
                    .forEach(ct -> {
                        String tenDm = ct.getDanhMuc() != null ? ct.getDanhMuc().getTenDanhMuc() : "Khác";
                        double soTien = ct.getSoTien() != null ? ct.getSoTien().doubleValue() : 0.0;
                        tongTheoDanhMuc.put(tenDm,
                                tongTheoDanhMuc.getOrDefault(tenDm, 0.0) + soTien);
                    });
            model.addAttribute("loaiThongKe", "Tuần hiện tại");
        } else if (loai.equals("thang")) {
            int currentMonth = now.getMonthValue();
            int currentYear = now.getYear();

            listChiTieu.stream()
                    .filter(ct -> ct.getNgayGiaoDich() != null &&
                            ct.getNgayGiaoDich().getMonthValue() == currentMonth &&
                            ct.getNgayGiaoDich().getYear() == currentYear)
                    .forEach(ct -> {
                        String tenDm = ct.getDanhMuc() != null ? ct.getDanhMuc().getTenDanhMuc() : "Khác";
                        double soTien = ct.getSoTien() != null ? ct.getSoTien().doubleValue() : 0.0;
                        tongTheoDanhMuc.put(tenDm,
                                tongTheoDanhMuc.getOrDefault(tenDm, 0.0) + soTien);
                    });
            model.addAttribute("loaiThongKe", "Tháng hiện tại");
        }

        model.addAttribute("tongTheoDanhMuc", tongTheoDanhMuc);

        // Thêm 2 list riêng cho Chart.js
        model.addAttribute("danhMucKeys", new ArrayList<>(tongTheoDanhMuc.keySet()));
        model.addAttribute("tongTienValues", new ArrayList<>(tongTheoDanhMuc.values()));

        return "thong-ke/thong-ke";
    }

    @GetMapping("/chi-tieu/loc")
    public String locChiTieu(
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            Model model,
            HttpSession session,
            Pageable pageable) {

        NguoiDung user = (NguoiDung) session.getAttribute("user");

        Page<ChiTieu> listChiTieu = chiTieuService.timKiemVaLoc(
                user.getId().longValue(),
                null,
                minAmount,
                maxAmount,
                null,
                pageable
        );

        model.addAttribute("listChiTieu", listChiTieu.getContent());
        model.addAttribute("minAmount", minAmount);
        model.addAttribute("maxAmount", maxAmount);

        return "danh-sach-chi-tieu";
    }

    @GetMapping("/tai-khoan")
    public String taiKhoan(Model model, HttpSession session) {

        NguoiDung user = (NguoiDung) session.getAttribute("user");


        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "account/tai-khoan";
    }
}
