package com.ensemble.counter;

import com.ensemble.service.FileService;
import com.ensemble.service.WordCounter;

import java.net.URI;
import java.util.LinkedList;
import java.util.Map;

public class DemoCounter {

    public static void main(String[] args) throws Exception {
        FileService service = new FileService();
        LinkedList<String> lines = new LinkedList<>();
        URI path = ClassLoader.getSystemResource("textFile.txt").toURI();
        lines.addAll(service.readAndSortFile(path));

        System.out.println("************** Actual List *********************");
        for (String word : lines) {
            System.out.println(word);
        }

        WordCounter counter = new WordCounter();
        LinkedList<String> filtered = new LinkedList<>();
        counter.getFilteredWordsList(lines, filtered);

        System.out.println("**********  Filtered List  **************");
        for (String s : filtered) {
            System.out.println(s);
        }

        Map<String, Integer> map = counter.countWords(filtered);

        System.out.println("******* Counted Words   ********");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
