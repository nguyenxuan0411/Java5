package com.example.demo.model;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Date;

public class ThongKe {
    public Integer id;
    public String ten;
    public Long soLuong;
    public Date ngay;
    public Month thang;
    public  int nam;
    public DayOfWeek tuan;

    public ThongKe(Integer id, String ten, Long soLuong) {
        this.id = id;
        this.ten = ten;
        this.soLuong = soLuong;
    }

    public ThongKe(String ten, Long soLuong) {
        this.ten = ten;
        this.soLuong = soLuong;
    }
}
