package com.example.leapro.service;

import com.example.leapro.model.Transaction;
import com.example.leapro.repository.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TransactionRepo transactionRepo;


    // 1. Balance Sheet
    public String getBalanceSheet() {

        List<Transaction> transactions =
                transactionRepo.findAll();

        double totalIncome = 0;
        double totalExpenses = 0;

        for (Transaction transaction : transactions) {

            if ("AIRLINE_PAYMENT".equals(transaction.getType())) {

                totalIncome += transaction.getAmount();
            }

            if ("VENDOR_PAYMENT".equals(transaction.getType())
                    || "CLAIM".equals(transaction.getType())) {

                totalExpenses += transaction.getAmount();
            }
        }

        return "Balance Sheet\n"
                + "Total Income: " + totalIncome + "\n"
                + "Total Expenses: " + totalExpenses + "\n"
                + "Net Balance: "
                + (totalIncome - totalExpenses);
    }


    // 2. Profit and Loss
    public String getProfitLoss() {

        List<Transaction> transactions =
                transactionRepo.findAll();

        double income = 0;
        double expenses = 0;

        for (Transaction transaction : transactions) {

            if ("AIRLINE_PAYMENT".equals(transaction.getType())) {

                income += transaction.getAmount();
            }

            if ("VENDOR_PAYMENT".equals(transaction.getType())
                    || "CLAIM".equals(transaction.getType())) {

                expenses += transaction.getAmount();
            }
        }

        double profit = income - expenses;

        return "Profit & Loss\n"
                + "Income: " + income + "\n"
                + "Expenses: " + expenses + "\n"
                + "Profit: " + profit;
    }


    // 3. Budget Report
    public String getBudgetReport() {

        List<Transaction> transactions =
                transactionRepo.findAll();

        double actualExpense = 0;

        for (Transaction transaction : transactions) {

            if ("VENDOR_PAYMENT".equals(transaction.getType())
                    || "CLAIM".equals(transaction.getType())) {

                actualExpense += transaction.getAmount();
            }
        }

        double plannedBudget = 1000000;
        double remaining = plannedBudget - actualExpense;

        return "Terminal Budget Report\n"
                + "Planned Budget: " + plannedBudget + "\n"
                + "Actual Expense: " + actualExpense + "\n"
                + "Remaining Budget: " + remaining;
    }
}