package com.system.backend.services.impl;

import com.system.backend.entities.EntidadeBase;
import com.system.backend.entities.Fornecedor;
import com.system.backend.entities.Produto;
import com.system.backend.repositories.ProdutoRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("Produto")
public class ProdutoCRUDServiceImpl implements CRUDService {


    @Autowired
    private ProdutoRepository repository;

    @Override
    public EntidadeBase create(EntidadeBase produto) {

        return repository.save((Produto) produto);
    }

    @Override
    public void update(EntidadeBase produto) {
        if(repository.existsById(produto.getId())) {
            repository.save((Produto) produto);
        }
    }

    @Override
    public List<EntidadeBase> filter(EntidadeBase produto) {
        return repository.findBy((Produto) produto);
    }

    @Override
    public List<EntidadeBase> filterAll() {
        return repository.findAll().stream().map(filtered -> (EntidadeBase) filtered).collect(Collectors.toList());
    }

    @Override
    public void delete(EntidadeBase produto) {
        repository.delete((Produto) produto);
    }

    @Override
    public List<EntidadeBase> filterAll(EntidadeBase produto) {
        return null;
    }
}
