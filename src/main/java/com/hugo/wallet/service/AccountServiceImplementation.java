/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hugo.wallet.service;

import com.hugo.wallet.model.Account;
import com.hugo.wallet.repository.AccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hugo
 */
@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public Account create(Account account) {
        checkAccount(account);
        return repository.save(account);
    }

    @Override
    public Optional<Account> get(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private void checkAccount(Account account) {
        if (account.getAccountNumber() == null || account.getBankName() == null || account.getRoutingNumber() == null) {
            throw new IllegalArgumentException();
        }
    }
}
