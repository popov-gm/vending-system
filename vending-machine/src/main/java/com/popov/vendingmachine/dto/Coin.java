package com.popov.vendingmachine.dto;

import java.math.BigDecimal;

public class Coin {

    @ValidString(acceptedValues = {"10st", "20st", "50st", "1lv", "2lv"})
    private String denomination;

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public BigDecimal getValue() {
        switch (denomination) {
            case "10st":
                return BigDecimal.valueOf(0.1);
            case "20st":
                return BigDecimal.valueOf(0.2);
            case "50st":
                return BigDecimal.valueOf(0.5);
            case "1lv":
                return BigDecimal.valueOf(1);
            case "2lv":
                return BigDecimal.valueOf(2);
            default:
                return null;
        }
    }

}
