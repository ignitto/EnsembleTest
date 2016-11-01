package com.ensemble.service;

import java.util.*;

/**
 * Created by Ignas on 2016-10-31.
 */
public class WordCounter {

    /**
     * Filtering list using java recursion
     * @param sortedWordsList
     * @param filteredList
     * @return
     */
    public List<String> getFilteredWordsList(LinkedList<String> sortedWordsList, List<String> filteredList){

        String word = sortedWordsList.getFirst();

        if(filteredList.isEmpty() || filteredList.contains(word)){
            filteredList.add(word);
            sortedWordsList.removeFirst();
            return sortedWordsList.size()==0? filteredList: getFilteredWordsList(sortedWordsList, filteredList);
        }

        if(filteredList.stream().anyMatch(str -> str.toLowerCase().contains(word.toLowerCase()))){
            sortedWordsList.removeFirst();
            return sortedWordsList.size()==0? filteredList: getFilteredWordsList(sortedWordsList, filteredList);
        }else{
            filteredList.add(word);
            sortedWordsList.removeFirst();
        }
        return sortedWordsList.size()==0? filteredList: getFilteredWordsList(sortedWordsList, filteredList);
    }

    /**
     * Counting occurrences
     * @param wordList
     * @return occurences
     */
    public Map<String, Integer> countWords(List<String> wordList){
        Map<String, Integer> occurrences = new HashMap<>();
        for(String word: wordList) {
            Integer count = Collections.frequency(wordList, word);
            occurrences.put(word, count);
        }
        return occurrences;
    }
}
