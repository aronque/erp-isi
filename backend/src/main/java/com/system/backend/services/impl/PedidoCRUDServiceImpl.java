package com.system.backend.services.impl;

import com.system.backend.entities.*;
import com.system.backend.repositories.EnderecoRepository;
import com.system.backend.repositories.ItemPedidoRepository;
import com.system.backend.repositories.PedidoRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component("Pedido")
public class PedidoCRUDServiceImpl implements CRUDService {


    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public Object create(Object[] obj) {
        Pedido aux = null;

        if(obj[6].equals(PedidoFornecedor.class)) {
            aux = new PedidoFornecedor();
            aux.setFornecedor((Fornecedor) obj[5]);
        } else {
            aux = new PedidoSaidaEstoque();
        }

        aux.addManyItems(obj[1] != null ? (List<ItemPedido>) obj[1] : null);
        aux.setData(obj[2] != null ? (Date) obj[2] : null);
        aux.setStatus(obj[3] != null ? (Pedido.Status) obj[3] : null);
        aux.setUsuario(obj[4] != null ? (Usuario) obj[4] : null);

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
    public void update(Object[] obj) {
        Pedido aux = null;

        if(obj[6].equals(PedidoFornecedor.class)) {
            aux = new PedidoFornecedor();
            aux.setFornecedor((Fornecedor) obj[5]);
        } else {
            aux = new PedidoSaidaEstoque();
        }

        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.addManyItems(obj[1] != null ? (List<ItemPedido>) obj[1] : null);
        aux.setData(obj[2] != null ? (Date) obj[2] : null);
        aux.setStatus(obj[3] != null ? (Pedido.Status) obj[3] : null);
        aux.setUsuario(obj[4] != null ? (Usuario) obj[4] : null);

        if(repository.existsById(aux.getId())) {
            repository.save(aux);
        }
    }

    @Override
    public List<Pedido> filter(Object[] obj) {
        Pedido aux = null;

        if(obj[6].equals(PedidoFornecedor.class)) {
            aux = new PedidoFornecedor();
            aux.setFornecedor((Fornecedor) obj[5]);
        } else {
            aux = new PedidoSaidaEstoque();
        }

        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.addManyItems(obj[1] != null ? (List<ItemPedido>) obj[1] : null);
        aux.setData(obj[2] != null ? (Date) obj[2] : null);
        aux.setStatus(obj[3] != null ? (Pedido.Status) obj[3] : null);
        aux.setUsuario(obj[4] != null ? (Usuario) obj[4] : null);

        return repository.findBy(aux);
    }

    @Override
    public List<Pedido> filterAll(Object[] obj) {
        Pedido aux = null;

        if(obj[0].equals(PedidoFornecedor.class)) {
            aux = new PedidoFornecedor();
        } else {
            aux = new PedidoSaidaEstoque();
        }

        return repository.findBy(aux);
    }

    @Override
    public void delete(Object[] obj) {
        Pedido aux = new PedidoSaidaEstoque();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.addManyItems(obj[1] != null ? (List<ItemPedido>) obj[1] : null);
        aux.setData(obj[2] != null ? (Date) obj[2] : null);
        aux.setStatus(obj[3] != null ? (Pedido.Status) obj[3] : null);
        aux.setUsuario(obj[4] != null ? (Usuario) obj[4] : null);
        repository.delete(aux);
    }

    public void setPedido(List<ItemPedido> itens, Pedido pedido) {
        for(ItemPedido item : itens) {
            item.setPedido(pedido);
        }
    }

    @Override
    public Object filterAll() {
        return null;
    }
}
