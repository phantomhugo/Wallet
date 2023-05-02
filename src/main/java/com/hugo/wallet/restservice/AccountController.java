package com.hugo.wallet.restservice;

import com.hugo.wallet.model.Account;
import com.hugo.wallet.service.AccountService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hugo
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/account")
    public Account create(@RequestBody Account account) {
        return accountService.create(account);
    }

    @GetMapping("/account/{id}")
    public Optional<Account> get(@PathVariable Integer id) {
        return accountService.get(id);
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }

}
