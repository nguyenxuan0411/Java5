package com.example.demo.service.account;


import com.example.demo.model.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByUserName(String name);

    void save(Account account);
   String nameId(String name);

    void capnhat(Account account);

    void changePassword(String username, String oldPass, String newPass);
}
