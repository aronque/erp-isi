package com.system.backend.util;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.system.backend.entities.Relatorio;

public class RelatorioModulo extends SimpleModule {
    {
        addDeserializer(Relatorio.class, new RelatorioDeserializer());
    }
}
