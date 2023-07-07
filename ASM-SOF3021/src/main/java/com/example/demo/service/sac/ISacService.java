package com.example.demo.service.sac;

import com.example.demo.dto.SanPhamGiam;
import com.example.demo.model.Sac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;


public interface ISacService {
    Page<Sac> getAll(Pageable pageable);

    void add(Sac sac);

    void deleteById(int id);

    Sac getById(int id);
    Page<SanPhamGiam> getList(Pageable pageable);

    void update(Sac sac);
    Page findByNameLike(String ten, Pageable pageable );
    Page<Sac> findByPriceBetween(BigDecimal min, BigDecimal max,Pageable pageable );
    Page<Sac> findByPriceBetweenAndName(BigDecimal min, BigDecimal max,String ten,Pageable pageable);
    Page<Sac> findSacByNameLikeOrCategoryOrAndPowerLike(
            String ten, String loai, String congSuat,
            Pageable pageable);

    Page<Sac> findSacByOrderByPriceAsc(Pageable pageable);

    Page<Sac> findSacByOrderByPriceDesc(Pageable pageable);

}
