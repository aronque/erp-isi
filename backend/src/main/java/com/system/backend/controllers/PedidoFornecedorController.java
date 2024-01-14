package com.system.backend.controllers;

import com.system.backend.entities.Pedido;
import com.system.backend.entities.PedidoFornecedor;
import com.system.backend.services.CRUDService;
import com.system.backend.services.ControleAcessoService;
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
 * Classe controladora de requests relacionados a entidade pedidos de entrada de produtos (fornecedor)
 */
@RestController
@RequestMapping("/pedidosFornecedor")
public class PedidoFornecedorController {

    private static final String FUNC_CONST = "CRUD";

    @Autowired
    @Qualifier("Pedido")
    CRUDService crudService;

    @Autowired
    ControleAcessoService accControlService;

    @PostMapping("/insert")
    public ResponseEntity createPedido(@RequestBody PedidoFornecedor pedido) {
        try {
            if(!accControlService.temPersmissao(pedido.getUsuario().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            Object[] objAux = new Object[7];
            objAux[1] = pedido.getItems();
            objAux[2] = Date.from(Instant.now());
            objAux[3] = pedido.getStatus();
            objAux[4] = pedido.getUsuario();
            objAux[5] = pedido.getFornecedor();
            objAux[6] = PedidoFornecedor.class;
            crudService.create(objAux);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Pedido>> findAll() {
        Object[] objAux = new Object[1];
        objAux[0] = PedidoFornecedor.class;

        return ResponseEntity.ok((List<Pedido>) crudService.filterAll(objAux));
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<?>> findByCriteria(@RequestBody PedidoFornecedor pedido) {
        try {
            return ResponseEntity.ok(crudService.filter(pedido).stream().map(filtered -> (PedidoFornecedor) filtered).collect(Collectors.toList()));
        } catch(Exception e) {
            return ResponseEntity.ok(List.of("500"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity updatePedido(@RequestBody PedidoFornecedor pedido) {
        try {
            if(!accControlService.temPersmissao(pedido.getUsuario().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            PedidoFornecedor pedidoAux;
            Object[] objAux = new Object[7];
            objAux[0] = pedido.getId();

            pedidoAux = (PedidoFornecedor) ((ArrayList) crudService.filter(objAux)).get(0);

            setUpdatedFields(objAux, pedido, pedidoAux);
            crudService.update(objAux);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody PedidoFornecedor pedido) {
        try {
            if(!accControlService.temPersmissao(pedido.getUsuario().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
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
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    private void setUpdatedFields(Object[] objAux, PedidoFornecedor pedidoParam, PedidoFornecedor pedidoAux) {
        objAux[1] = pedidoParam.getItems() != null ? pedidoParam.getItems() : pedidoAux.getItems();
        objAux[2] = pedidoParam.getData() != null ? pedidoParam.getData() : pedidoAux.getData();
        objAux[3] = pedidoParam.getStatus() != null ? pedidoParam.getStatus() : pedidoAux.getStatus();
        objAux[4] = pedidoAux.getUsuario();
        objAux[5] = pedidoParam.getFornecedor() != null ? pedidoParam.getFornecedor() : pedidoAux.getFornecedor();
        objAux[6] = pedidoAux.getInstancia();
    }
}
