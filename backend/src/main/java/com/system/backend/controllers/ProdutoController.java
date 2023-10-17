package com.system.backend.controllers;

import com.system.backend.entities.Produto;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    @Qualifier("Produto")
    CRUDService crudService;

    @PostMapping("/insert")
    public ResponseEntity createProduto(@RequestBody Produto produto) {
        Object[] objAux = new Object[5];
        objAux[1] = produto.getNome();
        objAux[2] = produto.getFornecedor();
        objAux[3] = produto.getQuantidade();
        objAux[4] = produto.getPreco();
        crudService.create(objAux);
        return ResponseEntity.ok().build();
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
        Object[] objAux = new Object[5];
        objAux[0] = produto.getId();
        objAux[1] = produto.getNome();
        objAux[2] = produto.getFornecedor();
        objAux[3] = produto.getQuantidade();
        objAux[4] = produto.getPreco();
        crudService.update(objAux);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Produto produto) {
        Object[] objAux = new Object[5];
        objAux[0] = produto.getId();
        objAux[1] = produto.getNome();
        objAux[2] = produto.getFornecedor();
        objAux[3] = produto.getQuantidade();
        objAux[4] = produto.getPreco();
        crudService.delete(objAux);
        return ResponseEntity.ok().build();
    }

}
