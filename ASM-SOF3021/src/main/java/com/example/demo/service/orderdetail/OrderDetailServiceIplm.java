package com.example.demo.service.orderdetail;

import com.example.demo.model.OrderDetail;
import com.example.demo.repository.IOrderDetailRepo;
import com.example.demo.service.account.IAccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceIplm implements IOrderDetailService{
    @Autowired
    HttpServletRequest request;
    @Autowired
    private IAccountService accountService;
    @Autowired
    IOrderDetailRepo iOrderDetail;
    @Override
    public List<OrderDetail> getList(){
        List<OrderDetail> list=iOrderDetail.findAll();
        return list;
    }
    @Override
    public List<OrderDetail> detail(Integer id){
        List<OrderDetail> order=iOrderDetail.findAllByOrderId(id);
        return order;
    }
}
