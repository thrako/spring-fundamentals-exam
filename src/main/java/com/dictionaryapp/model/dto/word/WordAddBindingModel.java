package com.dictionaryapp.model.dto.word;

import com.dictionaryapp.model.enums.LanguageEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class WordAddBindingModel {

    @Size(min = 2, max = 40, message = "The term length must be between {min} and {max} characters!")
    @NotBlank(message = "The field should not be blank!")
    private String term;

    @Size(min = 2, max = 80, message = "The translation length must be between {min} and {max} characters!")
    @NotBlank(message = "The field should not be blank!")
    private String translation;

    @Size(min = 2, max = 200, message = "The example length must be between {min} and {max} characters!")
    @NotBlank(message = "The field should not be blank!")
    private String example;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent(message = "The input date must be in the past or present!")
    @NotNull(message = "The field is required!")
    private LocalDate inputDate;

    @NotNull(message = "You must select a language!")
    private LanguageEnum language;

    @JsonIgnore
    private String addedBy;

    public WordAddBindingModel() {

    }

    public String getTerm() {

        return term;
    }

    public WordAddBindingModel setTerm(String term) {

        this.term = term;
        return this;
    }

    public String getTranslation() {

        return translation;
    }

    public WordAddBindingModel setTranslation(String translation) {

        this.translation = translation;
        return this;
    }

    public String getExample() {

        return example;
    }

    public WordAddBindingModel setExample(String example) {

        this.example = example;
        return this;
    }

    public LocalDate getInputDate() {

        return inputDate;
    }

    public WordAddBindingModel setInputDate(LocalDate inputDate) {

        this.inputDate = inputDate;
        return this;
    }

    public LanguageEnum getLanguage() {

        return language;
    }

    public WordAddBindingModel setLanguage(LanguageEnum language) {

        this.language = language;
        return this;
    }

    public String getAddedBy() {

        return addedBy;
    }

    public WordAddBindingModel setAddedBy(String addedBy) {

        this.addedBy = addedBy;
        return this;
    }
}
