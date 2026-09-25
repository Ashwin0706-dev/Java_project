package com.example.leapro.controller;

import com.example.leapro.model.Transaction;
import com.example.leapro.service.BillingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin
public class BillingController {

    @Autowired
    private BillingService billingService;


    // 1. Create airline invoice
    @PostMapping("/invoice")
    public String createInvoice(@RequestBody Transaction transaction) {

        return billingService.createTransaction(transaction);
    }


    // 2. Record payment
    @PostMapping("/payment")
    public String recordPayment(@RequestBody Transaction transaction) {

        return billingService.createTransaction(transaction);
    }


    // 3. Create purchase order
    @PostMapping("/purchase")
    public String createPurchase(@RequestBody Transaction transaction) {

        return billingService.createTransaction(transaction);
    }


    // 4. Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {

        return billingService.getAllTransactions();
    }
}