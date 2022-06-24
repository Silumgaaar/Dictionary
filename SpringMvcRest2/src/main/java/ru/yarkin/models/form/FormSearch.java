package ru.yarkin.models.form;

import lombok.Data;

@Data
public class FormSearch {
    private Long sourceLanguage;
    private Long targetLanguage;
    private String key;
}
