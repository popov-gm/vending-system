package com.popov.vendingmachine.service.impl;

import com.popov.vendingmachine.dto.*;
import com.popov.vendingmachine.service.MoneyService;
import com.popov.vendingmachine.service.ProductService;
import com.popov.vendingmachine.service.VendingService;
import com.popov.vendingmachine.service.state.MaintenanceState;
import com.popov.vendingmachine.service.state.State;
import com.popov.vendingmachine.util.MoneyUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class VendingServiceImpl implements VendingService {

    private MoneyService moneyService;
    private ProductService productService;

    private State currentState = new MaintenanceState(this);

//    private List<Coin> insertedCoins = new ArrayList<>();



    public VendingServiceImpl(MoneyService moneyService, ProductService productService) {
        this.moneyService = moneyService;
        this.productService = productService;
    }

    // region DelegatedToState

    public InsertCoinResult insertCoin(Coin coin) {
        BigDecimal totalCredit  = currentState.insertCoin(coin);
        return new InsertCoinResult(MoneyUtil.toLvFormat(coin.getValue()), MoneyUtil.toLvFormat(totalCredit));
    }

    @Override
    public ReturnedCreditResult returnCredit() {
        return this.currentState.returnCredit();
    }

    @Override
    public OrderResult buyProduct(String code) {
        return currentState.buyProduct(code);
    }

    @Override
    public Product loadProduct(Product product) {
        return currentState.loadProduct(product);
    }

    @Override
    public Product removeProduct(String code) {
        return currentState.removeProduct(code);
    }

    @Override
    public Product updateProductPrice(String code, BigDecimal price) {
        return currentState.updateProductPrice(code, price);
    }

    public void setOperationalMode() {
        currentState.setOperationalMode();
    }

    public void setMaintenanceMode() {
        currentState.setMaintenanceMode();
    }

    // endregion DelegatedToState

    // region DelegatedToOtherServices

    @Override
    public BigDecimal addCredit(BigDecimal money) {
        return moneyService.addCredit(money);
    }

    @Override
    public BigDecimal returnChange(BigDecimal price) {
        return moneyService.returnChange(price);
    }

    @Override
    public BigDecimal getCredit() {
        return moneyService.getCredit();
    }

    @Override
    public void resetCredit() {
        moneyService.resetCredit();
    }

    @Override
    public void verifySufficientCredit(BigDecimal price) {
        moneyService.verifySufficientCredit(price);
    }

    @Override
    public void collectSum(BigDecimal price) {
        moneyService.collectSum(price);
    }

    @Override
    public Product addProduct(Product product) {
        return this.productService.addProduct(product);
    }

    @Override
    public String getProduct(String code) {
        return productService.getProduct(code);
    }

    @Override
    public Product deleteProduct(String code) {
       return productService.deleteProduct(code);
    }

    @Override
    public void validateProductAvailable(String code) {
        productService.validateProductAvailable(code);
    }

    @Override
    public BigDecimal getPrice(String code) {
        return productService.getPrice(code);
    }

    @Override
    public Product updateProduct(String code, BigDecimal price) {
        return productService.updateProduct(code, price);
    }

    @Override
    public MachineState getMachineState() {
        return currentState.getMachineState();
    }

    @Override
    public MachineState getMachineInfo() {
        List<Product> availableProducts = productService.listAvailableProducts();
        String collectedMoney = MoneyUtil.toLvFormat(moneyService.getTotalMoney());
        return new MachineState(availableProducts, collectedMoney);
    }

    // endregion DelegatedToOtherServices

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

}
