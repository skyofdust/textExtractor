package com.example.textextractor.service;

import com.example.textextractor.model.WordResult;

public interface ExtractorService {

    WordResult extractSentanceByWord(String word);

}
