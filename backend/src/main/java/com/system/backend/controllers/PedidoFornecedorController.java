package com.system.backend.controllers;

import com.system.backend.entities.Pedido;
import com.system.backend.entities.PedidoFornecedor;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/pedidosFornecedor")
public class PedidoFornecedorController {

    @Autowired
    @Qualifier("Pedido")
    CRUDService crudService;

    @PostMapping("/insert")
    public ResponseEntity createPedido(@RequestBody PedidoFornecedor pedido) {
        Object[] objAux = new Object[7];
        objAux[1] = pedido.getItems();
        objAux[2] = Date.from(Instant.now());
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        objAux[5] = pedido.getFornecedor();
        objAux[6] = PedidoFornecedor.class;
        crudService.create(objAux);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Pedido>> findAll() {
        return ResponseEntity.ok((List<Pedido>) crudService.filterAll());
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<Pedido>> findByCriteria(@RequestBody PedidoFornecedor pedido) {
        Object[] objAux = new Object[7];
        objAux[0] = pedido.getId();
        objAux[1] = pedido.getItems();
        objAux[2] = pedido.getData();
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        objAux[5] = pedido.getFornecedor();
        objAux[6] = PedidoFornecedor.class;
        return ResponseEntity.ok((List<Pedido>) crudService.filter(objAux));
    }

    @PutMapping("/update")
    public ResponseEntity updatePedido(@RequestBody PedidoFornecedor pedido) {
        Object[] objAux = new Object[7];
        objAux[0] = pedido.getId();
        objAux[1] = pedido.getItems();
        objAux[2] = pedido.getData();
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        objAux[5] = pedido.getFornecedor();
        objAux[6] = PedidoFornecedor.class;
        crudService.update(objAux);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody PedidoFornecedor pedido) {
        Object[] objAux = new Object[7];
        objAux[0] = pedido.getId();
        objAux[1] = pedido.getItems();
        objAux[2] = pedido.getData();
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        objAux[5] = pedido.getFornecedor();
        objAux[6] = PedidoFornecedor.class;
        crudService.delete(objAux);
        return ResponseEntity.ok().build();
    }

}
