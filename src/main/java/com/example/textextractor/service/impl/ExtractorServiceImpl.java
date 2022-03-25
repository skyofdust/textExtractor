package com.example.textextractor.service.impl;

import com.example.textextractor.model.SentenceResult;
import com.example.textextractor.model.WordResult;
import com.example.textextractor.service.ExtractorService;
import com.example.textextractor.service.FileService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtractorServiceImpl implements ExtractorService {

    private final FileService fileService;

    public ExtractorServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    public WordResult extractSentenceByWord(String word){
        List<String> sentences = fileService.getSentences();
        List<SentenceResult> sentenceResults = getSentenceResults(word, sentences);
        return new WordResult(word,sentenceResults);
    }

    private List<SentenceResult> getSentenceResults(String word, List<String> sentences) {
        return sentences.stream()
                .map(e -> new SentenceResult(e, countWords(e, word)))
                .filter(e -> e.getCount() > 0)
                .sorted(Comparator.comparing(SentenceResult::getCount).reversed().thenComparing(SentenceResult::getSentence))
                .collect(Collectors.toList());
    }

    private Integer countWords(String sentence, String word) {
        return Arrays.stream(sentence.split(" "))
                .map(this::removeSpecialCharacters)
                .filter(e -> !e.isBlank() && e.equalsIgnoreCase(word))
                .collect(Collectors.toList())
                .size();
    }

    private String removeSpecialCharacters(String e) {
        return e.replaceAll("[^a-zA-Z]", "");
    }
}
