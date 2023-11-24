package com.system.backend.services.impl;

import com.system.backend.entities.RelatorioPedidoPendente;
import com.system.backend.repositories.customRepos.CustomRelatorioRepository;
import com.system.backend.services.RelatorioService;
import com.system.backend.util.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Pedido_Pendente")
public class RelatorioPedidoPendenteServiceImpl implements RelatorioService {

    private static final String PATH = "\\src\\main\\resources\\temp\\pedidos_pendente.csv";

    @Autowired
    CustomRelatorioRepository repository;

    @Override
    public List<String> getRelatorioCsv() {

        repository.execRelatorio(RelatorioPedidoPendente.class);

        String path = System.getProperty("user.dir") + PATH;
        List<String> rows = null;

        try {
            rows = CSVReader.read(path);
        } catch(Exception e) {

        }

        return rows;
    }

    public String getRelatorioType() {
        return "Pedido_Pendente";
    }
}
