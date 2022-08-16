package ru.yarkin.models.demobase;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "words", uniqueConstraints = {@UniqueConstraint(columnNames = {"value"})} )
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", unique = true)
    private String value;

    @ManyToOne
    @JoinColumn(name = "language_id",nullable = false)
    private Languages languages_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Languages getLanguages_id() {
        return languages_id;
    }

    public void setLanguages_id(Languages languages_id) {
        this.languages_id = languages_id;
    }

    public Words(String value, Languages languages_id) {
        this.value = value;
        this.languages_id = languages_id;
    }

    public Words() {
    }
}
