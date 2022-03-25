package com.example.textextractor.api;

import com.example.textextractor.model.WordResult;
import com.example.textextractor.service.ExtractorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicApi {

    private final ExtractorService extractorService;

    public PublicApi(ExtractorService extractorService) {
        this.extractorService = extractorService;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public WordResult getWordCounts(@RequestParam(name="word", required = false) String word){
        return extractorService.extractSentanceByWord(word);
    }
}
