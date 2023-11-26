package com.system.backend.services;

import com.system.backend.entities.Grafico;
import org.springframework.stereotype.Service;

@Service
public interface GraficoService {

    public Grafico getData(Grafico grafico);
}
