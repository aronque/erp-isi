package com.system.backend.controllers;

import com.system.backend.entities.Fornecedor;
import com.system.backend.entities.Produto;
import com.system.backend.services.CRUDService;
import com.system.backend.services.ControleAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

            crudService.create(produto);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(crudService.filterAll().stream().map(filtered -> (Produto) filtered).collect(Collectors.toList()));
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<?>> findByCriteria(@RequestBody Produto produto) {
        try {
            return ResponseEntity.ok(crudService.filter(produto).stream().map(filtered -> (Produto) filtered).collect(Collectors.toList()));
        } catch(Exception e) {
            return ResponseEntity.ok(List.of("500"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateProduto(@RequestBody Produto produto) {
        try {
            if(!accControlService.temPersmissao(produto.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            Produto produtoAux = new Produto();
            produtoAux.setId(produto.getId());
            produtoAux = (Produto) ((ArrayList<?>) crudService.filter(produto)).get(0);

            setUpdatedFields(produto, produtoAux);
            crudService.update(produtoAux);

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
            crudService.delete(produto);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    private void setUpdatedFields(Produto produtoParam, Produto produtoAux) {
        produtoAux.setNome(produtoParam.getNome() != null ? produtoParam.getNome() : produtoAux.getNome());
        produtoAux.setFornecedor(produtoParam.getFornecedor() != null ? produtoParam.getFornecedor() : produtoAux.getFornecedor());
        produtoAux.setQuantidade(produtoParam.getQuantidade() != null ? produtoParam.getQuantidade() : produtoAux.getQuantidade());
        produtoAux.setPreco(produtoParam.getPreco() != null ? produtoParam.getPreco() : produtoAux.getPreco());
    }
}
