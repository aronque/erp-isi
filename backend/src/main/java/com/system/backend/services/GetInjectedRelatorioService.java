package com.system.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe de serviço que recupera os serviços especificos de cada tipo de relatório já com a dependência injetada
 * @author aronque
 */
@Service
public class GetInjectedRelatorioService {

    private static Map<String, RelatorioService> servicesMap = null;

    @Autowired
    public GetInjectedRelatorioService(List<RelatorioService> service) {
        servicesMap = service.stream().collect(Collectors.toMap(RelatorioService::getRelatorioType, Function.identity()));
    }


    /**
     * Busca no map de serviços a instância do serviço correto para o tipo de relatório
     * @author aronque
     * @param type String contendo o tipo do relatório, utilizado como chave do map de serviços
     * @return RelatorioService retorna a instância do serviço requisitado
     */
    public static RelatorioService getServiceInstance(String type) {
        return servicesMap.get(type);
    }
}
