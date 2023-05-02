package com.hugo.wallet.service;

import com.hugo.wallet.model.Account;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author hugo
 */
public interface AccountService {

    Account create(Account account);

    Optional<Account> get(Integer id);

    void delete(Integer id);
}
