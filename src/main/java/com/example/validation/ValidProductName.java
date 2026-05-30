package com.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProductName {

    String message() default
            "Product name contains forbidden word";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}