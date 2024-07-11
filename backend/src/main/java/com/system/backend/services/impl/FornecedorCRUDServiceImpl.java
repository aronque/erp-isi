package com.system.backend.services.impl;

import com.system.backend.entities.Endereco;
import com.system.backend.entities.EntidadeBase;
import com.system.backend.entities.Fornecedor;
import com.system.backend.repositories.FornecedorRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("Fornecedor")
public class FornecedorCRUDServiceImpl implements CRUDService {


    @Autowired
    private FornecedorRepository repository;

    @Override
    public EntidadeBase create(EntidadeBase fornecedor) {
        return repository.save((Fornecedor) fornecedor);
    }

    @Override
    public void update(EntidadeBase fornecedor) {
        if(repository.existsById(fornecedor.getId())) {
            repository.save((Fornecedor) fornecedor);
        }
    }

    @Override
    public List<EntidadeBase> filter(EntidadeBase fornecedor) {
        return repository.findBy((Fornecedor) fornecedor);
    }

    @Override
    public List<EntidadeBase> filterAll() {
        return repository.findAll().stream().map(filtered -> (EntidadeBase) filtered).collect(Collectors.toList());
    }

    @Override
    public void delete(EntidadeBase fornecedor) {
        repository.delete((Fornecedor) fornecedor);
    }

    @Override
    public List<EntidadeBase> filterAll(EntidadeBase fornecedor) {
        return null;
    }
}
