package com.dictionaryapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255)")
    private String username;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany
    private final List<WordEntity> addedWords = new ArrayList<>();


    public UserEntity() {

    }

    public String getUsername() {

        return username;
    }

    public UserEntity setUsername(String username) {

        this.username = username;
        return this;
    }

    public String getPassword() {

        return password;
    }

    public UserEntity setPassword(String password) {

        this.password = password;
        return this;
    }

    public String getEmail() {

        return email;
    }

    public UserEntity setEmail(String email) {

        this.email = email;
        return this;
    }

    public List<WordEntity> getAddedWords() {

        return Collections.unmodifiableList(addedWords);
    }

    public UserEntity addWord(WordEntity word) {

        this.addedWords.add(word);
        return this;
    }

    public UserEntity removeWord(WordEntity wordEntity) {

        this.addedWords.remove(wordEntity);
        return this;
    }

    public UserEntity removeAllWords() {

        this.addedWords.clear();
        return this;
    }
}
