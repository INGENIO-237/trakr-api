package com._naptic.trakr_api.shared.validators.enums;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<IsEnum, String> {

    private List<String> allowedValues;

    @Override
    public void initialize(IsEnum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        allowedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants()).map(Enum::name).map(String::toUpperCase).toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        return allowedValues.contains(value.toUpperCase());
    }
}
