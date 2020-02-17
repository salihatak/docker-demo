package com.asa.dockerdemo.controller;

import java.util.Optional;

import com.asa.dockerdemo.exception.InvalidTransationReferenceException;
import com.asa.dockerdemo.model.Transaction;
import com.asa.dockerdemo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TransactionController {

    final
    TransactionService transactionService;

    @PostMapping("/create")
    public Long createTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return transaction.getTransactionId();
    }

    @GetMapping("/viewAll")
    public Iterable<Transaction> viewAllTransactions() {
        return transactionService.getTransactionHistory();
    }

    @GetMapping("/findAll")
    public Page<Transaction> findAll(@RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue = "3") int pageSize,
                                     @RequestParam(defaultValue = "transactionId") String sortBy){
        return transactionService.findAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/view/{id}")
    public Transaction viewTransactionById(@PathVariable ("id") Long id) {
        Optional<Transaction> transaction = transactionService.getTransaction(id);
        if(transaction.isPresent()) {
            return transaction.get();
        }

        throw new InvalidTransationReferenceException("Invalid transaction reference provided");
    }
}
