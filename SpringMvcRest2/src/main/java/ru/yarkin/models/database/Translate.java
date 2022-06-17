package ru.yarkin.models.database;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    public Translate(Word sourceWord, Word targetWord) {
        this.sourceWord = sourceWord;
        this.targetWord = targetWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Translate translate = (Translate) o;
        return id != null && Objects.equals(id, translate.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
