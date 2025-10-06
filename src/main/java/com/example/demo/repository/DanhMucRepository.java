package com.example.demo.repository;

import com.example.demo.model.DanhMuc;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DanhMucRepository {

    private static final List<DanhMuc> DS_DanhMuc = new ArrayList<>();
    private Integer count = 6;

    static {
        DS_DanhMuc.add(new DanhMuc(1, "Ăn uống", "Chi phí hằng ngày", "Chi tiêu", LocalDate.of(2025, 5, 1)));
        DS_DanhMuc.add(new DanhMuc(2, "Đi lại", "Xăng xe, vé xe", "Chi tiêu", LocalDate.of(2025, 7, 9)));
        DS_DanhMuc.add(new DanhMuc(3, "Học tập", "Sách vở, học phí", "Chi tiêu", LocalDate.of(2025, 7, 27)));
        DS_DanhMuc.add(new DanhMuc(4, "Tiết kiệm", "Gửi ngân hàng", "Tiết kiệm", LocalDate.of(2025, 6, 15)));
        DS_DanhMuc.add(new DanhMuc(5, "Giải trí", "Xem phim, cà phê", "Chi tiêu", LocalDate.of(2025, 3, 1)));
    }

    public List<DanhMuc> getAll() {
        return DS_DanhMuc;
    }

    public DanhMuc getById(Integer id) {
        return DS_DanhMuc.stream()
                .filter(dm -> dm.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean them(DanhMuc danhMuc) {
        danhMuc.setId(count++);
        DS_DanhMuc.add(danhMuc);
        return true;
    }

    public boolean sua(DanhMuc newDM, Integer id) {
        for (DanhMuc dm : DS_DanhMuc) {
            if (dm.getId().equals(id)) {
                dm.setTen(newDM.getTen());
                dm.setMoTa(newDM.getMoTa());
                dm.setLoai(newDM.getLoai());
                dm.setNgayTao(newDM.getNgayTao());
                return true;
            }
        }
        return false;
    }

    public boolean xoa(Integer id) {
        return DS_DanhMuc.removeIf(dm -> dm.getId() == (id));
    }
}
