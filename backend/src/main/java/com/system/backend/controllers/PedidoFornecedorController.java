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

            pedido.setData(Date.from(Instant.now()));
            crudService.create(pedido);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Pedido>> findAll() {
        PedidoFornecedor objAux = new PedidoFornecedor();

        return ResponseEntity.ok(crudService.filterAll(objAux).stream().map(filtered -> (PedidoFornecedor) filtered).collect(Collectors.toList()));
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
            PedidoFornecedor pedidoAux = new PedidoFornecedor();
            pedidoAux.setId(pedido.getId());

            pedidoAux = (PedidoFornecedor) ((ArrayList) crudService.filter(pedido)).get(0);

            setUpdatedFields(pedido, pedidoAux);
            crudService.update(pedidoAux);

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
            crudService.delete(pedido);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    private void setUpdatedFields (PedidoFornecedor pedidoParam, PedidoFornecedor pedidoAux) {
        pedidoAux.setItens(pedidoParam.getItems() != null ? pedidoParam.getItems() : pedidoAux.getItems());
        pedidoAux.setData(pedidoParam.getData() != null ? pedidoParam.getData() : pedidoAux.getData());
        pedidoAux.setStatus(pedidoParam.getStatus() != null ? pedidoParam.getStatus() : pedidoAux.getStatus());
        pedidoAux.setUsuario(pedidoAux.getUsuario());
        pedidoAux.setFornecedor(pedidoParam.getFornecedor() != null ? pedidoParam.getFornecedor() : pedidoAux.getFornecedor());
    }
}
