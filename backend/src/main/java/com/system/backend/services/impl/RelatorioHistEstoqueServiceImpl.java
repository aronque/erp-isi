package com.system.backend.services.impl;

import com.system.backend.entities.RelatorioHistEstoque;
import com.system.backend.repositories.customRepos.CustomRelatorioRepository;
import com.system.backend.services.RelatorioService;
import com.system.backend.util.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Hist_Estoque")
public class RelatorioHistEstoqueServiceImpl implements RelatorioService {

    private static final String PATH = "\\src\\main\\resources\\tmp\\hist_estoque.csv";

    @Autowired
    CustomRelatorioRepository repository;

    @Override
    public List<String> getRelatorioCsv() {

        repository.execRelatorio(RelatorioHistEstoque.class);

        String path = System.getProperty("user.dir") + PATH;
        List<String> rows = null;

        try {
            rows = Reader.read(path);
        } catch(Exception e) {

        }

        return rows;
    }

    public String getRelatorioType() {
        return "Hist_Estoque";
    }
}
