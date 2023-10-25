package com.popov.vendingmachine.controller.impl;

import com.popov.vendingmachine.controller.ProductController;
import com.popov.vendingmachine.dto.OrderResult;
import com.popov.vendingmachine.dto.Product;
import com.popov.vendingmachine.service.VendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductControllerImpl implements ProductController {

    private VendingService vendingService;

    @Autowired
    public ProductControllerImpl(VendingService vendingService) {
        this.vendingService = vendingService;
    }

    @Override
    public ResponseEntity<OrderResult> buyProduct(String code) {
        return  ResponseEntity.ok(vendingService.buyProduct(code));
    }

}
