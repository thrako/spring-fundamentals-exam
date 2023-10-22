package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.word.AllWordsViewModel;
import com.dictionaryapp.model.dto.word.WordAddBindingModel;
import com.dictionaryapp.model.dto.word.WordViewModel;
import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.model.entity.WordEntity;
import com.dictionaryapp.model.enums.LanguageEnum;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    public WordService(WordRepository wordRepository,
                       LanguageRepository languageRepository,
                       UserRepository userRepository) {

        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
    }

    public AllWordsViewModel getAllGroupedByLanguage() {

        final List<WordEntity> allWords = this.wordRepository.findAll();

        final Map<String, List<WordViewModel>> wordsMap = allWords.stream()
                .map(WordService::mapToViewModel)
                .collect(Collectors.groupingBy(vm -> vm.getLanguage().name()));

        Arrays.stream(LanguageEnum.values())
                .map(Enum::name)
                .forEach(l -> wordsMap.putIfAbsent(l, new ArrayList<>()));

        final Map<String, Integer> countsMap = new HashMap<>();
        wordsMap.forEach((key, value) -> countsMap.put(key, value.size()));
        countsMap.put("ALL", allWords.size());

        return new AllWordsViewModel()
                .setWordsMap(wordsMap)
                .setCountsMap(countsMap);
    }

    private static WordViewModel mapToViewModel(WordEntity e) {

        return new WordViewModel()
                .setId(e.getId())
                .setTerm(e.getTerm())
                .setLanguage(e.getLanguage().getName())
                .setTranslation(e.getTranslation())
                .setExample(e.getExample())
                .setAddedBy(e.getAddedBy().getUsername())
                .setInputDate(e.getInputDate());
    }

    @Transactional
    public void removeById(Long wordId) {

        final WordEntity wordEntity = this.wordRepository.findById(wordId).orElseThrow();
        final UserEntity userEntity = wordEntity.getAddedBy();
        userEntity.removeWord(wordEntity);
        this.wordRepository.delete(wordEntity);
        this.userRepository.saveAndFlush(userEntity);
    }

    @Transactional
    public void removeAll() {

        final List<UserEntity> userEntities = this.userRepository.findAll();
        userEntities.forEach(UserEntity::removeAllWords);
        this.wordRepository.deleteAll();
        userRepository.saveAll(userEntities);
    }

    @Transactional
    public void addWord(WordAddBindingModel wordAddBindingModel, String username) {

        final UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        final WordEntity wordEntity = mapToEntity(wordAddBindingModel, userEntity);
        userEntity.addWord(wordEntity);
        wordRepository.save(wordEntity);
        userRepository.saveAndFlush(userEntity);
    }

    private WordEntity mapToEntity(WordAddBindingModel wordAddBindingModel, UserEntity userEntity) {

        return new WordEntity()
                .setTerm(wordAddBindingModel.getTerm())
                .setLanguage(languageRepository.findByName(wordAddBindingModel.getLanguage()).orElseThrow())
                .setTranslation(wordAddBindingModel.getTranslation())
                .setExample(wordAddBindingModel.getExample())
                .setInputDate(wordAddBindingModel.getInputDate())
                .setAddedBy(userEntity);
    }
}
