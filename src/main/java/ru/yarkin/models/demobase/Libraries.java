package ru.yarkin.models.demobase;

import javax.persistence.*;

@Entity
@Table(name = "libraries")
public class Libraries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "source_language_id",nullable = false)
    private Languages sourceLanguageId;

    @ManyToOne()
    @JoinColumn(name = "target_language_id",nullable = false)
    private Languages targetLanguageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Languages getSourceLanguageId() {
        return sourceLanguageId;
    }

    public void setSourceLanguageId(Languages sourceLanguageId) {
        this.sourceLanguageId = sourceLanguageId;
    }

    public Languages getTargetLanguageId() {
        return targetLanguageId;
    }

    public void setTargetLanguageId(Languages targetLanguageId) {
        this.targetLanguageId = targetLanguageId;
    }
}
