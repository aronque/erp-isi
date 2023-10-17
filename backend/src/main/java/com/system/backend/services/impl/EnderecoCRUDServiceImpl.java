package com.system.backend.services.impl;

import com.system.backend.entities.Endereco;
import com.system.backend.entities.Endereco;
import com.system.backend.repositories.EnderecoRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Endereco")
public class EnderecoCRUDServiceImpl implements CRUDService {


    @Autowired
    private EnderecoRepository repository;

    @Override
    public Object create(Object[] obj) {
        Endereco aux = new Endereco();
        aux.setRua(obj[1] != null ? obj[1].toString() : null);
        aux.setNumero(obj[2] != null ? obj[2].toString() : null);
        aux.setBairro(obj[3] != null ? obj[3].toString() : null);
        aux.setCidade(obj[4] != null ? obj[4].toString() : null);
        aux.setEstado(obj[5] != null ? obj[5].toString() : null);
        aux.setCep(obj[6] != null ? obj[6].toString() : null);

        return repository.save(aux);
    }

    @Override
    public void update(Object[] obj) {
        Endereco aux = new Endereco();
        aux.setRua(obj[1] != null ? obj[1].toString() : null);
        aux.setNumero(obj[2] != null ? obj[2].toString() : null);
        aux.setBairro(obj[3] != null ? obj[3].toString() : null);
        aux.setCidade(obj[4] != null ? obj[4].toString() : null);
        aux.setEstado(obj[5] != null ? obj[5].toString() : null);
        aux.setCep(obj[6] != null ? obj[6].toString() : null);
        repository.save(aux);
    }

    @Override
    public List<Endereco> filter(Object[] obj) {
        Endereco aux = new Endereco();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.setRua(obj[1] != null ? obj[1].toString() : null);
        aux.setNumero(obj[2] != null ? obj[2].toString() : null);
        aux.setBairro(obj[3] != null ? obj[3].toString() : null);
        aux.setCidade(obj[4] != null ? obj[4].toString() : null);
        aux.setEstado(obj[5] != null ? obj[5].toString() : null);
        aux.setCep(obj[6] != null ? obj[6].toString() : null);
        return repository.findByCriteria(aux);
    }

    @Override
    public List<Endereco> filterAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Object[] obj) {
        Endereco aux = new Endereco();
        aux.setId(obj[0] != null ? (Long) obj[0] : null);
        aux.setRua(obj[1] != null ? obj[1].toString() : null);
        aux.setNumero(obj[2] != null ? obj[2].toString() : null);
        aux.setBairro(obj[3] != null ? obj[3].toString() : null);
        aux.setCidade(obj[4] != null ? obj[4].toString() : null);
        aux.setEstado(obj[5] != null ? obj[5].toString() : null);
        aux.setCep(obj[6] != null ? obj[6].toString() : null);
        repository.delete(aux);
    }
}
