package com.asa.dockerdemo.repository;

import com.asa.dockerdemo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{

}