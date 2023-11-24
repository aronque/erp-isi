package com.system.backend.repositories.customRepos.impl;

import com.system.backend.entities.RelatorioProdForn;
import com.system.backend.repositories.customRepos.CustomRelatorioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Component
public class CustomRelatorioRepositoryImpl implements CustomRelatorioRepository {

    private RelatorioProdForn relatorio = new RelatorioProdForn();

    @PersistenceContext
    private EntityManager em;

    @Override
    public void execProdutoFornecedorRelatorio() {
        runRelatorio();
    }

    private long runRelatorio() {

        ResourceBundle bundle = ResourceBundle.getBundle("application-dev");
        Long rowsInserted = 0L;

        try (Connection conn = DriverManager.getConnection(
                bundle.getString("spring.datasource.url"),
                bundle.getString("spring.datasource.username"),
                bundle.getString("spring.datasource.password"))) {
            File csv = new File(System.getProperty("user.dir") + relatorio.getPathCsv());
            csv.createNewFile();

            rowsInserted = new CopyManager((BaseConnection) conn)
                    .copyOut(
                            "COPY (SELECT * FROM VW_FORNECEDOR_PRODUTO) TO STDOUT (FORMAT csv, DELIMITER ';') ",
                            new FileOutputStream(System.getProperty("user.dir") + relatorio.getPathCsv())
                    );
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return rowsInserted;
    }
}
