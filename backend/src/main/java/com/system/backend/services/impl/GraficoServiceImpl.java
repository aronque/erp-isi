package com.system.backend.services.impl;

import com.system.backend.entities.Grafico;
import com.system.backend.repositories.customRepos.CustomGraficoRepository;
import com.system.backend.services.GraficoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraficoServiceImpl implements GraficoService {

    @Autowired
    private CustomGraficoRepository repository;

    @Override
    public Grafico getData(Grafico grafico) {
        return repository.getData(grafico);
    }
}
