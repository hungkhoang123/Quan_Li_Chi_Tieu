package com.example.demo.service;

import com.example.demo.model.ChiTieu;
import com.example.demo.repository.ChiTieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class ChiTieuService {

    @Autowired
    private ChiTieuRepository chiTieuRepository;

    public Page<ChiTieu> timKiemVaLoc(Long nguoiDungId,
                                      String keyword,
                                      Double minAmount,
                                      Double maxAmount,
                                      Long danhMucId,
                                      Pageable pageable) {

        Specification<ChiTieu> spec = Specification.where((root, query, cb) ->
                cb.equal(root.get("nguoiDung").get("id"), nguoiDungId)
        );

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("moTa")), "%" + keyword.toLowerCase() + "%"));
        }

        if (minAmount != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("soTien"), minAmount));
        }

        if (maxAmount != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("soTien"), maxAmount));
        }

        if (danhMucId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.join("danhMuc").get("id"), danhMucId));
        }

        return chiTieuRepository.findAll(spec, pageable);
    }
}
