package ru.yarkin.models.form;

import lombok.Data;

@Data
public class FormDelete {
    private Long sourceLanguage;
    private Long targetLanguage;
    private String key;


}
