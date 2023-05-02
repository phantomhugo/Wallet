package com.hugo.wallet.restservice;

import com.hugo.wallet.exceptions.EmptyOrUnexistingUserException;
import com.hugo.wallet.exceptions.InaccesibleBalanceException;
import com.hugo.wallet.exceptions.NotEnoughFundsException;
import com.hugo.wallet.exceptions.TransferAmountIsZeroException;
import com.hugo.wallet.model.Transfer;
import com.hugo.wallet.service.AccountService;
import com.hugo.wallet.service.TransferService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hugo
 */
@RestController
public class TransferController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer/{accountId}")
    public Transfer makeTransfer(@RequestBody Transfer transfer, @PathVariable Integer accountId) throws InaccesibleBalanceException, NotEnoughFundsException, EmptyOrUnexistingUserException {
        return transferService.create(transfer, accountService.get(accountId).get());
    }

    @GetMapping("/transfer")
    public List<Transfer> listRecent(@RequestParam Double amount, @RequestParam Date date, @RequestParam int page, @RequestParam int pageSize) {
        return transferService.listRecent(amount, date, page, pageSize);
    }

    @ExceptionHandler(value = InaccesibleBalanceException.class)
    public ResponseEntity<Object> exception(InaccesibleBalanceException exception) {
        return new ResponseEntity<>("Balance not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotEnoughFundsException.class)
    public ResponseEntity<Object> exception(NotEnoughFundsException exception) {
        return new ResponseEntity<>("Not enough funds", HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(value = TransferAmountIsZeroException.class)
    public ResponseEntity<Object> exception(TransferAmountIsZeroException exception) {
        return new ResponseEntity<>("Amount can not be zero", HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(value = EmptyOrUnexistingUserException.class)
    public ResponseEntity<Object> exception(EmptyOrUnexistingUserException exception) {
        return new ResponseEntity<>("User does not exist or is null", HttpStatus.EXPECTATION_FAILED);
    }
}
