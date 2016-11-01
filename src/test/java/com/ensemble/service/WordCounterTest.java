package com.ensemble.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;
import java.util.*;

/**
 * Created by Ignas on 2016-10-31.
 */
public class WordCounterTest {

    private static WordCounter wordCounter;
    private static List<String> testList;
    private LinkedList<String> textFileList;

    @BeforeClass
    public static void init(){
        wordCounter = new WordCounter();
    }

    @Before
    public void initEmptyList(){
        testList = new ArrayList<>();
        textFileList = new LinkedList<>();
    }

    @Test
    public void getFilteredWordsList_SmallTextFile() throws Exception {
        textFileList.addAll(readFile("textFile.txt"));
        wordCounter.getFilteredWordsList(textFileList, testList);

        Assert.assertTrue("List is not Empty", textFileList.size() == 0);
        Assert.assertArrayEquals("Arrays are NOT equal",testList.toArray(), new String[]{"material", "maybe", "right", "maybe"});
    }

    @Test(expected = Exception.class)
    public void getFilteredWordsList_EmptyTextFile() throws Exception {
        textFileList.addAll(readFile("emptyTextFile.txt"));
        wordCounter.getFilteredWordsList(textFileList, testList);
    }

    @Test
    public void getFilteredWordsList_LargeTextFile() throws Exception {
        textFileList.addAll(readFile("largeTextFile.txt"));
        wordCounter.getFilteredWordsList(textFileList, testList);

        Assert.assertTrue("List is not Empty", textFileList.size() == 0);
        Assert.assertTrue("List has 100 words", testList.size() == 100);
        Assert.assertTrue("All words in list is TEXT", testList.stream().allMatch(t -> t.equals("text")));
    }

    @Test
    public void countWords() throws Exception {
        Map<String, Integer> expectedMap = new HashMap();
        expectedMap.put("material", 1);
        expectedMap.put("maybe", 2);
        expectedMap.put("right", 1);

        textFileList.addAll(readFile("textFile.txt"));
        wordCounter.getFilteredWordsList(textFileList, testList);

        Map<String, Integer> textMap = wordCounter.countWords(testList);
        Assert.assertTrue("Maps are not same", expectedMap.equals(textMap));
    }

    private List<String> readFile(String fileName) throws Exception {
        FileService fileService = new FileService();
        URI path = ClassLoader.getSystemResource(fileName).toURI();
        return fileService.readAndSortFile(path);
    }

}