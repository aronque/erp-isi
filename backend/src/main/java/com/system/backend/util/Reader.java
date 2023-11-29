package com.system.backend.util;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Classe utilitária para leitura de arquivos
 * @author aronque
 */
@Component
public class Reader {


    /**
     * Retorna uma lista com as linhas do arquivo mapeadas em posições da lista
     * @author aronque
     * @param path caminho absoluto para o arquivo
     * @return List<String> lista contendo as linhas do arquivo
     */
    public static List<String> read(String path) {
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(Path.of(path));

        } catch(Exception e) {

        }

        return linhas;
    }
}
