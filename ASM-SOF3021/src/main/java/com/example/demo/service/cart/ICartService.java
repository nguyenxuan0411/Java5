package com.example.demo.service.cart;
import com.example.demo.model.Sac;

import java.math.BigDecimal;
import java.util.Collection;

public interface ICartService {
    Sac add(int id);

    Sac findById(int id);

    Sac themGioHang(int id);

    void remove(int id);

    Sac update(int id, int quantity);

    void clear();

    Collection<Sac> getProducts();

    int getCount();

    BigDecimal getAmount();

}
