package ru.yarkin.service.validators;

public interface Validator {
    ValidationResult checkAdd(String newKey, String newValue);
}
