package com.system.backend.controllers;

import com.system.backend.entities.Pedido;
import com.system.backend.entities.PedidoSaidaEstoque;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/pedidosEstoque")
public class PedidoSaidaEstoqueController {

    @Autowired
    @Qualifier("Pedido")
    CRUDService crudService;

    @PostMapping("/insert")
    public ResponseEntity createPedido(@RequestBody PedidoSaidaEstoque pedido) {
        Object[] objAux = new Object[5];
        objAux[1] = pedido.getItems();
        objAux[2] = Date.from(Instant.now());
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        crudService.create(objAux);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Pedido>> findAll() {
        return ResponseEntity.ok((List<Pedido>) crudService.filterAll());
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<Pedido>> findByCriteria(@RequestBody PedidoSaidaEstoque pedido) {
        Object[] objAux = new Object[5];
        objAux[0] = pedido.getId();
        objAux[1] = pedido.getItems();
        objAux[2] = pedido.getData();
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        return ResponseEntity.ok((List<Pedido>) crudService.filter(objAux));
    }

    @PutMapping("/update")
    public ResponseEntity updatePedido(@RequestBody PedidoSaidaEstoque pedido) {
        Object[] objAux = new Object[5];
        objAux[0] = pedido.getId();
        objAux[1] = pedido.getItems();
        objAux[2] = pedido.getData();
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        crudService.update(objAux);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody PedidoSaidaEstoque pedido) {
        Object[] objAux = new Object[5];
        objAux[0] = pedido.getId();
        objAux[1] = pedido.getItems();
        objAux[2] = pedido.getData();
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        crudService.delete(objAux);
        return ResponseEntity.ok().build();
    }

}
