package com.system.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GetInjectedRelatorioService {

    private static Map<String, RelatorioService> servicesMap = null;

    @Autowired
    public GetInjectedRelatorioService(List<RelatorioService> service) {
        servicesMap = service.stream().collect(Collectors.toMap(RelatorioService::getRelatorioType, Function.identity()));
    }

    public static RelatorioService getServiceInstance(String type) {
        return servicesMap.get(type);
    }
}
