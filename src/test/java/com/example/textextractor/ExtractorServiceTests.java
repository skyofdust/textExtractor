package com.example.textextractor;


import com.example.textextractor.model.WordResult;
import com.example.textextractor.service.impl.ExtractorServiceImpl;
import com.example.textextractor.service.FileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

public class ExtractorServiceTests extends TextExtractorApplicationTests{
    @MockBean
    private FileService fileService;

    @Autowired
    private ExtractorServiceImpl extractorService;

    @Test
    public void extractSentanceByWord_allArgsOk_returnsSentenceResult(){
        //prepare
        Mockito.when(fileService.getSentences()).thenReturn(generateSentences());

        //execute
        WordResult wordResult = extractorService.extractSentanceByWord("eggplant");

        //assert
        Assertions.assertNotNull(wordResult.getSentences());
        Assertions.assertNotNull(wordResult.getWord());
        Assertions.assertEquals("eggplant",wordResult.getWord());
        Assertions.assertNotNull(wordResult.getSentences());
        Assertions.assertEquals(7,wordResult.getSentences().size());
        Assertions.assertEquals("eggplant 1 eggplant", wordResult.getSentences().get(0).getSentence());
        Assertions.assertEquals(2, wordResult.getSentences().get(0).getCount());
        Assertions.assertEquals("eggplant", wordResult.getSentences().get(6).getSentence());
        Assertions.assertEquals(1, wordResult.getSentences().get(6).getCount());

    }

    @Test
    public void extractSentanceByWord_emptyWord_returnsSentenceResult(){
        //prepare
        Mockito.when(fileService.getSentences()).thenReturn(generateSentences());

        //execute
        WordResult wordResult = extractorService.extractSentanceByWord("");

        //assert
        Assertions.assertNotNull(wordResult.getSentences());
        Assertions.assertNotNull(wordResult.getWord());
        Assertions.assertEquals("",wordResult.getWord());
        Assertions.assertNotNull(wordResult.getSentences());
        Assertions.assertEquals(0,wordResult.getSentences().size());
    }

    @Test
    public void extractSentanceByWord_nullWord_returnsSentenceResult(){
        //prepare
        Mockito.when(fileService.getSentences()).thenReturn(generateSentences());

        //execute
        WordResult wordResult = extractorService.extractSentanceByWord(null);

        //assert
        Assertions.assertNotNull(wordResult.getSentences());
        Assertions.assertNull(wordResult.getWord());
        Assertions.assertEquals(null,wordResult.getWord());
        Assertions.assertNotNull(wordResult.getSentences());
        Assertions.assertEquals(0,wordResult.getSentences().size());
    }

    private List<String> generateSentences(){
        List<String> strings = Arrays.asList("eggplant", "eggplant eggplant", "eggplant 1 eggplant", "eggplant 2 eggplant", "elephant", "elephant elephant", "There is no eggplant in the basket of eggplants.", "Eggplant is not a plant.", "Where did you get that Eggplant, mate?");
        return strings;
    }
}
