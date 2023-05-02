package com.hugo.wallet.repository;

import com.hugo.wallet.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hugo
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
