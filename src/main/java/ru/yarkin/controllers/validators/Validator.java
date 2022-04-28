package ru.yarkin.controllers.validators;

public interface Validator {
    ValidationResult checkAdd(String newKey, String newValue);
}
