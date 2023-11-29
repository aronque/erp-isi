package com.system.backend.services;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de serviços relacionados aos relatórios
 * @author aronque
 */
@Service
public interface RelatorioService {


    /**
     * Retorna o tipo do relatório gerado com base na implementação serviço instanciado
     * @author aronque
     * @return String tipo do relatório
     */
    public String getRelatorioType();


    /**
     * Retorna as linhas do relatório para tratamento posterior
     * @author aronque
     * @return List<String> lista contendo cada linha do csv em uma posição da lista
     */
    public List<String> getRelatorioCsv();
}
