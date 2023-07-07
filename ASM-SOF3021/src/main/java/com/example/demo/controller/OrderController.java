package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.repository.IAccountRepositories;
import com.example.demo.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService service;

    @Autowired
    private IAccountRepositories accountRepo;

    @GetMapping("/add")
    public String add(@ModelAttribute("order")Order order){
        service.add(order);
        return "redirect:/order/list";
    }
    @GetMapping("/list")
    public String view(Model model){
        List<Order> list=service.getList();
       model.addAttribute("list",list);
        return "order";
    }


}
