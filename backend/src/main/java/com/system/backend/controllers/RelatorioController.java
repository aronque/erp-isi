package com.system.backend.controllers;

import com.system.backend.entities.*;
import com.system.backend.services.ControleAcessoService;
import com.system.backend.services.EmailService;
import com.system.backend.services.GetInjectedRelatorioService;
import com.system.backend.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Classe controladora de requests relacionados aos relatórios
 */
@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private static final String FUNC_CONST = "RELATORIO";

    RelatorioService service;

    @Autowired
    EmailService emailService;

    @Autowired
    ControleAcessoService accControlService;


    @PostMapping("/produtoFornecedor")
    public ResponseEntity getRelatorioProdForn(@RequestBody RelatorioProdForn relatorioParam) throws IOException {
        try {
            if(!accControlService.temPersmissao(relatorioParam.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
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
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }


    @PostMapping("/produtoEstoque")
    public ResponseEntity getRelatorioProdEstoque(@RequestBody RelatorioProdEstoque relatorioParam) throws IOException {
        try {
            if(!accControlService.temPersmissao(relatorioParam.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
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
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }


    @PostMapping("/pedidosPendente")
    public ResponseEntity getRelatorioPedidoPendente(@RequestBody RelatorioPedidoPendente relatorioParam) throws IOException {
        try {
            if(!accControlService.temPersmissao(relatorioParam.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            service = GetInjectedRelatorioService.getServiceInstance("Pedido_Pendente");
            List<String> linhas = service.getRelatorioCsv();
            Relatorio relatorio = new RelatorioPedidoPendente();
            relatorio.setNomeRelatorio("Relação Pedidos Pendentes");
            relatorio.setEmail(relatorioParam.getEmail());
            relatorio.print(linhas);

            emailService.sendEmail(relatorio);

            new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();
            new File(System.getProperty("user.dir") + relatorio.getPathCsv()).delete();

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }


    @PostMapping("/vendasProduto")
    public ResponseEntity getRelatorioVendasProduto(@RequestBody RelatorioVendasProduto relatorioParam) throws IOException {
        try {
            if(!accControlService.temPersmissao(relatorioParam.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            service = GetInjectedRelatorioService.getServiceInstance("Vendas_Produto");
            List<String> linhas = service.getRelatorioCsv();
            Relatorio relatorio = new RelatorioVendasProduto();
            relatorio.setNomeRelatorio("Relação Vendas-Produto");
            relatorio.setEmail(relatorioParam.getEmail());
            relatorio.print(linhas);

            emailService.sendEmail(relatorio);

            new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();
            new File(System.getProperty("user.dir") + relatorio.getPathCsv()).delete();

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }


    @PostMapping("/histEstoque")
    public ResponseEntity getRelatorioHistEstoque(@RequestBody RelatorioHistEstoque relatorioParam) throws IOException {
        try {
            if(!accControlService.temPersmissao(relatorioParam.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            service = GetInjectedRelatorioService.getServiceInstance("Hist_Estoque");
            List<String> linhas = service.getRelatorioCsv();
            Relatorio relatorio = new RelatorioHistEstoque();
            relatorio.setNomeRelatorio("Relação Histórico Movimentações-Estoque");
            relatorio.setEmail(relatorioParam.getEmail());
            relatorio.print(linhas);

            emailService.sendEmail(relatorio);

            new File(System.getProperty("user.dir") + relatorio.getXslPath()).delete();
            new File(System.getProperty("user.dir") + relatorio.getPathCsv()).delete();

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

}
