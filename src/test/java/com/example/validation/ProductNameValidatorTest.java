package com.example.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

class ProductNameValidatorTest {
    private final ProductNameValidator validator = new ProductNameValidator();

    @ParameterizedTest
    @MethodSource("productNames")
    void testProductNamesValidator(String productName, boolean expected) {
        boolean isValid = validator.isValid(productName, null);
        assertEquals(expected, isValid);
    }

    static Stream<Arguments> productNames() {
        return Stream.of(
                of("iPhone", true),
                of("Samsung Galaxy", true),
                of("TEST product", false),
                of("Dummy item", false),
                of("FAKE phone", false),
                of("", true),
                of(null, true)
        );
    }
}