package ru.yarkin.models.demobase;

import javax.persistence.*;

@Entity
@Table(name = "translate")
public class Translate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_word_id", nullable = false)
    private Words sourceWordsId;

    @ManyToOne
    @JoinColumn(name = "source_language_id", nullable = false)
    private Languages sourceLanguageId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "target_word_id", nullable = false)
    private Words targetWordsId;

    @ManyToOne
    @JoinColumn(name = "target_language_id", nullable = false)
    private Languages targetLanguageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Words getSourceWordsId() {
        return sourceWordsId;
    }

    public void setSourceWordsId(Words sourceWordsId) {
        this.sourceWordsId = sourceWordsId;
    }

    public Languages getSourceLanguageId() {
        return sourceLanguageId;
    }

    public void setSourceLanguageId(Languages sourceLanguageId) {
        this.sourceLanguageId = sourceLanguageId;
    }

    public Words getTargetWordsId() {
        return targetWordsId;
    }

    public void setTargetWordsId(Words targetWordsId) {
        this.targetWordsId = targetWordsId;
    }

    public Languages getTargetLanguageId() {
        return targetLanguageId;
    }

    public void setTargetLanguageId(Languages targetLanguageId) {
        this.targetLanguageId = targetLanguageId;
    }

    public Translate(Words sourceWordsId, Languages sourceLanguageId, Words targetWordsId, Languages targetLanguageId) {
        this.sourceWordsId = sourceWordsId;
        this.sourceLanguageId = sourceLanguageId;
        this.targetWordsId = targetWordsId;
        this.targetLanguageId = targetLanguageId;
    }

    public Translate() {
    }
}
