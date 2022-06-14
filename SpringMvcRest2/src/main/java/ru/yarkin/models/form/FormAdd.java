package ru.yarkin.models.form;

import lombok.Data;

@Data
public class FormAdd {
    private Long sourceLanguage;
    private Long targetLanguage;
    private String key;
    private String value;

}
