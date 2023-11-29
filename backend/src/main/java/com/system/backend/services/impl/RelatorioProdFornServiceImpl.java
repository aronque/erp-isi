package com.system.backend.services.impl;

import com.system.backend.entities.RelatorioProdForn;
import com.system.backend.repositories.customRepos.CustomRelatorioRepository;
import com.system.backend.services.RelatorioService;
import com.system.backend.util.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Prod_Forn")
public class RelatorioProdFornServiceImpl implements RelatorioService {

    private static final String PATH = "\\src\\main\\resources\\tmp\\fornecedor_produtos.csv";

    @Autowired
    CustomRelatorioRepository repository;

    @Override
    public List<String> getRelatorioCsv() {

        repository.execRelatorio(RelatorioProdForn.class);

        String path = System.getProperty("user.dir") + PATH;
        List<String> rows = null;

        try {
            rows = Reader.read(path);
        } catch(Exception e) {

        }

        return rows;
    }

    public String getRelatorioType() {
        return "Prod_Forn";
    }
}
