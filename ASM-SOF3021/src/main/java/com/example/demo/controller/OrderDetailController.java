package com.example.demo.controller;

import com.example.demo.model.OrderDetail;
import com.example.demo.service.orderdetail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hoa-don")
public class OrderDetailController {
    @Autowired
    IOrderDetailService service;
    @GetMapping("/detail/{id}")
    public String view(Model model, @PathVariable("id")Integer id){
       List<OrderDetail> detail =service.detail(id);
        model.addAttribute("list",detail);
        return "orderdetail";
    }
    @GetMapping("/detail")
    public String views(Model model){
        List<OrderDetail> detail =service.getList();
        model.addAttribute("list",detail);
        return "orderdetail";
    }
}
