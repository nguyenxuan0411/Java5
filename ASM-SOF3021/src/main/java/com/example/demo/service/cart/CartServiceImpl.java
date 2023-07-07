package com.example.demo.service.cart;

import com.example.demo.model.Cart;
import com.example.demo.model.Sac;
import com.example.demo.service.sac.SacService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartServiceImpl implements ICartService {
    @Autowired
    SacService productDetailService;
    @Autowired
    HttpSession session;
    private Cart cart=new Cart();
    Map<Integer, Sac> map = new HashMap<>();

    @Override
    public Sac add(int id) {
        Sac product = map.get(id);

        if (product != null){
            product.setQuantity(product.getQuantity() + 1);
        } else {
            Sac exProduct = productDetailService.getById(id);
            if (exProduct != null){
                exProduct.setQuantity(1);
                map.put(id, exProduct);
            }
        }
        session.setAttribute("cart",map);
        return product;
    }
    @Override
    public Sac findById(int id){
        Sac product = findById(id);
        return product;
    }
    @Override
    public Sac themGioHang(int id) {
        Sac product = map.get(id);

        if (product != null){
            product.setQuantity(product.getQuantity() + 1);
        } else {
            Sac exProduct = productDetailService.getById(id);
            if (exProduct != null){
                exProduct.setQuantity(1);
                map.put(id, exProduct);
            }
        }

        return product;
    }

    @Override
    public void remove(int id) {
        map.remove(id);
    }

    @Override
    public Sac update(int id, int quantity) {
        Sac p = map.get(id);
        if (p != null){
            p.setQuantity(quantity);
        }
        return p;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Sac> getProducts() {
        return map.values();
    }

    @Override
    public int getCount() {
        int count = 0;
        for (Sac product : map.values()){
            count+=product.getQuantity();
        }
        return count;
    }

    @Override
    public BigDecimal getAmount() {
        BigDecimal amount = BigDecimal.ZERO;
        for (Sac product : map.values()){
            if (product != null) {
                int quantity = product.getQuantity();
                BigDecimal price = product.getPrice();
                amount = amount.add(BigDecimal.valueOf(quantity).multiply(price));
            }
        }
        return amount;
    }


}
