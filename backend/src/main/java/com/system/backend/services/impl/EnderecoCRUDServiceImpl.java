package com.system.backend.services.impl;

import com.system.backend.entities.Endereco;
import com.system.backend.entities.EntidadeBase;
import com.system.backend.repositories.EnderecoRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("Endereco")
public class EnderecoCRUDServiceImpl implements CRUDService {


    @Autowired
    private EnderecoRepository repository;

    @Override
    public EntidadeBase create(EntidadeBase obj) {
        Endereco aux = (Endereco) obj;
        return repository.save(aux);
    }

    @Override
    public void update(EntidadeBase obj) {
        Endereco aux = (Endereco) obj;
        if(repository.existsById(aux.getId())) {
            repository.save(aux);
        }
    }

    @Override
    public List<EntidadeBase> filter(EntidadeBase obj) {
        Endereco aux = (Endereco) obj;
        return repository.findByCriteria(aux).stream().map(filtered -> (EntidadeBase) filtered).collect(Collectors.toList());
    }

    @Override
    public List<EntidadeBase> filterAll() {
        return repository.findAll().stream().map(filtered -> (EntidadeBase) filtered).collect(Collectors.toList());
    }

    @Override
    public void delete(EntidadeBase obj) {
        Endereco aux = (Endereco) obj;
        repository.delete(aux);
    }

    @Override
    public List<EntidadeBase> filterAll(EntidadeBase obj) {
        return null;
    }
}
