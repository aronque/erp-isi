package com.system.backend.util;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class CSVReader {

    public static List<String> read(String path) {
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(Path.of(path));

        } catch(Exception e) {

        }

        return linhas;
    }
}
