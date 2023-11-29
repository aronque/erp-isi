package com.system.backend.services.impl;

import com.system.backend.entities.RelatorioProdEstoque;
import com.system.backend.repositories.customRepos.CustomRelatorioRepository;
import com.system.backend.services.RelatorioService;
import com.system.backend.util.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Prod_Estoque")
public class RelatorioProdEstoqueServiceImpl implements RelatorioService {

    private static final String PATH = "\\src\\main\\resources\\tmp\\produtos_estoque.csv";

    @Autowired
    CustomRelatorioRepository repository;

    @Override
    public List<String> getRelatorioCsv() {

        repository.execRelatorio(RelatorioProdEstoque.class);

        String path = System.getProperty("user.dir") + PATH;
        List<String> rows = null;

        try {
            rows = Reader.read(path);
        } catch(Exception e) {

        }

        return rows;
    }

    public String getRelatorioType() {
        return "Prod_Estoque";
    }
}
