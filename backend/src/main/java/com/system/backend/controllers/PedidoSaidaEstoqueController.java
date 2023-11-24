package com.system.backend.controllers;

import com.system.backend.entities.ItemPedido;
import com.system.backend.entities.Pedido;
import com.system.backend.entities.PedidoSaidaEstoque;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidosEstoque")
public class PedidoSaidaEstoqueController {

    private static final String FUNC_CONST = "CRUDPEDIDO";

    @Autowired
    @Qualifier("Pedido")
    CRUDService crudService;

    @PostMapping("/insert")
    public ResponseEntity createPedido(@RequestBody PedidoSaidaEstoque pedido) {
        Object[] objAux = new Object[7];
        objAux[1] = pedido.getItems();
        objAux[2] = Date.from(Instant.now());
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        objAux[6] = PedidoSaidaEstoque.class;
        crudService.create(objAux);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Pedido>> findAll() {
        Object[] objAux = new Object[1];
        objAux[0] = PedidoSaidaEstoque.class;

        return ResponseEntity.ok((List<Pedido>) crudService.filterAll(objAux));
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<Pedido>> findByCriteria(@RequestBody PedidoSaidaEstoque pedido) {
        Object[] objAux = new Object[7];
        objAux[0] = pedido.getId();
        objAux[1] = pedido.getItems();
        objAux[2] = pedido.getData();
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        objAux[6] = PedidoSaidaEstoque.class;
        return ResponseEntity.ok((List<Pedido>) crudService.filter(objAux));
    }

    @PutMapping("/update")
    public ResponseEntity updatePedido(@RequestBody PedidoSaidaEstoque pedido) {
        PedidoSaidaEstoque pedidoAux;
        Object[] objAux = new Object[7];
        objAux[0] = pedido.getId();
        objAux[6] = PedidoSaidaEstoque.class;

        pedidoAux = (PedidoSaidaEstoque) ((ArrayList<?>) crudService.filter(objAux)).get(0);

        setUpdatedFields(objAux, pedido, pedidoAux);
        crudService.update(objAux);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody PedidoSaidaEstoque pedido) {
        Object[] objAux = new Object[7];
        objAux[0] = pedido.getId();
        objAux[1] = pedido.getItems();
        objAux[2] = pedido.getData();
        objAux[3] = pedido.getStatus();
        objAux[4] = pedido.getUsuario();
        objAux[6] = PedidoSaidaEstoque.class;
        crudService.delete(objAux);
        return ResponseEntity.ok().build();
    }

    private void setUpdatedFields(Object[] objAux, PedidoSaidaEstoque pedidoParam, PedidoSaidaEstoque pedidoAux) {

        if(pedidoParam.getItems() != null && !pedidoParam.getItems().isEmpty()) {

            setPedidoInItems(pedidoParam.getItems(), pedidoParam);
            objAux[1] = pedidoParam.getItems();
        } else {
            setPedidoInItems(pedidoAux.getItems(), pedidoAux);
            objAux[1] = pedidoAux.getItems();
        }

        objAux[2] = pedidoParam.getData() != null ? pedidoParam.getData() : pedidoAux.getData();
        objAux[3] = pedidoParam.getStatus() != null ? pedidoParam.getStatus() : pedidoAux.getStatus();
        objAux[4] = pedidoAux.getUsuario();
        objAux[6] = pedidoAux.getInstancia();
    }

    private void setPedidoInItems(List<ItemPedido> items, Pedido pedido) {
        for(ItemPedido item : items) {
            item.setPedido(pedido);
        }
    }
}
