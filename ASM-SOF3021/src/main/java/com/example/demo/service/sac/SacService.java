package com.example.demo.service.sac;

import com.example.demo.dto.SanPhamGiam;
import com.example.demo.model.Sac;
import com.example.demo.repository.SacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SacService implements ISacService {
    @Autowired
    SacRepository repository;

    @Override
    public Page<Sac> getAll(Pageable pageable) {
        Page<Sac> sacList = repository.findAll(pageable);
        return sacList;
    }

    @Override
    public void add(Sac sac) {
        repository.save(sac);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Sac getById(int id) {
        Optional<Sac> sac = repository.findById(id);
        return sac.get();
    }

    @Override
    public Page<SanPhamGiam> getList(Pageable pageable) {
        Page<SanPhamGiam> list=repository.getList(pageable);
        return list;
    }

    @Override
    public Page findByNameLike(String ten, Pageable pageable) {
        Page<Sac> sac = repository.findSacByNameContains(ten, pageable);
        return sac;
    }

    @Override
    public Page<Sac> findByPriceBetween(BigDecimal min, BigDecimal max,Pageable pageable) {
        Page<Sac> page = repository.findByPriceBetween(min, max, pageable);
        return page;
    }

    @Override
    public Page<Sac> findByPriceBetweenAndName( BigDecimal min, BigDecimal max,String ten, Pageable pageable) {
        Page<Sac> page = repository.findByPriceBetweenAndName(min, max,ten, pageable);
        return page;
    }

    @Override
    public Page<Sac> findSacByNameLikeOrCategoryOrAndPowerLike(String ten, String loai, String congSuat, Pageable pageable) {
        Page<Sac> page = repository.findSacByNameLikeOrCategoryOrAndPowerLike(ten, loai, congSuat,pageable);
        return page;
    }

    @Override
    public Page<Sac> findSacByOrderByPriceAsc(Pageable pageable) {
        return  null;
    }

    @Override
    public Page<Sac> findSacByOrderByPriceDesc(Pageable pageable) {
        return null;
    }

    @Override
    public void update(Sac sac) {
        repository.save(sac);
    }

}
