package com.example.demo.repository;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepositories extends JpaRepository<Account, String> {
//    @Query("""
//    select u from Account u join fetch  u.orders h where u.username like ?1
//""")
//    List<Account> getAllHoaDon(String username);
}
