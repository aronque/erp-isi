package com.system.backend.services.impl;

import com.system.backend.repositories.customRepos.CustomRelatorioRepository;
import com.system.backend.services.RelatorioService;
import com.system.backend.util.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("Prod_Forn")
public class RelatorioProdFornServiceImpl implements RelatorioService {

    private static final String PATH = "\\src\\main\\resources\\temp\\fornecedor_produtos.csv";

    @Autowired
    CSVReader reader;

    @Autowired
    CustomRelatorioRepository repository;

    @Override
    public List<String> getRelatorioCsv() {

        repository.execProdutoFornecedorRelatorio();

        String path = System.getProperty("user.dir") + PATH;
        List<String> rows = null;

        try {
            rows = CSVReader.read(path);
        } catch(Exception e) {

        }

        return rows;
    }
}
