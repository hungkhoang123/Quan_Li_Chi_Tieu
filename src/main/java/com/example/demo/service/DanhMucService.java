package com.example.demo.service;

import com.example.demo.model.DanhMuc;
import com.example.demo.repository.DanhMucRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DanhMucService {

    @Autowired
    DanhMucRepository danhMucRepository;

    public Page<DanhMuc> timKiemVaLoc(Integer nguoiDungId, String keyword, String loai, Pageable pageable) {
        Specification<DanhMuc> spec = (root, query, cb) -> {
            Predicate p = cb.equal(root.get("nguoiDung").get("id"), nguoiDungId);

            if (keyword != null && !keyword.trim().isEmpty()) {
                p = cb.and(p, cb.like(cb.lower(root.get("tenDanhMuc")), "%" + keyword.toLowerCase() + "%"));
            }

            if (loai != null && !loai.isEmpty()) {
                p = cb.and(p, cb.equal(root.get("loai"), loai));
            }

            return p;
        };

        return danhMucRepository.findAll(spec, pageable);
    }

}
