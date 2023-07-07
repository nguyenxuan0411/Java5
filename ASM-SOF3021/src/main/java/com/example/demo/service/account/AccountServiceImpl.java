package com.example.demo.service.account;

import com.example.demo.model.Account;
import com.example.demo.repository.IAccountRepositories;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    IAccountRepositories accountRepositories;
    @Override
    public Optional<Account> findByUserName(String name) {
        Optional<Account> a = accountRepositories.findById(name);
        return a;
    }
    @Override
    public String nameId(String name){
        String[] nameParts = name.split("\\s+");
        String firstName = nameParts[nameParts.length-1];
        String lastName = "";
        for (int i = 0; i < nameParts.length-1; i++) {
            lastName += nameParts[i].charAt(0);
        }
        String result = firstName + lastName.toUpperCase();
        String normalized = Normalizer.normalize(result, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String output = pattern.matcher(normalized).replaceAll("").toLowerCase();

        Random rd = new Random();
        int ranNum = rd.nextInt(100);
        return output + ranNum;
    }

    @Override
    public void save(Account account) {
        accountRepositories.save(account);
    }
    @Override
    public void capnhat(Account account) {
        Account accounts = (Account) request.getSession().getAttribute("user");
        account.setUsername(accounts.getUsername());
        accountRepositories.save(account);
    }
    @Override
    public void changePassword(String username, String oldPass, String newPass) {
        Account account = (Account) request.getSession().getAttribute("user");
        try {
            if(account.getUsername().equals(username) && account.getPassword().equals(oldPass))
            account.setPassword(newPass);
            accountRepositories.save(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
