package com.popov.vendingmachine.controller.impl;

import com.popov.vendingmachine.controller.MoneyController;
import com.popov.vendingmachine.dto.Coin;
import com.popov.vendingmachine.dto.InsertCoinResult;
import com.popov.vendingmachine.dto.ReturnedCreditResult;
import com.popov.vendingmachine.service.VendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoneyControllerImpl implements MoneyController {

    private VendingService vendingService;


    @Autowired
    public MoneyControllerImpl(VendingService vendingService) {
        this.vendingService = vendingService;
    }


    @Override
    public ResponseEntity<InsertCoinResult> insertCoin(Coin coin) {
        return ResponseEntity.ok(vendingService.insertCoin(coin));
    }

    @Override
    public ResponseEntity<ReturnedCreditResult> returnCredit() {
        return ResponseEntity.ok(vendingService.returnCredit());
    }
}
