package com.example.demo.repository;

import com.example.demo.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiscountsRepository extends JpaRepository<Discount,Integer> {

}
