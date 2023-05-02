package com.hugo.wallet.repository;

import com.hugo.wallet.model.Transfer;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hugo
 */
public interface TransferRepository extends JpaRepository<Transfer, String> {

    List<Transfer> findAllByOrderByTransactionDateDesc(Double amount, Date transactionDate, Pageable pageable);
}
