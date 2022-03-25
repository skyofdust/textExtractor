package com.example.textextractor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WordResult {
    private String word;
    private List<SentenceResult> sentences;
}
