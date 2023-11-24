package com.system.backend.controllers;

import com.system.backend.entities.Relatorio;
import com.system.backend.entities.RelatorioProdEstoque;
import com.system.backend.entities.RelatorioProdForn;
import com.system.backend.services.EmailService;
import com.system.backend.services.GetInjectedRelatorioService;
import com.system.backend.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    RelatorioService service;

    @Autowired
    EmailService emailService;


    @PostMapping("/produtoFornecedor")
    public ResponseEntity getRelatorioProdForn(@RequestBody Relatorio relatorioParam) throws IOException {
        service = GetInjectedRelatorioService.getServiceInstance("Prod_Forn");
        List<String> linhas = service.getRelatorioCsv();
        Relatorio relatorio = new RelatorioProdForn();
        relatorio.setNomeRelatorio("Relação Produto-Fornecedor");
        relatorio.setEmail(relatorioParam.getEmail());
        relatorio.print(linhas);

        emailService.sendEmail(relatorio);

        new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();
        new File(System.getProperty("user.dir") + relatorio.getPathCsv()).delete();

        return ResponseEntity.ok().build();
    }


    @PostMapping("/produtoEstoque")
    public ResponseEntity getRelatorioProdEstoque(@RequestBody Relatorio relatorioParam) throws IOException {
        service = GetInjectedRelatorioService.getServiceInstance("Prod_Estoque");
        List<String> linhas = service.getRelatorioCsv();
        Relatorio relatorio = new RelatorioProdEstoque();
        relatorio.setNomeRelatorio("Relação Produto-Estoque");
        relatorio.setEmail(relatorioParam.getEmail());
        relatorio.print(linhas);

        emailService.sendEmail(relatorio);

        new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();
        new File(System.getProperty("user.dir") + relatorio.getPathCsv()).delete();

        return ResponseEntity.ok().build();
    }


    @PostMapping("/pedidosPendente")
    public ResponseEntity getRelatorioPedidoPendente(@RequestBody Relatorio relatorioParam) throws IOException {
        service = GetInjectedRelatorioService.getServiceInstance("Pedido_Pendente");
        List<String> linhas = service.getRelatorioCsv();
        Relatorio relatorio = new RelatorioProdEstoque();
        relatorio.setNomeRelatorio("Relação Pedidos Pendentes");
        relatorio.setEmail(relatorioParam.getEmail());
        relatorio.print(linhas);

        emailService.sendEmail(relatorio);

        new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();
        new File(System.getProperty("user.dir") + relatorio.getPathCsv()).delete();

        return ResponseEntity.ok().build();
    }


    @PostMapping("/vendasProduto")
    public ResponseEntity getRelatorioVendasProduto(@RequestBody Relatorio relatorioParam) throws IOException {
        service = GetInjectedRelatorioService.getServiceInstance("Vendas_Produto");
        List<String> linhas = service.getRelatorioCsv();
        Relatorio relatorio = new RelatorioProdEstoque();
        relatorio.setNomeRelatorio("Relação Vendas-Produto");
        relatorio.setEmail(relatorioParam.getEmail());
        relatorio.print(linhas);

        emailService.sendEmail(relatorio);

        new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();
        new File(System.getProperty("user.dir") + relatorio.getPathCsv()).delete();

        return ResponseEntity.ok().build();
    }


    @PostMapping("/histEstoque")
    public ResponseEntity getRelatorioHistEstoque(@RequestBody Relatorio relatorioParam) throws IOException {
        service = GetInjectedRelatorioService.getServiceInstance("Hist_Estoque");
        List<String> linhas = service.getRelatorioCsv();
        Relatorio relatorio = new RelatorioProdEstoque();
        relatorio.setNomeRelatorio("Relação Histórico Movimentações-Estoque");
        relatorio.setEmail(relatorioParam.getEmail());
        relatorio.print(linhas);

        emailService.sendEmail(relatorio);

        new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();
        new File(System.getProperty("user.dir") + relatorio.getPathCsv()).delete();

        return ResponseEntity.ok().build();
    }

}
