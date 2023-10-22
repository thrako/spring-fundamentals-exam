package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageEnum;

import javax.persistence.*;

@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageEnum name;

    @Column(nullable = false)
    private String description;


    public LanguageEntity() {

    }

    public LanguageEnum getName() {

        return name;
    }

    public LanguageEntity setName(LanguageEnum name) {

        this.name = name;
        return this;
    }

    public String getDescription() {

        return description;
    }

    public LanguageEntity setDescription(String description) {

        this.description = description;
        return this;
    }
}
