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
            Object[] objAux = new Object[5];
            objAux[0] = fornecedor.getId();
            objAux[1] = fornecedor.getNome();
            objAux[2] = fornecedor.getCnpj();
            objAux[3] = fornecedor.getContato();
            objAux[4] = fornecedor.getEndereco();

            crudService.create(objAux);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Fornecedor>> findAll() {
        List<Fornecedor> list = (List<Fornecedor>) crudService.filterAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<?>> findByCriteria(@RequestBody Fornecedor fornecedor) {
        try {
            Object[] objAux = new Object[5];
            objAux[0] = fornecedor.getId();
            objAux[1] = fornecedor.getNome();
            objAux[2] = fornecedor.getCnpj();
            objAux[3] = fornecedor.getContato();
            objAux[4] = fornecedor.getEndereco();
            return ResponseEntity.ok((List<Fornecedor>) crudService.filter(objAux));
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
            Fornecedor fornecedorAux;
            Object[] objAux = new Object[5];
            objAux[0] = fornecedor.getId();

            fornecedorAux = (Fornecedor) ((ArrayList<?>) crudService.filter(objAux)).get(0);

            setUpdatedFields(objAux, fornecedor, fornecedorAux);
            crudService.update(objAux);

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
            Object[] objAux = new Object[5];
            objAux[0] = fornecedor.getId();
            objAux[1] = fornecedor.getNome();
            objAux[2] = fornecedor.getCnpj();
            objAux[3] = fornecedor.getContato();
            objAux[4] = fornecedor.getEndereco();
            crudService.delete(objAux);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    private void setUpdatedFields(Object[] objAux, Fornecedor fornecedorParam, Fornecedor fornecedorAux) {
        objAux[1] = fornecedorParam.getNome() != null ? fornecedorParam.getNome() : fornecedorAux.getNome();
        objAux[2] = fornecedorParam.getCnpj() != null ? fornecedorParam.getCnpj() : fornecedorAux.getCnpj();
        objAux[3] = fornecedorParam.getContato() != null ? fornecedorParam.getContato() : fornecedorAux.getContato();
        objAux[4] = fornecedorParam.getEndereco() != null ? fornecedorParam.getEndereco() : fornecedorAux.getEndereco();
    }
}
