/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hugo.wallet.service;

import com.hugo.wallet.exceptions.EmptyOrUnexistingUserException;
import com.hugo.wallet.exceptions.InaccesibleBalanceException;
import com.hugo.wallet.exceptions.NotEnoughFundsException;
import com.hugo.wallet.model.Account;
import com.hugo.wallet.model.Balance;
import com.hugo.wallet.model.Destination;
import com.hugo.wallet.model.PaymentRequest;
import com.hugo.wallet.model.PaymentResponse;
import com.hugo.wallet.model.Source;
import com.hugo.wallet.model.SourceInformation;
import com.hugo.wallet.model.Transaction;
import com.hugo.wallet.model.Transfer;
import com.hugo.wallet.repository.TransferRepository;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author hugo
 */
@Service
public class TransferServiceImplementation implements TransferService {

    @Value("${service.gateway}")
    private String gateway;

    @Value("${service.endpoint.wallet.balance}")
    private String balanceEndpoint;
    @Value("${service.endpoint.wallet.transactions}")
    private String transactionsEndpoint;
    @Value("${service.endpoint.payments}")
    private String paymentsEndpoint;

    @Value("${transaction.fee}")
    private Double transactionFee;

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Transfer create(Transfer transfer, Account account) throws InaccesibleBalanceException, NotEnoughFundsException, EmptyOrUnexistingUserException {
        if (transfer.getUserId() == null) {
            throw new EmptyOrUnexistingUserException();
        }
        checkBalance(transfer);
        if (makeTransfer(transfer)) {
            createPayment(transfer, account);
        }
        return transferRepository.save(transfer);
    }

    private Destination buildDestination(Account account) {
        Destination destination = new Destination();
        destination.setName(account.getFirstName() + " " + account.getLastName());
        Account newAccount = new Account();
        newAccount.setRoutingNumber(account.getRoutingNumber());
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setCurrency("USD");
        destination.setAccount(newAccount);
        return destination;
    }

    private Source buildSource() {
        Source source = new Source();
        source.setType("COMPANY");
        SourceInformation sourceInformation = new SourceInformation();
        sourceInformation.setName("ONTOP INC");
        source.setSourceInformation(sourceInformation);
        Account account = new Account();
        account.setAccountNumber("0245253419");
        account.setCurrency("USD");
        account.setRoutingNumber("028444018");
        source.setAccount(account);
        return source;
    }

    private void createPayment(Transfer transfer, Account account) {
        RestTemplate restTemplate = new RestTemplate();
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(transfer.getAmount());
        paymentRequest.setSource(buildSource());
        paymentRequest.setDestination(buildDestination(account));

        URI targetUrl = UriComponentsBuilder.fromUriString(gateway) // Build the base link
                .path(paymentsEndpoint) // Add path
                .build() // Build the URL
                .encode() // Encode any URI items that need to be encoded
                .toUri();
        PaymentResponse lastTransaction = restTemplate.postForObject(targetUrl, paymentRequest, PaymentResponse.class);
        if (lastTransaction != null && lastTransaction.getRequestInfo().getStatus().equals("Processing")) {
            transfer.setStatus(Transfer.Status.IN_PROGRESS.getStatus());
            transfer.setTransactionId(lastTransaction.getPaymentInfo().getId());
        }
    }

    private void checkBalance(Transfer transfer) throws InaccesibleBalanceException, NotEnoughFundsException {
        RestTemplate restTemplate = new RestTemplate();
        URI targetUrl = UriComponentsBuilder.fromUriString(gateway) // Build the base link
                .path(balanceEndpoint) // Add path
                .queryParam("user_id", transfer.getUserId()) // Add one or more query params
                .build() // Build the URL
                .encode() // Encode any URI items that need to be encoded
                .toUri();
        Balance currentBalance = restTemplate.getForObject(targetUrl, Balance.class);
        if (currentBalance == null) {
            throw new InaccesibleBalanceException();
        }
        if (currentBalance.getBalance() < transactionFee * transfer.getAmount() / 100) {
            throw new NotEnoughFundsException();
        }
    }

    private boolean makeTransfer(Transfer transfer) {
        RestTemplate restTemplate = new RestTemplate();
        URI targetUrl = UriComponentsBuilder.fromUriString(gateway) // Build the base link
                .path(transactionsEndpoint) // Add path
                .build() // Build the URL
                .encode() // Encode any URI items that need to be encoded
                .toUri();
        Transaction lastTransaction = restTemplate.postForObject(targetUrl, transfer, Transaction.class);
        return lastTransaction != null && lastTransaction.getWalletTransactionId() != null;
    }

    @Override
    public List<Transfer> listRecent(Double amount, Date transactionDate, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return transferRepository.findAllByOrderByTransactionDateDesc(amount, transactionDate, pageable);
    }
}
