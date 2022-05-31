package ru.yarkin.service.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidatorDictionary implements Validator {
    private static final String INCORRECT_KEY = "Неправильный шаблон ключа";
    private static final String INCORRECT_VALUE = "Неправильный шаблон значения";

    private final Pattern rulesKey;
    private final Pattern rulesValue;

    public ValidatorDictionary(String rulesKey, String rulesValue){
        this.rulesKey = Pattern.compile(rulesKey);
        this.rulesValue = Pattern.compile(rulesValue);
    }

    public ValidationResult checkAdd(String newKey, String newValue){

        boolean isValidKey = rulesKey.matcher(newKey).matches();
        boolean isValidValue = rulesValue.matcher(newValue).matches();

        List<String> errorsMessages = new ArrayList<>();
        if (!isValidKey) {
            errorsMessages.add(INCORRECT_KEY);
        }
        if (!isValidValue) {
            errorsMessages.add(INCORRECT_VALUE);
        }
        ValidationResult validationResult;
        validationResult = new ValidationResult(errorsMessages);

        return validationResult;
    }

}
