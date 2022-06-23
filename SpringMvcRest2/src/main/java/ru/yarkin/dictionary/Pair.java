package ru.yarkin.dictionary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yarkin.dao.word.WordDao;
import ru.yarkin.models.database.Word;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Component
public class Pair {

    private Word sourceWord;
    private Word targetWord;

    public Pair(Word sourceWord, Word targetWord) {
        this.sourceWord = sourceWord;
        this.targetWord = targetWord;
    }

    public String getValueSourceWord() {
        return sourceWord.getValue();
    }

}
