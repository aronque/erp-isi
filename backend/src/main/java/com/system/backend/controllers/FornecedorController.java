package com.system.backend.controllers;

import com.system.backend.entities.Fornecedor;
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
 * Classe controladora de requests relacionados a entidade fornecedores
 */
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private static final String FUNC_CONST = "CRUD";

    @Autowired
    @Qualifier("Fornecedor")
    CRUDService crudService;

    @Autowired
    @Qualifier("Endereco")
    CRUDService enderecoService;

    @Autowired
    ControleAcessoService accControlService;

    @PostMapping("/insert")
    public ResponseEntity createFornecedor(@RequestBody Fornecedor fornecedor) {
        try {
            if(!accControlService.temPersmissao(fornecedor.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }

            crudService.create(fornecedor);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Fornecedor>> findAll() {
        return ResponseEntity.ok(crudService.filterAll().stream().map(filtered -> (Fornecedor) filtered).collect(Collectors.toList()));
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<?>> findByCriteria(@RequestBody Fornecedor fornecedor) {
        try {
            return ResponseEntity.ok(crudService.filter(fornecedor).stream().map(filtered -> (Fornecedor) filtered).collect(Collectors.toList()));
        } catch(Exception e) {
            return ResponseEntity.ok(List.of("500"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateFornecedor(@RequestBody Fornecedor fornecedor) {
        try {
            if(!accControlService.temPersmissao(fornecedor.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            Fornecedor fornecedorAux = new Fornecedor();
            fornecedorAux.setId(fornecedor.getId());
            fornecedorAux = (Fornecedor) ((ArrayList<?>) crudService.filter(fornecedorAux)).get(0);

            setUpdatedFields(fornecedor, fornecedorAux);
            crudService.update(fornecedorAux);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Fornecedor fornecedor) {
        try {
            if(!accControlService.temPersmissao(fornecedor.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            crudService.delete(fornecedor);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    private void setUpdatedFields(Fornecedor fornecedorParam, Fornecedor fornecedorAux) {
        fornecedorAux.setNome(fornecedorParam.getNome() != null ? fornecedorParam.getNome() : fornecedorAux.getNome());
        fornecedorAux.setCnpj(fornecedorParam.getCnpj() != null ? fornecedorParam.getCnpj() : fornecedorAux.getCnpj());
        fornecedorAux.setContato(fornecedorParam.getContato() != null ? fornecedorParam.getContato() : fornecedorAux.getContato());
        fornecedorAux.setEndereco(fornecedorParam.getEndereco() != null ? fornecedorParam.getEndereco() : fornecedorAux.getEndereco());
    }
}
