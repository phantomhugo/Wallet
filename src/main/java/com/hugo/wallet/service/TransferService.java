package com.hugo.wallet.service;

import com.hugo.wallet.exceptions.EmptyOrUnexistingUserException;
import com.hugo.wallet.exceptions.InaccesibleBalanceException;
import com.hugo.wallet.exceptions.NotEnoughFundsException;
import com.hugo.wallet.model.Account;
import com.hugo.wallet.model.Transfer;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hugo
 */
public interface TransferService {

    Transfer create(Transfer transfer, Account account) throws InaccesibleBalanceException, NotEnoughFundsException, EmptyOrUnexistingUserException;

    List<Transfer> listRecent(Double amount, Date transactionDate, int page, int pageSize);
}
