package com.example.demo.controller;

import com.example.demo.model.Discount;
import com.example.demo.repository.IDiscountsRepository;
import com.example.demo.service.sac.ISacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    IDiscountsRepository repository;
    @Autowired
    ISacService sacService;
    @GetMapping("/list")
    public String views(Model model){
        List<Discount> list=repository.findAll();
        System.out.println(list.size());
        model.addAttribute("list",list);
        return "discount";
    }
    @PostMapping("/add/{id}")
    public String add(Model model, @ModelAttribute("discount")Discount discount, @PathVariable("id")Integer id){
        Discount discount1=new Discount();
        discount1.setProduct(sacService.getById(id));
        discount1.setDiscountAmount(discount.getDiscountAmount());
        repository.save(discount1);
        return "redirect:/discount/list";
    }
    @GetMapping("/add/{id}")
    public String addForm(Model model, @ModelAttribute("discount")Discount discount, @PathVariable("id")Integer id){
        List<Discount> list=repository.findAll();
        System.out.println(list.size());
        model.addAttribute("list",list);
        return "discount";
    }

}
