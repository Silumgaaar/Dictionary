package ru.yarkin.dictionary;

import lombok.Data;

import ru.yarkin.models.database.Word;

@Data
public class Pair {
    private Word sourceWord;
    private Word targetWord;

}
