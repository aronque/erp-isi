package com.system.backend.controllers;

import com.system.backend.entities.Grafico;
import com.system.backend.services.GraficoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/graficos")
public class GraficoController {

    @Autowired
    private GraficoService service;


    @PostMapping("/prodPedido")
    public ResponseEntity getRelatorioProdForn(@RequestBody Grafico grafico) throws IOException {
        List<Map<String, Integer>> resultList = new ArrayList<>();

        Map<String, Integer> maptest = new HashMap<>();
        maptest.put("value1", 2);
        maptest.put("1124", 102);


//        Grafico response;
//
//        grafico.setVwName("VW_PRODUTOS_PEDIDOS");
//        response = service.getData(grafico);

        return ResponseEntity.ok(maptest);
    }

    @PostMapping("/movMes")
    public ResponseEntity getRelatorioMovMes(@RequestBody Grafico grafico) throws IOException {

        Grafico response;

        grafico.setVwName("VW_MOVIMENTACOES_MES");
        response = service.getData(grafico);

        return ResponseEntity.ok(response.getXy());
    }

    @PostMapping("/statusPedidos")
    public ResponseEntity getStatusPedidos(@RequestBody Grafico grafico) throws IOException {

        Grafico response;

        grafico.setVwName("VW_STATUS_PEDIDOS");
        response = service.getData(grafico);

        return ResponseEntity.ok(response.getXy());
    }
}
