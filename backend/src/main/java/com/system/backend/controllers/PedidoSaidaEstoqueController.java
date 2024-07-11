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
import java.util.stream.Collectors;

/**
 * Classe controladora de requests relacionados a entidade pedidos de retirada de produtos (estoque)
 */
@RestController
@RequestMapping("/pedidosEstoque")
public class PedidoSaidaEstoqueController {

    private static final String FUNC_CONST = "CRUDPEDIDO";

    @Autowired
    @Qualifier("Pedido")
    CRUDService crudService;

    @PostMapping("/insert")
    public ResponseEntity createPedido(@RequestBody PedidoSaidaEstoque pedido) {
        try {

            pedido.setData(Date.from(Instant.now()));
            crudService.create(pedido);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Pedido>> findAll() {
        PedidoSaidaEstoque objAux = new PedidoSaidaEstoque();
        return ResponseEntity.ok(crudService.filterAll(objAux).stream().map(filtered -> (PedidoSaidaEstoque) filtered).collect(Collectors.toList()));
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<Pedido>> findByCriteria(@RequestBody PedidoSaidaEstoque pedido) {

        return ResponseEntity.ok(crudService.filterAll(pedido).stream().map(filtered -> (PedidoSaidaEstoque) filtered).collect(Collectors.toList()));
    }

    @PutMapping("/update")
    public ResponseEntity updatePedido(@RequestBody PedidoSaidaEstoque pedido) {
        try {
            PedidoSaidaEstoque pedidoAux = new PedidoSaidaEstoque();
            pedidoAux.setId(pedido.getId());

            pedidoAux = (PedidoSaidaEstoque) ((ArrayList<?>) crudService.filter(pedidoAux)).get(0);

            setUpdatedFields(pedido, pedidoAux);
            crudService.update(pedidoAux);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody PedidoSaidaEstoque pedido) {
        try {
            crudService.delete(pedido);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    private void setUpdatedFields(PedidoSaidaEstoque pedidoParam, PedidoSaidaEstoque pedidoAux) {

        if(pedidoParam.getItems() != null && !pedidoParam.getItems().isEmpty()) {

            setPedidoInItems(pedidoParam.getItems(), pedidoParam);
            pedidoAux.setItens(pedidoParam.getItems());
        } else {
            setPedidoInItems(pedidoAux.getItems(), pedidoAux);
            pedidoAux.setItens(pedidoAux.getItems());
        }

        pedidoAux.setData(pedidoParam.getData() != null ? pedidoParam.getData() : pedidoAux.getData());
        pedidoAux.setStatus(pedidoParam.getStatus() != null ? pedidoParam.getStatus() : pedidoAux.getStatus());
        pedidoAux.setUsuario(pedidoAux.getUsuario());
    }

    private void setPedidoInItems(List<ItemPedido> items, Pedido pedido) {
        for(ItemPedido item : items) {
            item.setPedido(pedido);
        }
    }
}
