package com.example.demo.controller;

import com.example.demo.model.Sac;
import com.example.demo.service.cart.ICartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    HttpSession session;
    @Autowired
    ICartService cart;
    @GetMapping()
    public String viewCart(Model model){
        model.addAttribute("cart", cart);
        return "cart";
    }
    @RequestMapping("/add/{id}")
    public String addtoOrder(@PathVariable("id") Integer id){
        cart.add(id);
        return "forward:/cart";
    }
    @RequestMapping("/themGioHang/{id}")
    public String addtoCart(@PathVariable("id") Integer id, @ModelAttribute("cart")Map<Integer, Sac> map){
        cart.themGioHang(id);
        return "forward:/product/show";
    }
    @ModelAttribute("count")
    public int getCountProd(){
        int count = 0;
        if (cart != null){
            count = cart.getCount();
        }
        return count;
    }
    @ModelAttribute("amount")
    public BigDecimal getAmount(){
        BigDecimal amount = BigDecimal.ZERO;
        if (cart != null){
            amount = cart.getAmount();
        }
        return amount;
    }


    @RequestMapping("/update/{id}")
    public String updateCart(@PathVariable("id") Integer id,
                             @RequestParam("quantity") Integer qty) {
        cart.update(id, qty);
        return "redirect:/cart";
    }

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id) {
        cart.remove(id);
        return "redirect:/cart";
    }
}
