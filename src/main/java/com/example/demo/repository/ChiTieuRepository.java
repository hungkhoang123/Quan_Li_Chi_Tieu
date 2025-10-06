package com.example.demo.repository;

import com.example.demo.model.ChiTieu;
import com.example.demo.model.DanhMuc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChiTieuRepository {

    private static final List<ChiTieu> DS_ChiTieu = new ArrayList<>();
    private Integer cout = 5;
    static {
        DS_ChiTieu.add(new ChiTieu(1, "Ăn sáng", 20000.0, LocalDate.of(2025, 9, 20), null, new DanhMuc(1, "Ăn uống", "Chi phí hằng ngày", "Chi tiêu", LocalDate.of(2025, 5, 1))));
        DS_ChiTieu.add(new ChiTieu(2, "Cà phê", 30000.0, LocalDate.of(2025, 9, 20), null, new DanhMuc(1, "Ăn uống", "Chi phí hằng ngày", "Chi tiêu", LocalDate.of(2025, 5, 1))));
        DS_ChiTieu.add(new ChiTieu(3, "Ăn trưa", 40000.0, LocalDate.of(2025, 9, 20), null, new DanhMuc(1, "Ăn uống", "Chi phí hằng ngày", "Chi tiêu", LocalDate.of(2025, 5, 1))));
        DS_ChiTieu.add(new ChiTieu(4, "Ăn tối", 40000.0, LocalDate.of(2025, 9, 20), null, new DanhMuc(1, "Ăn uống", "Chi phí hằng ngày", "Chi tiêu", LocalDate.of(2025, 5, 1))));
        DS_ChiTieu.add(new ChiTieu(5, "Xăng xe", 50000.0, LocalDate.of(2025, 9, 20), null, new DanhMuc(1, "Đi lại", "Xăng xe, vé xe", "Chi tiêu", LocalDate.of(2025, 7, 9))));
    }

    public List<ChiTieu> getAll() {
        return DS_ChiTieu;
    }

    public ChiTieu getById(Integer id) {
        return DS_ChiTieu.stream().filter(chiTieu -> chiTieu.getId().equals(id)).findFirst().orElse(null);
    }

    public boolean them(ChiTieu chiTieu) {
        chiTieu.setId(cout++);
        DS_ChiTieu.add(chiTieu);
        return true;
    }

    public boolean sua(ChiTieu newChiTieu, Integer id) {
        for (ChiTieu ct : DS_ChiTieu) {
            if (ct.getId()== id){
                ct.setNoiDung(newChiTieu.getNoiDung());
                ct.setSoTien(newChiTieu.getSoTien());
                ct.setNgay(newChiTieu.getNgay());
                ct.setGhiChu(newChiTieu.getGhiChu());
                ct.setDanhMuc(newChiTieu.getDanhMuc());
                return true;
            }
        }
        return false;
    }

    public boolean xoa(Integer id) {
        return DS_ChiTieu.removeIf(chiTieu -> chiTieu.getId()==id);
    }
}
