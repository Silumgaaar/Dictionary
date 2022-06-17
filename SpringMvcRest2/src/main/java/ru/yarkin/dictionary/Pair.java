package ru.yarkin.dictionary;

import lombok.Data;

import ru.yarkin.models.database.Word;

@Data
public class Pair {
    private Word sourceWord;
    private Word targetWord;

    public Pair(Word sourceWord, Word targetWord){
        this.sourceWord = sourceWord;
        this.targetWord = targetWord;
    }

    public Pair(){

    }
}
