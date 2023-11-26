package com.system.backend.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.system.backend.entities.Relatorio;

import java.io.IOException;

public class RelatorioDeserializer extends StdDeserializer<Relatorio> {

    public RelatorioDeserializer() {
        super(Relatorio.class);
    }

    @Override
    public Relatorio deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return null;
    }
}
