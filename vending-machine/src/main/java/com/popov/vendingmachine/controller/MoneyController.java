package com.popov.vendingmachine.controller;

import com.popov.vendingmachine.dto.Coin;
import com.popov.vendingmachine.dto.InsertCoinResult;
import com.popov.vendingmachine.dto.ReturnedCreditResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/money")
public interface MoneyController {

    @PostMapping()
    ResponseEntity<InsertCoinResult> insertCoin(@Validated @RequestBody Coin coin);

    @GetMapping("/return")
    ResponseEntity<ReturnedCreditResult> returnCredit();

}

