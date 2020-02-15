package com.asa.dockerdemo.controller;

import java.util.Optional;

import com.asa.dockerdemo.exception.InvalidTransationReferenceException;
import com.asa.dockerdemo.model.Transaction;
import com.asa.dockerdemo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/view/{id}")
    public Transaction viewTransactionById(@PathVariable ("id") Long id) {
        Optional<Transaction> transaction = transactionService.getTransaction(id);
        if(transaction.isPresent()) {
            return transaction.get();
        }

        throw new InvalidTransationReferenceException("Invalid transaction reference provided");
    }
}
