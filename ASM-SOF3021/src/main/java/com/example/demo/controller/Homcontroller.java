package com.example.demo.controller;

import com.example.demo.model.Sac;
import com.example.demo.service.sac.ISacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/product")
public class Homcontroller {
    @Autowired
    private ISacService service;


    @GetMapping("/show")
    public String view(Model model
            , @RequestParam(required = false, name = "name", defaultValue = "") String keyword , @RequestParam(name = "min" , required = false)
                           BigDecimal min,@RequestParam(name = "max" ,required = false) BigDecimal max, @RequestParam(defaultValue = "1", name = "page") int page) {
        Page<Sac> danhSachSanPham;

        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
            danhSachSanPham = service.getAll(pageable);
       model.addAttribute("list", danhSachSanPham);
        return "shop";
    }
    @GetMapping("/ShowOne/{IdProduct}")
    public String getOneProductDetail(@PathVariable(value = "IdProduct") int idProduct, Model model) {
        Sac productDetail = service.getById(idProduct);
        if (productDetail == null) {
            return "shop";
        }
        model.addAttribute("prod", productDetail);
        return "productdetail";
    }
}
