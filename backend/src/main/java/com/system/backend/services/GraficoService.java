package com.system.backend.services;

import com.system.backend.entities.Grafico;
import org.springframework.stereotype.Service;

/**
 * Classe de serviços relacionados aos gráficos
 * @author aronque
 */
@Service
public interface GraficoService {


    /**
     * Busca os dados referente ao gráfico requisitado
     * @author aronque
     * @param grafico Objeto grafico com as informações do gráfico solicitado
     * @return Grafico retorna o mesmo objeto com os dados x e y populados
     */
    public Grafico getData(Grafico grafico);
}
