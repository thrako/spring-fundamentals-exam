package com.dictionaryapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "words")
public class WordEntity extends BaseEntity {

    @Size(min = 2, max = 40)
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String term;

    @Size(min = 2, max = 80)
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String translation;

    @Size(min = 2, max = 200)
    @Column(columnDefinition = "TEXT")
    private String example;

    @PastOrPresent
    @Column(nullable = false)
    private LocalDate inputDate;

    @ManyToOne(optional = false)
    private LanguageEntity language;

    @ManyToOne(optional = false)
    private UserEntity addedBy;


    public WordEntity() {

    }

    public String getTerm() {

        return term;
    }

    public WordEntity setTerm(String term) {

        this.term = term;
        return this;
    }

    public String getTranslation() {

        return translation;
    }

    public WordEntity setTranslation(String translation) {

        this.translation = translation;
        return this;
    }

    public String getExample() {

        return example;
    }

    public WordEntity setExample(String example) {

        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {

        return inputDate;
    }

    public WordEntity setInputDate(LocalDate inputDate) {

        this.inputDate = inputDate;
        return this;
    }

    public LanguageEntity getLanguage() {

        return language;
    }

    public WordEntity setLanguage(LanguageEntity language) {

        this.language = language;
        return this;
    }

    public UserEntity getAddedBy() {

        return addedBy;
    }

    public WordEntity setAddedBy(UserEntity addedBy) {

        this.addedBy = addedBy;
        return this;
    }
}
