package com.hugo.wallet.service;

import com.hugo.wallet.model.Account;
import com.hugo.wallet.model.Transfer;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hugo
 */
public class TransferServiceTestImplementation implements TransferService {

    @Override
    public Transfer create(Transfer transfer, Account account) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Transfer> listRecent(Double amount, Date transactionDate, int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
