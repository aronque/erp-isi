package com.system.backend.services.impl;

import com.system.backend.entities.Endereco;
import com.system.backend.entities.Fornecedor;
import com.system.backend.repositories.FornecedorRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Fornecedor")
public class FornecedorCRUDServiceImpl implements CRUDService {


    @Autowired
    private FornecedorRepository repository;

    @Override
    public Object create(Object[] obj) {
        Fornecedor aux = new Fornecedor();
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setCnpj(obj[2] != null ? obj[2].toString() : null);
        aux.setContato(obj[3] != null ? obj[3].toString() : null);
        aux.setEndereco(obj[4] != null ?  (Endereco) obj[4] : null);
        return repository.save(aux);
    }

    @Override
    public void update(Object[] obj) {
        Fornecedor aux = new Fornecedor();
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setCnpj(obj[2] != null ? obj[2].toString() : null);
        aux.setContato(obj[3] != null ? obj[3].toString() : null);
        aux.setEndereco(obj[4] != null ?  (Endereco) obj[4] : null);
        repository.save(aux);
    }

    @Override
    public List<Fornecedor> filter(Object[] obj) {
        Fornecedor aux = new Fornecedor();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setCnpj(obj[2] != null ? obj[2].toString() : null);
        aux.setContato(obj[3] != null ? obj[3].toString() : null);
        aux.setEndereco(obj[4] != null ?  (Endereco) obj[4] : null);
        return repository.findByCriteria(aux);
    }

    @Override
    public List<Fornecedor> filterAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Object[] obj) {
        Fornecedor aux = new Fornecedor();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setCnpj(obj[2] != null ? obj[2].toString() : null);
        aux.setContato(obj[3] != null ? obj[3].toString() : null);
        aux.setEndereco(obj[4] != null ?  (Endereco) obj[4] : null);
        repository.delete(aux);
    }
}
