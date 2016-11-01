package com.ensemble.service;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ignas on 2016-10-31.
 */

public class FileService {
    /**
     * Reading file, Storing to List and Sorting
     * @param path
     * @return
     * @throws Exception
     */
    public List<String> readAndSortFile(URI path) throws Exception {
        final List<String> wordList = Files.lines(Paths.get(path))
                .map(line -> line.split("[\\s]+"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        if(wordList.size() == 0){
            throw new Exception("File is empty");
        }

        //sorting List
        wordList.sort(Comparator.comparing(String::length).reversed());
        return wordList;
    }
}
