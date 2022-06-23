package ru.yarkin.dictionary;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Dictionary {
    private Long sourceLanguageId;
    private Long targetLanguageId;

    private String sourceLanguage;
    private String targetLanguage;
    private String error;
}
