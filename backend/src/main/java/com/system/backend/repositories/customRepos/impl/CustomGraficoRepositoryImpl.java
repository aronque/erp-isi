package com.system.backend.repositories.customRepos.impl;

import com.system.backend.entities.Grafico;
import com.system.backend.repositories.customRepos.CustomGraficoRepository;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ResourceBundle;

@Component
public class CustomGraficoRepositoryImpl implements CustomGraficoRepository {



    @Override
    public Grafico getData(Grafico grafico) {

        //TODO tratar filtro por data

        ResourceBundle bundle = ResourceBundle.getBundle("application-dev");
        PreparedStatement ps;
        ResultSet rs;

        try (Connection conn = DriverManager.getConnection(
                bundle.getString("spring.datasource.url"),
                bundle.getString("spring.datasource.username"),
                bundle.getString("spring.datasource.password"))) {

            ps = conn.prepareStatement("SELECT * FROM " + grafico.getVwName());

            rs = ps.executeQuery();

            while(rs.next()) {
                String keyAux = rs.getString(1);
                Integer valueAux = rs.getInt(2);
                grafico.addItem(keyAux, valueAux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return grafico;
    }
}
