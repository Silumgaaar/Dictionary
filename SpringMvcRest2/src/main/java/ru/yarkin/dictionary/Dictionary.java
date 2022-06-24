package ru.yarkin.dictionary;

import lombok.Data;

@Data
public class Dictionary {
    private Long sourceLanguageId;
    private Long targetLanguageId;

    private String sourceLanguage;
    private String targetLanguage;
    private String error;
}
