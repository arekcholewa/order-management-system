package com.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {

    private static final Set<String> FORBIDDEN_WORDS = Set.of("test", "dummy", "fake");

    @Override
    public void initialize(ValidProductName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        String lowerCaseValue = value.toLowerCase();

        return FORBIDDEN_WORDS.stream()
                .noneMatch(lowerCaseValue::contains);
    }

}