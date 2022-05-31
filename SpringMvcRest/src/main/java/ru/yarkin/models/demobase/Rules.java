package ru.yarkin.models.demobase;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule")
    private String rule;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", nullable = false)
    private Languages languages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }
}
