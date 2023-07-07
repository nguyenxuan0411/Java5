package com.example.demo.service.order;

import com.example.demo.model.Account;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Sac;
import com.example.demo.repository.IOrderDetailRepo;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.service.account.IAccountService;
import com.example.demo.service.cart.ICartService;
import com.example.demo.service.sac.ISacService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceIplm implements IOrderService{
    @Autowired
    private IOrderRepository repository;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IOrderDetailRepo orderDetailRepo;
    @Autowired
    private ISacService sacService;
    @Autowired
    private ICartService iCartService;
    Map<Integer, Sac> map = new HashMap<>();
   @Override
   public void add(Order order) {
       Date date = new Date();
       Account account = (Account) request.getSession().getAttribute("user");
       Account account1 = accountService.findByUserName(account.getUsername()).orElse(null);
       if (account1 != null) {
           order.setAccounts(account1);
           order.setCreatedate(date);
           order.setTotalamount(iCartService.getAmount());
           repository.save(order);
           Map<Integer,Sac> cart = (Map) request.getSession().getAttribute("cart");
           for (Map.Entry<Integer, Sac> entry : cart.entrySet()) {
               Sac product = entry.getValue();
               Sac product1=sacService.getById(product.getId());
               product1.setQuantity(product1.getQuantity() -product.getQuantity());
               sacService.add(product1);
               OrderDetail orderDetail = new OrderDetail();
               orderDetail.setOrder(order);
               orderDetail.setPrice(product.getPrice());
               orderDetail.setProduct(product);
               orderDetail.setQuantity(product.getQuantity());
               orderDetailRepo.save(orderDetail);
           }
       }
   }


    @Override
    public List<Order> getList(){
        Account account = (Account) request.getSession().getAttribute("user");
        Account account1 = accountService.findByUserName(account.getUsername()).orElse(null);
       List<Order>  list=repository.findAllByAccounts(account1);
        System.out.println(list.size());
       return list;
    }
    @Override
    public Order getbyid(int id){
        Order order=repository.findById(id).get();
        return order;
    }


}
