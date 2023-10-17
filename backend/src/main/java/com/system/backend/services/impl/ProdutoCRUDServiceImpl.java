package com.system.backend.services.impl;

import com.system.backend.entities.Fornecedor;
import com.system.backend.entities.Produto;
import com.system.backend.repositories.ProdutoRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Produto")
public class ProdutoCRUDServiceImpl implements CRUDService {


    @Autowired
    private ProdutoRepository repository;

    @Override
    public Object create(Object[] obj) {
        Produto aux = new Produto();
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setFornecedor(obj[2] != null ? (Fornecedor) obj[2] : null);
        aux.setQuantidade(obj[3] != null ? (Integer) obj[3] : null);
        aux.setPreco(obj[4] != null ? (Double) obj[4] : null);
        return repository.save(aux);
    }

    @Override
    public void update(Object[] obj) {
        Produto aux = new Produto();
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setFornecedor(obj[2] != null ? (Fornecedor) obj[2] : null);
        aux.setQuantidade(obj[3] != null ? (Integer) obj[3] : null);
        aux.setPreco(obj[4] != null ? (Double) obj[4] : null);
        repository.save(aux);
    }

    @Override
    public List<Produto> filter(Object[] obj) {
        Produto aux = new Produto();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setFornecedor(obj[2] != null ? (Fornecedor) obj[2] : null);
        aux.setQuantidade(obj[3] != null ? (Integer) obj[3] : null);
        aux.setPreco(obj[4] != null ? (Double) obj[4] : null);
        return repository.findByCriteria(aux);
    }

    @Override
    public List<Produto> filterAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Object[] obj) {
        Produto aux = new Produto();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setFornecedor(obj[2] != null ? (Fornecedor) obj[2] : null);
        aux.setQuantidade(obj[3] != null ? (Integer) obj[3] : null);
        aux.setPreco(obj[4] != null ? (Double) obj[4] : null);
        repository.delete(aux);
    }
}
