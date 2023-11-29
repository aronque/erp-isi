package com.system.backend.repositories.customRepos;

import com.system.backend.entities.Grafico;
import org.springframework.stereotype.Repository;

/**
 * Repository customizado para operações em banco relacionadas aos gráficos do sistema
 * @author aronque
 */
@Repository
public interface CustomGraficoRepository {


    /**
     * Método para chamar a operação em banco que buscará os dados referente ao gráfico requisitado
     * @author aronque
     * @param grafico Objeto com as informações do gráfico a ser gerado
     */
    public Grafico getData(Grafico grafico);
}
