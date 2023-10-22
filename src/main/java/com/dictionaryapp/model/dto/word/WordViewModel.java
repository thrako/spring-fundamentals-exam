package com.dictionaryapp.model.dto.word;

import com.dictionaryapp.model.enums.LanguageEnum;

import java.time.LocalDate;

public class WordViewModel {


    private Long id;

    private String term;

    private String translation;

    private String example;

    private LocalDate inputDate;

    private LanguageEnum language;

    private String addedBy;

    public WordViewModel() {

    }

    public Long getId() {

        return id;
    }

    public WordViewModel setId(Long id) {

        this.id = id;
        return this;
    }

    public String getTerm() {

        return term;
    }

    public WordViewModel setTerm(String term) {

        this.term = term;
        return this;
    }

    public String getTranslation() {

        return translation;
    }

    public WordViewModel setTranslation(String translation) {

        this.translation = translation;
        return this;
    }

    public String getExample() {

        return example;
    }

    public WordViewModel setExample(String example) {

        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {

        return inputDate;
    }

    public WordViewModel setInputDate(LocalDate inputDate) {

        this.inputDate = inputDate;
        return this;
    }

    public LanguageEnum getLanguage() {

        return language;
    }

    public WordViewModel setLanguage(LanguageEnum language) {

        this.language = language;
        return this;
    }

    public String getAddedBy() {

        return addedBy;
    }

    public WordViewModel setAddedBy(String addedBy) {

        this.addedBy = addedBy;
        return this;
    }
}
