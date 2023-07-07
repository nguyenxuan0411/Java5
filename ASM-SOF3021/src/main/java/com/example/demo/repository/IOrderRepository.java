package com.example.demo.repository;

import com.example.demo.model.Account;
import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Integer> {

 List<Order> findAllByAccounts(Account account);
}
