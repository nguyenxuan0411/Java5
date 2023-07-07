package com.example.demo.service.orderdetail;

import com.example.demo.model.OrderDetail;

import java.util.List;


public interface IOrderDetailService {
    List<OrderDetail> getList();


    List<OrderDetail> detail(Integer id);
}
