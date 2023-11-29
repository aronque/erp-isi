package com.system.backend.controllers;

import com.system.backend.entities.Produto;
import com.system.backend.services.CRUDService;
import com.system.backend.services.ControleAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controladora de requests relacionados a entidade produtos
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private static final String FUNC_CONST = "CRUD";

    @Autowired
    @Qualifier("Produto")
    CRUDService crudService;

    @Autowired
    ControleAcessoService accControlService;

    @PostMapping("/insert")
    public ResponseEntity createProduto(@RequestBody Produto produto) {
        try {
            if(!accControlService.temPersmissao(produto.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            Object[] objAux = new Object[5];
            objAux[1] = produto.getNome();
            objAux[2] = produto.getFornecedor();
            objAux[3] = produto.getQuantidade();
            objAux[4] = produto.getPreco();
            crudService.create(objAux);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok((List<Produto>) crudService.filterAll());
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<Produto>> findByCriteria(@RequestBody Produto produto) {
        Object[] objAux = new Object[5];
        objAux[0] = produto.getId();
        objAux[1] = produto.getNome();
        objAux[2] = produto.getFornecedor();
        objAux[3] = produto.getQuantidade();
        objAux[4] = produto.getPreco();
        return ResponseEntity.ok((List<Produto>) crudService.filter(objAux));
    }

    @PutMapping("/update")
    public ResponseEntity updateProduto(@RequestBody Produto produto) {
        try {
            if(!accControlService.temPersmissao(produto.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            Produto produtoAux;
            Object[] objAux = new Object[5];
            objAux[0] = produto.getId();

            produtoAux = (Produto) ((ArrayList<?>) crudService.filter(objAux)).get(0);

            setUpdatedFields(objAux, produto, produtoAux);
            crudService.update(objAux);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Produto produto) {
        try {
            if(!accControlService.temPersmissao(produto.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            Object[] objAux = new Object[5];
            objAux[0] = produto.getId();
            objAux[1] = produto.getNome();
            objAux[2] = produto.getFornecedor();
            objAux[3] = produto.getQuantidade();
            objAux[4] = produto.getPreco();
            crudService.delete(objAux);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    private void setUpdatedFields(Object[] objAux, Produto produtoParam, Produto produtoAux) {
        objAux[1] = produtoParam.getNome() != null ? produtoParam.getNome() : produtoAux.getNome();
        objAux[2] = produtoParam.getFornecedor() != null ? produtoParam.getFornecedor() : produtoAux.getFornecedor();
        objAux[3] = produtoParam.getQuantidade() != null ? produtoParam.getQuantidade() : produtoAux.getQuantidade();
        objAux[4] = produtoParam.getPreco() != null ? produtoParam.getPreco() : produtoAux.getPreco();
    }
}
