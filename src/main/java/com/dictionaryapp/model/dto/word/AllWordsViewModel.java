package com.dictionaryapp.model.dto.word;

import java.util.List;
import java.util.Map;

public class AllWordsViewModel {

    private Map<String, List<WordViewModel>> wordsMap;
    private Map<String, Integer> countsMap;

    public AllWordsViewModel() {

    }

    public Map<String, List<WordViewModel>> getWordsMap() {

        return wordsMap;
    }

    public AllWordsViewModel setWordsMap(Map<String, List<WordViewModel>> wordsMap) {

        this.wordsMap = wordsMap;
        return this;
    }

    public Map<String, Integer> getCountsMap() {

        return countsMap;
    }

    public AllWordsViewModel setCountsMap(Map<String, Integer> countsMap) {

        this.countsMap = countsMap;
        return this;
    }
}
