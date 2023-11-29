package com.system.backend.repositories.customRepos;

import org.springframework.stereotype.Repository;

/**
 * Repository customizado para operações em banco relacionadas aos relatórios
 * @author aronque
 */
@Repository
public interface CustomRelatorioRepository {


    /**
     * Método para chamar a operação em banco que gerará o .csv para posterior geração de relatório
     * @author aronque
     * @param relatorioClass .class do tipo de relatório a ser gerado
     */
    public void execRelatorio(Class<?> relatorioClass);
}
