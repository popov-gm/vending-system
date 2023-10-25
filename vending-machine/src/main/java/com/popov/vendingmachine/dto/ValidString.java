package com.popov.vendingmachine.dto;

import jakarta.validation.Payload;

public @interface ValidString {

    String[] acceptedValues();

    String message() default "Value is not valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
