package com.asa.dockerdemo.repository;

import com.asa.dockerdemo.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> { }