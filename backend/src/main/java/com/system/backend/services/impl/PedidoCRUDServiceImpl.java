package com.system.backend.services.impl;

import com.system.backend.entities.*;
import com.system.backend.repositories.EnderecoRepository;
import com.system.backend.repositories.PedidoRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component("Pedido")
public class PedidoCRUDServiceImpl implements CRUDService {


    @Autowired
    private PedidoRepository repository;

    @Override
    public Object create(Object[] obj) {
        Pedido aux = obj.length == 7 ? new PedidoFornecedor() : new PedidoSaidaEstoque();
        aux.addManyItems(obj[1] != null ? (Set<ItemPedido>) obj[1] : null);
        aux.setData(obj[2] != null ? (Date) obj[2] : null);
        aux.setStatus(obj[3] != null ? (Pedido.Status) obj[3] : null);
        aux.setUsuario(obj[4] != null ? (Usuario) obj[4] : null);

        System.out.println(aux.getClass());
        System.out.println(PedidoFornecedor.class);

        if(aux.getClass().equals(PedidoFornecedor.class)) {
            aux.setFornecedor((Fornecedor) obj[5]);
        }

        return repository.save(aux);
    }

    @Override
    public void update(Object[] obj) {
        Pedido aux = new PedidoSaidaEstoque();
        aux.addManyItems(obj[1] != null ? (Set<ItemPedido>) obj[1] : null);
        aux.setData(obj[2] != null ? (Date) obj[2] : null);
        aux.setStatus(obj[3] != null ? (Pedido.Status) obj[3] : null);
        aux.setUsuario(obj[4] != null ? (Usuario) obj[4] : null);

        if(aux.getClass().isInstance(PedidoFornecedor.class)) {
            aux.setFornecedor((Fornecedor) obj[5]);
        }

        repository.save(aux);
    }

    @Override
    public List<Pedido> filter(Object[] obj) {
        Pedido aux = new PedidoSaidaEstoque();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.addManyItems(obj[1] != null ? (Set<ItemPedido>) obj[1] : null);
        aux.setData(obj[2] != null ? (Date) obj[2] : null);
        aux.setStatus(obj[3] != null ? (Pedido.Status) obj[3] : null);
        aux.setUsuario(obj[4] != null ? (Usuario) obj[4] : null);

        if(aux.getClass().isInstance(PedidoFornecedor.class)) {
            aux.setFornecedor((Fornecedor) obj[5]);
        }

        return repository.findByCriteria(aux);
    }

    @Override
    public List<Pedido> filterAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Object[] obj) {
        Pedido aux = new PedidoSaidaEstoque();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.addManyItems(obj[1] != null ? (Set<ItemPedido>) obj[1] : null);
        aux.setData(obj[2] != null ? (Date) obj[2] : null);
        aux.setStatus(obj[3] != null ? (Pedido.Status) obj[3] : null);
        aux.setUsuario(obj[4] != null ? (Usuario) obj[4] : null);
        repository.delete(aux);
    }
}
