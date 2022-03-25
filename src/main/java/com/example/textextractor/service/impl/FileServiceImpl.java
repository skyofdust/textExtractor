package com.example.textextractor.service.impl;

import com.example.textextractor.service.FileService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    private final ResourceLoader resourceLoader;

    private String filePath;

    private org.springframework.core.io.Resource resource;

    @Getter
    private List<String> sentences;

    public FileServiceImpl(@Value("${filePath}") String filePath, ResourceLoader resourceLoader) throws IOException {
        this.filePath = filePath;
        this.resourceLoader = resourceLoader;
        this.resource = resourceLoader.getResource("classpath:"+filePath);
        this.sentences = getFileWordMatrix(this.resource);
    }

    private List<String> getFileWordMatrix(org.springframework.core.io.Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
        return reader.lines()
                .filter(e -> !e.isBlank())
                .collect(Collectors.toList());
    }
}
