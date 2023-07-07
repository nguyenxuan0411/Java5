package com.example.demo.controller;

import com.example.demo.model.Sac;
import com.example.demo.model.ThongKe;
import com.example.demo.repository.IOrderDetailRepo;
import com.example.demo.service.sac.ISacService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@Controller
@RequestMapping("/sac")
public class AdminController {
    @Autowired
    private IOrderDetailRepo repo;
    @Autowired
    private ISacService service;


    @GetMapping("/hien-thi")
    public String view(Model model
            , @RequestParam(required = false, name = "name", defaultValue = "") String keyword ,
                       @RequestParam(name = "min" , required = false) BigDecimal min,
                       @RequestParam(name = "max" ,required = false) BigDecimal max,
                       @RequestParam(defaultValue = "1", name = "page") int page,
                      @ModelAttribute("SacObject")Sac sac) {

        System.out.println("hien thi sac");
        Page<Sac> danhSachSanPham;

        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);

        if (keyword == null || keyword.isBlank() && min == null && max == null) {
            danhSachSanPham = service.getAll(pageable);
        } else if (min == null && max == null) {
            danhSachSanPham = service.findByNameLike(keyword, pageable);
        } else if (keyword == null || keyword.isBlank()) {

                danhSachSanPham = service.findByPriceBetween(min, max, pageable);
        } else if (max == null) {
            danhSachSanPham = service.findByNameLike(keyword, pageable);
        } else if (min == null) {
            danhSachSanPham =service.findByNameLike(keyword, pageable);
        }else {
            danhSachSanPham = service.findByPriceBetweenAndName(min, max, keyword, pageable);
        }

        model.addAttribute("SacList", danhSachSanPham);
        return "admin";
    }

    @PostMapping("/add")
    public String them(
            @RequestParam(required = false, name = "name", defaultValue = "") String keyword ,
            @RequestParam(name = "min" , required = false) BigDecimal min,
            @RequestParam(name = "max" ,required = false) BigDecimal max,
            @RequestParam(defaultValue = "1", name = "page") int page,
            @Valid @ModelAttribute("SacObject") Sac sac, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("me", "Vui Lòng Điền Đủ Thông Tin");
            model.addAttribute("ale", "danger");
        } else {
            service.add(sac);
            model.addAttribute("me", "Thêm Mới Sản Phẩm Thành Công");
            model.addAttribute("ale", "success");
        }

        view(model, keyword, min, max, page, sac);
        return "admin";
    }

    @GetMapping("/delete/{id}")
    public String xoa(@PathVariable(value = "id") int id) {
        service.deleteById(id);
        return "redirect:/sac/hien-thi";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model
            , @RequestParam(required = false, name = "name", defaultValue = "") String keyword ,
                         @RequestParam(name = "min" , required = false) BigDecimal min,
                         @RequestParam(name = "max" ,required = false) BigDecimal max,
                         @RequestParam(defaultValue = "1", name = "page") int page,
                         @ModelAttribute("SacObject")Sac sac,
                         @PathVariable(value = "id") int id) {
        Sac SacObject = service.getById(id);
        model.addAttribute("SacObject",SacObject);
        view(model, keyword, min, max, page, sac);
        return "admin";
    }

    @PostMapping("/update")
    public String update(@RequestParam(required = false, name = "name", defaultValue = "") String keyword ,
                         @RequestParam(name = "min" , required = false) BigDecimal min,
                         @RequestParam(name = "max" ,required = false) BigDecimal max,
                         @RequestParam(defaultValue = "1", name = "page") int page,
                         @Valid @ModelAttribute("SacObject") Sac sac, BindingResult result, Model model) {

        System.out.println("update");
        if (result.hasErrors()) {
            model.addAttribute("me", "Bạn cần điền hết tất cả các trường");
        } else {
            service.update(sac);
            model.addAttribute("message", "Sửa thành công");
        }
        view(model, keyword, min, max, page, sac);
        return "admin";
    }

    @GetMapping("/thongketheongay")
    public String thongKe(Model model){
        List<ThongKe> thongKe=repo.getListByNgay(PageRequest.of(0,10));
        model.addAttribute("list",thongKe);
        System.out.println(thongKe.size());
        System.out.println(thongKe.get(0).soLuong);
        return "thongke";
    }
    @GetMapping("/thongketheothang")
    public String thongKetheoThang(Model model){
        List<ThongKe> thongKe=repo.getListByThang(PageRequest.of(0,10));
        model.addAttribute("list",thongKe);
        System.out.println(thongKe.size());
        return "thongke";
    }
    @GetMapping("/thongketheonam")
    public String thongKeTheoNam(Model model){
        List<ThongKe> thongKe=repo.getListByNam(PageRequest.of(0,10));
        model.addAttribute("list",thongKe);
        System.out.println(thongKe.size());
        return "thongke";
    }
    @GetMapping("/thongketheotuan")
    public String thongKeTheoTuan(Model model){
        List<ThongKe> thongKe=repo.getListByTuan(PageRequest.of(0,10));
        model.addAttribute("list",thongKe);
        System.out.println(thongKe.size());
        return "thongke";
    }
    @GetMapping("/thongke")
    public String thongKeForm(Model model){
        List<ThongKe> thongKe=repo.getList(PageRequest.of(0,10));
        model.addAttribute("list",thongKe);
        return "thongke";
    }
    @GetMapping("/spTon")
    public String spTon(Model model){
        List<ThongKe> thongKe=repo.getListTon(PageRequest.of(0,10));
        model.addAttribute("list",thongKe);
        return "giamgia";
    }
}

