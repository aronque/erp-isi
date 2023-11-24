package com.system.backend.controllers;

import com.system.backend.entities.Relatorio;
import com.system.backend.entities.RelatorioProdForn;
import com.system.backend.services.EmailService;
import com.system.backend.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    @Qualifier("Prod_Forn")
    RelatorioService service;

    @Autowired
    EmailService emailService;

    @PostMapping("/produtoFornecedor")
    public ResponseEntity getRelatorio(@RequestBody Relatorio relatorioParam) throws IOException {

        List<String> linhas = service.getRelatorioCsv();
        Relatorio relatorio = new RelatorioProdForn();
        relatorio.setEmail(relatorioParam.getEmail());
        relatorio.print(linhas);

        emailService.sendEmail(relatorio);

        new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();

        return ResponseEntity.ok().build();
    }

}
