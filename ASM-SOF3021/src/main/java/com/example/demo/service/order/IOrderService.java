package com.example.demo.service.order;

import com.example.demo.model.Order;

import java.util.List;

public interface IOrderService  {
    void add(Order order);

    List<Order> getList();

    Order getbyid(int id);
}
