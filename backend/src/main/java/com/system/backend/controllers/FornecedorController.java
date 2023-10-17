package com.system.backend.controllers;

import com.system.backend.entities.Endereco;
import com.system.backend.entities.Fornecedor;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    @Qualifier("Fornecedor")
    CRUDService crudService;

    @Autowired
    @Qualifier("Endereco")
    CRUDService enderecoService;

    @PostMapping("/insert")
    public ResponseEntity createFornecedor(@RequestBody Fornecedor fornecedor) {
        Object[] objAux = new Object[5];
        objAux[0] = fornecedor.getId();
        objAux[1] = fornecedor.getNome();
        objAux[2] = fornecedor.getCnpj();
        objAux[3] = fornecedor.getContato();
        objAux[4] = fornecedor.getEndereco();

        crudService.create(objAux);

        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Fornecedor>> findAll() {
        return ResponseEntity.ok((List<Fornecedor>) crudService.filterAll());
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<Fornecedor>> findByCriteria(@RequestBody Fornecedor fornecedor) {
        Object[] objAux = new Object[5];
        objAux[0] = fornecedor.getId();
        objAux[1] = fornecedor.getNome();
        objAux[2] = fornecedor.getCnpj();
        objAux[3] = fornecedor.getContato();
        objAux[4] = fornecedor.getEndereco();
        return ResponseEntity.ok((List<Fornecedor>) crudService.filter(objAux));
    }

    @PutMapping("/update")
    public ResponseEntity updateFornecedor(@RequestBody Fornecedor fornecedor) {
        Object[] objAux = new Object[5];
        objAux[0] = fornecedor.getId();
        objAux[1] = fornecedor.getNome();
        objAux[2] = fornecedor.getCnpj();
        objAux[3] = fornecedor.getContato();
        objAux[4] = fornecedor.getEndereco();
        crudService.update(objAux);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Fornecedor fornecedor) {
        Object[] objAux = new Object[5];
        objAux[0] = fornecedor.getId();
        objAux[1] = fornecedor.getNome();
        objAux[2] = fornecedor.getCnpj();
        objAux[3] = fornecedor.getContato();
        objAux[4] = fornecedor.getEndereco();
        crudService.delete(objAux);
        return ResponseEntity.ok().build();
    }

}
