package ru.yarkin.models.database;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "translate")
public class Translate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "source_word_id", nullable = false)
    private Word sourceWord;

    @ManyToOne()
    @JoinColumn(name = "target_word_id", nullable = false)
    private Word targetWord;

    public Translate() {
    }

    public Translate(Word sourceWord, Word targetWord) {
        this.sourceWord = sourceWord;
        this.targetWord = targetWord;
    }
}
