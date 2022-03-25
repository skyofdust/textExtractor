package com.example.textextractor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SentenceResult {
    private String sentence;
    private Integer count;
}
