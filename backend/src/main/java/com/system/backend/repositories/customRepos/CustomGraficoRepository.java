package com.system.backend.repositories.customRepos;

import com.system.backend.entities.Grafico;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomGraficoRepository {

    public Grafico getData(Grafico grafico);
}
