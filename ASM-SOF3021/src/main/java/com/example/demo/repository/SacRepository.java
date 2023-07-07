package com.example.demo.repository;

import com.example.demo.dto.SanPhamGiam;
import com.example.demo.model.Sac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SacRepository extends JpaRepository<Sac, Integer> {
    Page findSacByNameContains(String ten, Pageable pageable );

    Page<Sac> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);
    Page<Sac> findByPriceBetweenAndName(BigDecimal min, BigDecimal max,String ten, Pageable pageable);
    Page<Sac> findSacByNameLikeOrCategoryOrAndPowerLike(
            String ten, String loai, String congSuat,
            Pageable pageable);

    @Query(value = "SELECT new com.example.demo.dto.SanPhamGiam(P.id, P.name, P.price,  D.discountAmount , P.image) " +
            "FROM Sac AS P \n" +
            "LEFT JOIN Discount AS D ON P.id = D.product.id\n")
    Page<SanPhamGiam> getList(Pageable pageable);

}
