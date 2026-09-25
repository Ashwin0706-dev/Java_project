package com.example.leapro.service;

import com.example.leapro.model.Transaction;
import com.example.leapro.repository.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    @Autowired
    private TransactionRepo transactionRepo;


    // Create transaction
    public String createTransaction(Transaction transaction) {

        transactionRepo.save(transaction);

        return "Transaction created successfully";
    }


    // Get all transactions
    public List<Transaction> getAllTransactions() {

        return transactionRepo.findAll();
    }
}