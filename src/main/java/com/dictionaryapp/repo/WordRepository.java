package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<WordEntity, Long> {

}
