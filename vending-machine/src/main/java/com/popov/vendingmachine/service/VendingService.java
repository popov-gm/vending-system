package com.popov.vendingmachine.service;

import com.popov.vendingmachine.dto.*;
import com.popov.vendingmachine.service.state.State;

import java.math.BigDecimal;


public interface VendingService {

    // region DelegatedToState

    InsertCoinResult insertCoin(Coin coin);

    ReturnedCreditResult returnCredit();

    OrderResult buyProduct(String code);

    Product loadProduct(Product product);

    Product removeProduct(String code);

    Product updateProductPrice(String code, BigDecimal price);

    MachineState getMachineState();

    void setOperationalMode();

    void setMaintenanceMode();

    // endregion DelegatedToState

    BigDecimal addCredit(BigDecimal money);

    BigDecimal returnChange(BigDecimal price);

    BigDecimal getCredit();

    void resetCredit();

    void verifySufficientCredit(BigDecimal price);

    void collectSum(BigDecimal price);

    Product addProduct(Product product);

    String getProduct(String code);

    Product deleteProduct(String code);

    void validateProductAvailable(String code);

    BigDecimal getPrice(String code);

    Product updateProduct(String code, BigDecimal price);

    MachineState getMachineInfo();

    State getCurrentState();

    void setCurrentState(State currentState);


}
