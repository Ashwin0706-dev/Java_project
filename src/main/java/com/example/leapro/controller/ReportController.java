package com.example.leapro.controller;

import com.example.leapro.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    @Autowired
    private ReportService reportService;


    // 1. Balance Sheet
    @GetMapping("/balance-sheet")
    public String balanceSheet() {

        return reportService.getBalanceSheet();
    }


    // 2. Profit and Loss
    @GetMapping("/profit-loss")
    public String profitLoss() {

        return reportService.getProfitLoss();
    }


    // 3. Budget Report
    @GetMapping("/budget")
    public String budgetReport() {

        return reportService.getBudgetReport();
    }
}