package com.popov.vendingmachine.controller;

import com.popov.vendingmachine.dto.OrderResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/product")
public interface ProductController {

    @GetMapping("/{code}")
    ResponseEntity<OrderResult> buyProduct(@PathVariable(value = "code")  String code);

}
