package com.system.backend.repositories.customRepos.impl;

import com.system.backend.entities.Relatorio;
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

    private Relatorio relatorio;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void execRelatorio(Class relatorioClass) {

        try {
            relatorio = (Relatorio) relatorioClass.getDeclaredConstructor().newInstance();

            runRelatorio();
        } catch(Exception e) {

        }

    }

    private void runRelatorio() {

        ResourceBundle bundle = ResourceBundle.getBundle("application-dev");

        try (Connection conn = DriverManager.getConnection(
                bundle.getString("spring.datasource.url"),
                bundle.getString("spring.datasource.username"),
                bundle.getString("spring.datasource.password"))) {
            File csv = new File(System.getProperty("user.dir") + relatorio.getPathCsv());
            csv.createNewFile();

            new CopyManager((BaseConnection) conn)
                    .copyOut(
                            "COPY (SELECT * FROM " + relatorio.getVwName() + " ) TO STDOUT (FORMAT csv, DELIMITER ';') ",
                            new FileOutputStream(System.getProperty("user.dir") + relatorio.getPathCsv())
                    );
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
