package com.system.backend.services.impl;

import com.system.backend.entities.*;
import com.system.backend.repositories.PedidoRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Pedido")
public class PedidoCRUDServiceImpl implements CRUDService {


    @Autowired
    private PedidoRepository repository;

    @Override
    public EntidadeBase create(EntidadeBase pedido) {
        Pedido aux = null;

        if(pedido.getClass().equals(PedidoFornecedor.class)) {
            aux = (PedidoFornecedor) pedido;
        } else {
            aux = (PedidoSaidaEstoque) pedido;
        }

        setPedido(aux.getItems(), aux);

        try {
            repository.save(aux);
        } catch(Exception e) {
            System.out.println("Erro na inserção do registro. Causa:");
            System.out.println(e.getCause());

            return null;
        }

        return repository.save(aux);
    }

    @Override
    public void update(EntidadeBase pedido) {
        Pedido aux = null;

        if(pedido.getClass().equals(PedidoFornecedor.class)) {
            aux = (PedidoFornecedor) pedido;
        } else {
            aux = (PedidoSaidaEstoque) pedido;
        }

        if(repository.existsById(aux.getId())) {
            repository.save(aux);
        }
    }

    @Override
    public List<EntidadeBase> filter(EntidadeBase pedido) {
        Pedido aux = null;

        if(pedido.getClass().equals(PedidoFornecedor.class)) {
            aux = (PedidoFornecedor) pedido;
        } else {
            aux = (PedidoSaidaEstoque) pedido;
        }

        return repository.findBy(aux);
    }

    @Override
    public List<EntidadeBase> filterAll(EntidadeBase pedido) {
        Pedido aux = null;

        if(pedido.getClass().equals(PedidoFornecedor.class)) {
            aux = (PedidoFornecedor) pedido;
        } else {
            aux = (PedidoSaidaEstoque) pedido;
        }

        return repository.findBy(aux);
    }

    @Override
    public void delete(EntidadeBase pedido) {
        Pedido aux = null;

        if(pedido.getClass().equals(PedidoFornecedor.class)) {
            aux = (PedidoFornecedor) pedido;
        } else {
            aux = (PedidoSaidaEstoque) pedido;
        }

        repository.delete(aux);
    }

    public void setPedido(List<ItemPedido> itens, Pedido pedido) {
        for(ItemPedido item : itens) {
            item.setPedido(pedido);
        }
    }

    @Override
    public List<EntidadeBase> filterAll() {
        return null;
    }
}
