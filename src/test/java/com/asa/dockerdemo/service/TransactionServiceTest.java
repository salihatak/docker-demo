package com.asa.dockerdemo.service;

import com.asa.dockerdemo.model.Transaction;
import com.asa.dockerdemo.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void saveTransaction_ShouldSaveTransactionToRepository() {
        // Given
        Transaction transaction = new Transaction();

        // When
        transactionService.saveTransaction(transaction);

        // Then
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void getTransactionHistory_ShouldReturnAllTransactionsFromRepository() {
        // Given
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());
        when(transactionRepository.findAll()).thenReturn(transactions);

        // When
        Iterable<Transaction> result = transactionService.getTransactionHistory();

        // Then
        assertEquals(transactions.size(), StreamSupport.stream(result.spliterator(), false).count());
    }

    @Test
    public void findAll_ShouldReturnAPageOfTransactionsFromRepository() {
        // Given
        int pageNo = 0;
        int pageSize = 10;
        String sortBy = "transactionDate";
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());
        Page<Transaction> expectedPage = new PageImpl<>(transactions);
        when(transactionRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

        // When
        Page<Transaction> result = transactionService.findAll(pageNo, pageSize, sortBy);

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    public void getTransaction_ShouldReturnATransactionFromRepositoryById() {
        // Given
        Long id = 1L;
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setTransactionId(id);
        when(transactionRepository.findById(id)).thenReturn(Optional.of(expectedTransaction));

        // When
        Optional<Transaction> result = transactionService.getTransaction(id);

        // Then
        assertTrue(result.isPresent());
        assertEquals(expectedTransaction, result.get());
    }
}